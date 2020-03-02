package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

public class AppelFonction extends Expression{

    private Idf idf;
    private int noLig;

    /**
     * Constructeur d'une expression
     * @param n int
     */
    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
        this.noLig = n;
    }

    /**
     * Retourne le nom de la fonction pour le commentaire MIPS
     * @return le nom de la fonction pour le commentaire MIPS
     */
    @Override
    public String getNom() {
        return idf.getNom()+"()";
    }

    /**
     * Vérifie la conformité de l'appel de fonction
     */
    @Override
    public void verifier() {
        if(!Tds.getInstance().identifier(idf.getNom(),noLig,"fonction").getType().equals("fonction")){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": fonction non déclarée");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Retourne le code MIPS de l'appel de fonction
     * @return le code MIPS de l'appel de fonction
     */
    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("\tjal " + Tds.getInstance().identifier(idf.getNom(), noLig,"fonction").getEtq() + "\n"); // jump à la fonction
        return res.toString();
    }
}
