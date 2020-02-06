package yal.arbre.expressions.binaire.expBool;

import yal.arbre.expressions.Expression;

public class BoolOu extends ExpressionBool{
    /**
     * Constructeur d'une expression booléenne
     *
     * @param n int
     */
    public BoolOu(Expression e1, Expression e2, int n) {
        super(e1,e2,n);
    }
}
