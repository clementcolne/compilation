package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.expressions.Expression;

public class Addition extends ExpressionEntier {
    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public Addition(Expression e1, Expression e2, int n) {
        super(e1,e2,n);
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
