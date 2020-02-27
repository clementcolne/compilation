package yal.arbre.expressions;

import yal.arbre.declaration.Tds;

public class AppelFonction extends Expression{

    private Idf idf;
    private int noLig;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
        this.noLig = n;
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
        System.out.println(Tds.getInstance().identifier(idf.getNom(), noLig).getDeplacement());
        res.append("\tjal " + Tds.getInstance().identifier(idf.getNom(), noLig).getEtq() + "\n"); // jump à la fonction
        res.append("\tsw $ra, " + Tds.getInstance().getDeplacement(idf.getNom()) + "($s7)\n"); // on empile l'adresse de retour de la fonction dans la pile
        return res.toString();
    }
}
