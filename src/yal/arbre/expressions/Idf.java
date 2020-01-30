package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

public class Idf extends Expression{

    //private int noLig;
    private String nom;

    /**
     * Constructeur de l'Idf
     * @param nom String
     * @param n int
     */
    public Idf(String nom, int n){
        super(n);
        this.nom = nom;
        this.noLigne = n;
    }

    /**
     * Vérifie si la variable est déjà déclarée ou pas
     * @throws AnalyseSemantiqueException
     */
    @Override
    public void verifier() throws AnalyseSemantiqueException{
        try {
            Tds.getInstance().identifier(nom);
        }catch (Exception e){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": variable non déclarée");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Renvoie le code Mips de la variable
     * @return String
     */
    @Override
    public String toMIPS() {
        return null;
    }

    /**
     * Renvoie le nom de la variable
     * @return String
     */
    @Override
    public String getNom() {
        return nom;
    }

}
