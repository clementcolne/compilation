package yal.arbre.expressions;

public class AppelFonction extends Expression{

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public AppelFonction(String idf, int n) {
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
