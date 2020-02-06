package yal.arbre.expressions;

public class ExpressionParenthese extends Expression {

    private Expression e;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public ExpressionParenthese(Expression e,int n) {
        super(n);
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
