package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Division extends ExpressionEntier{
    /**
     * Constructeur d'une expression
     *
     * @param e1
     * @param e2
     * @param n  int
     */
    public Division(Expression e1, Expression e2, int n) {
        super(e1, e2, n);
    }

    @Override
    public String getNom() {
        return expGauche.getNom()+" / "+expDroite.getNom();
    }

    /**
     * Renvoie le résultat entier de l'expression pour vérifier si c'est une division par 0
     * @return int
     */
    @Override
    public int getNombre(){
        return expGauche.getNombre() / expDroite.getNombre();
    }

    @Override
    public void verifier() {
        super.verifier();
        if(expDroite.getNombre()==0){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": division par 0");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        String res = "";
        res += expGauche.toMIPS() + "\n";
        res += "\t# Empiler $v0\n";
        res += "\tsw $v0,($sp)\n";
        res += "\tadd $sp,$sp,-4\n";
        //res += "\tmove $t8, $v0\n";
        res += expDroite.toMIPS() + "\n";
        res += "\t# Dépiler $v0\n";
        res += "\tadd $sp,$sp,4\n";
        res += "\tlw $t8,($sp)\n";

        res += "\tdivu $v0, $t8, $v0\n";

        return res;
    }
}
