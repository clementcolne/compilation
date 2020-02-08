package yal.arbre.expressions.unaire;

import yal.arbre.expressions.Expression;

public class ExpressionParenthese extends Expression {

    private Expression exp;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public ExpressionParenthese(Expression e,int n) {
        super(n);
        exp = e;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        return exp.toMIPS();
    }

    @Override
    public boolean isBool() {
        return exp.isBool();
    }
}
