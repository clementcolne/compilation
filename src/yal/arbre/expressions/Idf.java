package yal.arbre.expressions;

import yal.arbre.Entree;
import yal.arbre.Tds;
import yal.exceptions.AnalyseSemantiqueException;

public class Idf extends Expression{

    private String nom;
    private int noLig;

    public Idf(String nom, int n){
        super(n);
        this.nom = nom;
        this.noLig = n;
    }
    @Override
    public void verifier(){
        try{
            Tds.getInstance().identifier(new Entree(nom));
        }catch (Exception e){
            throw new AnalyseSemantiqueException(noLig, ": déclaration multiple de variables");
        }

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
