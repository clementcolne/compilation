package yal.arbre.expressions;

public class TailleTableau extends Expression {
    /**
     * Constructeur d'une taille de tableau
     * @param n int
     */
    protected TailleTableau(int n) {
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
