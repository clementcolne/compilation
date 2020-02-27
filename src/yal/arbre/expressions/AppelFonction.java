package yal.arbre.expressions;

public class AppelFonction extends Expression{

    private Idf idf;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
    }

    @Override
    public String getNom() {
        return idf.getNom()+"()";
    }

    @Override
    public void verifier() {
        idf.verifier();
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
