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
        return expGauche.getNom()+" et "+expDroite.getNom();
    }

    /**
     * Retourne le code toMIPS de l'expression booléenne
     * @return le code toMIPS de l'expression booléenne
     */
    @Override
    public String toMIPS() {
        int etq1 = Tds.getInstance().getIdfEtiquette();
        int etq2 = Tds.getInstance().getIdfEtiquette();
        String res = "";  // $t8

        res += expDroite.toMIPS() + "\n";  // $v0
        res += "\tla $t8, Faux\n";

        res += "\tbeq $v0, $t8, si"+etq1+"\n";
        res += "\tj sinon"+etq1+"\n";
        res += "\tsi"+etq1+":\n";
        res += "\tla $v0, Faux\n";
        res += "\tj suite"+etq1+"\n";   // expDroite est Fausse

        res += "\tsinon"+etq1+":\n";
        // si expdroit est Vraie
        res += expGauche.toMIPS() + "\n";  // $t8
        res += "\t# Empiler $v0\n";
        res += "\tsw $v0,($sp)\n";
        res += "\tadd $sp,$sp,-4\n";
        res += "\t# Dépiler $v0\n";
        res += "\tadd $sp,$sp,4\n";
        res += "\tlw $t8,($sp)\n";
        res += "\tla $v0, Faux\n";
        res += "\tbeq $t8, $v0, si"+etq2+"\n";
        res += "\tj sinon"+etq2+"\n";
        res += "\tsi"+etq2+":\n";
        res += "\tla $v0, Faux\n";  // expgauche est Vraie
        res += "\tj suite"+etq1+"\n";
        res += "\tsinon"+etq2+":\n";
        res += "\tla $v0, Vrai\n";    // les deux exp sont vraies
        res += "\tj suite"+etq1+"\n";
        res += "\tsuite"+etq1+":\n";

        return res;
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
