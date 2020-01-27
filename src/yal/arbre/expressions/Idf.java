package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

public class Idf extends Expression{

    private int noLig;
    private String nom;

    public Idf(String nom, int n){
        super(n);
        this.nom = nom;
        this.noLig = n;
    }
    @Override
    public void verifier() throws AnalyseSemantiqueException{
        try {
            Tds.getInstance().identifier(nom);
        }catch (Exception e){
            Tds.getInstance().ajoutErreur();
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": variable non déclarée");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        return null;
    }

    @Override
    public String getNom() {
        return nom;
    }

}
