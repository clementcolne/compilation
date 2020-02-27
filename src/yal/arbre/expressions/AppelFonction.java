package yal.arbre.expressions;

import yal.arbre.declaration.Tds;

public class AppelFonction extends Expression{

    private Idf idf;
    private int n;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
        this.n = n;
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
        StringBuilder res = new StringBuilder();
        res.append("\tjal " + Tds.getInstance().identifier(idf.getNom(), n).getEtq() + "\n");
        return null;
    }
}
