package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

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
        if(!Tds.getInstance().identifier(idf.getNom(),noLig).getType().equals("fonction")){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": fonction non déclarée");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("\tadd, $sp, $sp, -8\n");
        res.append("\tjal " + Tds.getInstance().identifier(idf.getNom(), noLig).getEtq() + "\n"); // jump à la fonction
        return res.toString();
    }
}
