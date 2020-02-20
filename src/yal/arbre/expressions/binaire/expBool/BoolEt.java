package yal.arbre.expressions.binaire.expBool;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;

public class BoolEt extends ExpressionBool{
    /**
     * Constructeur d'une expression booléenne
     * @param n int
     */
    public BoolEt(Expression e1, Expression e2, int n) {
        super(e1,e2,n);
    }

    /**
     * Retourne le nom de l'expression booléenne
     * @return le nom de l'expression booléenne
     */
    @Override
    public String getNom() {
        return expGauche.getNom() + " et " + expDroite.getNom();
    }

    /**
     * Retourne le code toMIPS de l'expression booléenne
     * @return le code toMIPS de l'expression booléenne
     */
    @Override
    public String toMIPS() {
        int etq1 = Tds.getInstance().getIdfEtiquette();
        int etq2 = Tds.getInstance().getIdfEtiquette();
        StringBuilder res = new StringBuilder();  // $t8

        res.append(expDroite.toMIPS() + "\n");  // $v0
        res.append("\tla $t8, Faux\n");

        res.append("\tbeq $v0, $t8, si"+etq1+"\n");
        res.append("\tj sinon"+etq1+"\n");
        res.append("\tsi"+etq1+":\n");
        res.append("\tla $v0, Faux\n");
        res.append("\tj suite"+etq1+"\n");   // expDroite est Fausse

        res.append("\tsinon"+etq1+":\n");
        // si expdroit est Vraie
        res.append(expGauche.toMIPS() + "\n");  // $t8
        res.append("\t# Empiler $v0\n");
        res.append("\tsw $v0,($sp)\n");
        res.append("\tadd $sp,$sp,-4\n");
        res.append("\t# Dépiler $v0\n");
        res.append("\tadd $sp,$sp,4\n");
        res.append("\tlw $t8,($sp)\n");
        res.append("\tla $v0, Faux\n");
        res.append("\tbeq $t8, $v0, si"+etq2+"\n");
        res.append("\tj sinon"+etq2+"\n");
        res.append("\tsi"+etq2+":\n");
        res.append("\tla $v0, Faux\n");  // expgauche est Vraie
        res.append("\tj suite"+etq1+"\n");
        res.append("\tsinon"+etq2+":\n");
        res.append("\tla $v0, Vrai\n");    // les deux exp sont vraies
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
