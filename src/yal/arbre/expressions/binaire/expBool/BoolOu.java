package yal.arbre.expressions.binaire.expBool;

import yal.arbre.expressions.Expression;
import yal.outils.Gestionnaire;

public class BoolOu extends ExpressionBool{
    /**
     * Constructeur d'une expression booléenne
     * @param n int
     */
    public BoolOu(Expression e1, Expression e2, int n) {
        super(e1,e2,n);
    }

    /**
     * Retourne le nom de l'expression booléenne
     * @return le nom de l'expression booléenne
     */
    public String getNom() {
        return expGauche.getNom()+" ou "+expDroite.getNom();
    }

    /**
     * Retourne le code toMIPS de l'expression booléenne
     * @return le code toMIPS de l'expression booléenne
     */
    @Override
    public String toMIPS() {
        int etq1 = Gestionnaire.getInstance().getIdfEtiquette();
        int etq2 = Gestionnaire.getInstance().getIdfEtiquette();

        StringBuilder res = new StringBuilder();

        res.append(expDroite.toMIPS() + "\n");
        res.append("\tla $t8, Vrai\n");

        res.append("\tbeq $v0, $t8, si"+etq1+"\n");
        res.append("\tj sinon"+etq1+"\n");   // expDroite est fausse
        res.append("\tsi"+etq1+":\n");
        res.append("\tla $v0, Vrai\n");
        res.append("\tj suite"+etq1+"\n");   // expDroite est Vraie

        res.append("\tsinon"+etq1+":\n");
        res.append(expGauche.toMIPS() + "\n");
        res.append("\t# Empiler $v0\n");
        res.append("\tsw $v0,($sp)\n");
        res.append("\tadd $sp,$sp,-4\n");
        res.append("\t# Dépiler $v0\n");
        res.append("\tadd $sp,$sp,4\n");
        res.append("\tlw $t8,($sp)\n");
        res.append("\tla $v0, Vrai\n");
        res.append("\tbeq $t8, $v0, si"+etq2+"\n");
        res.append("\tj sinon"+etq2+"\n");
        res.append("\tsi"+etq2+":\n");
        res.append("\tla $v0, Vrai\n");  // expgauche est Vraie
        res.append("\tj suite"+etq1+"\n");
        res.append("\tsinon"+etq2+":\n");
        res.append("\tla $v0, Faux\n");  // les deux exp sont fausses
        res.append("\tj suite"+etq1+"\n");
        res.append("\tsuite"+etq1+":\n");

        return res.toString();
    }

    /**
     * Retourne vrai
     * @return vrai
     */
    @Override
    public boolean isBool() {
        return true;
    }
}
