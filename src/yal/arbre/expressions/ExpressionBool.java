package yal.arbre.expressions;

public abstract class ExpressionBool extends Expression {

    /**
     * Constructeur d'une expression booléenne
     *
     * @param n int
     */
    protected ExpressionBool(int n) {
        super(n);
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
