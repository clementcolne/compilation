package yal.arbre.expressions;

public class Idf extends Expression{

    private String nom;
    private int noLig;

    public Idf(String nom, int n){
        super(n);
        this.nom = nom;
        this.noLig = n;
    }
    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
