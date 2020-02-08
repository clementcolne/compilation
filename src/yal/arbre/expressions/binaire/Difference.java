package yal.arbre.expressions.binaire;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Difference extends Expression {

    private Expression expGauche;
    private Expression expDroite;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public Difference(Expression e1, Expression e2, int n) {
        super(n);
        expDroite = e2;
        expGauche = e1;
    }

    @Override
    public String getNom() {
        return expGauche.getNom()+" != "+expDroite.getNom();
    }

    @Override
    public void verifier() {
        if((!expDroite.isBool() && !expGauche.isBool()) || (expDroite.isBool() && expGauche.isBool())) {
            expDroite.verifier();
            expGauche.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu doit être identique");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * renvoie l'opérateur de l'expression
     * @return String
     */
    @Override
    public String getOper(){
        return "!=";
    }

    @Override
    public String toMIPS() {    // TODO trouver un moyen de factoriser
        String res = expGauche.toMIPS() + "\n";
        res += "\t# Empiler $v0\n";
        res += "\tsw $v0,($sp)\n";
        res += "\tadd $sp,$sp,-4\n";

        res += expDroite.toMIPS() + "\n";
        res += "\t# Dépiler $v0\n";
        res += "\tadd $sp,$sp,4\n";
        res += "\tlw $t8,($sp)\n";

        return res;
    }

    @Override
    public boolean isBool() {
        return true;
    }
}
