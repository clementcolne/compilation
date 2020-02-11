package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Division extends ExpressionEntier{
    /**
     * Constructeur d'une expression
     * @param e1 expression gauche
     * @param e2 expression droite
     * @param n  int
     */
    public Division(Expression e1, Expression e2, int n) {
        super(e1, e2, n);
    }

    /**
     * Retourne le nom de la division
     * @return le nom de la division
     */
    @Override
    public String getNom() {
        return expGauche.getNom()+" / "+expDroite.getNom();
    }
    /**
     * Vérifie qu'une division par 0 n'est pas explicitement demandée
     */
    @Override
    public void verifier() {
        super.verifier();
        // division par 0 explicite
        if(expDroite.getNom().equals("0")){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": division par 0");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Retourne le code mips de la division
     * @return le code mips de la division
     */
    @Override
    public String toMIPS() {
        int etq = Tds.getInstance().getIdfEtiquette();
        String res = "";
        res += expGauche.toMIPS() + "\n";
        res += "\t# Empiler $v0\n";
        res += "\tsw $v0,($sp)\n";
        res += "\tadd $sp,$sp,-4\n";
        res += expDroite.toMIPS() + "\n";
        res += "\t# Dépiler $v0\n";
        res += "\tadd $sp,$sp,4\n";
        res += "\tlw $t8,($sp)\n";
        res += "\t# évaluation de l'opérande droite de la division\n";
        res += "\tbeqz $v0, erreurDivisionZero\n";
        res += "\tdiv $v0, $t8, $v0\n";
        res += "\tj suite"+etq+"\n";
        res += "suite"+etq+":\n";

        return res;
    }
}
