package yal.arbre;

public class Entree {

    private String nom;
    private int noLig;

    public Entree(String nom, int n) {
        this.nom = nom;
        this.noLig = n;
    }

    public String getNom() {
        return nom;
    }

    public int getNoLig() {
        return noLig;
    }
}
