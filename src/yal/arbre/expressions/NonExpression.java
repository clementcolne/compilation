package yal.arbre.expressions;

public class NonExpression extends Expression {

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    protected NonExpression(String e,int n) {
        super(n);
        nom = e;
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
