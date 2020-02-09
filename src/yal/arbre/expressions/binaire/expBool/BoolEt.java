package yal.arbre.expressions.binaire.expBool;

import yal.arbre.expressions.Expression;

public class BoolEt extends ExpressionBool{
    /**
     * Constructeur d'une expression booléenne
     *
     * @param n int
     */
    public BoolEt(Expression e1, Expression e2, int n) {
        super(e1,e2,n);
    }

    @Override
    public String getNom() {
        return expGauche.getNom()+" et "+expDroite.getNom();
    }

    @Override
    public String toMIPS() {
        String res = expGauche.toMIPS() + "\n";
        res += "\t# Empiler $v0\n";
        res += "\tsw $v0,($sp)\n";
        res += "\tadd $sp,$sp,-4\n";

        res += expDroite.toMIPS() + "\n";
        res += "\t# Dépiler $v0\n";
        res += "\tadd $sp,$sp,4\n";
        res += "\tlw $t8,($sp)\n";

        res += "\tand $v0, $t8, $v0\n";

        return res;
    }
}
