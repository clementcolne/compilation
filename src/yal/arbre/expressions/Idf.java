package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

public class Idf extends Expression{

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
    public void verifier() {
            if(!Tds.getInstance().identifier(nom,noLigne,"entier",0).getType().equals("entier")){
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": variable "+nom+" non déclarée");
                Tds.getInstance().add(a.getMessage());
            }
    }

    /**
     * Renvoie le code Mips de la variable
     * @return String
     */
    @Override
    public String toMIPS() {
        int deplacement = Tds.getInstance().getDeplacement(nom);
        StringBuilder res = new StringBuilder();
        res.append("\tlw $v0, " + deplacement + "($s7)");
        return res.toString();
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
