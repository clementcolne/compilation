package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

public class TailleTableau extends Expression {


    private Idf nom;
    /**
     * Constructeur d'une taille de tableau
     * @param n int
     */
    public TailleTableau(Idf nom, int n) {
        super(n);
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return nom.getNom()+".longueur";
    }

    @Override
    public void verifier() {
        if(!Tds.getInstance().identifier(nom.getNom(), noLigne,"tableau",0).getType().equals("tableau")){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": tableau " + nom + " non déclarée");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        return "\tli $v0, " + Tds.getInstance().identifier(nom.getNom(), noLigne,"tableau",0).getDeplacement();
    }
}
