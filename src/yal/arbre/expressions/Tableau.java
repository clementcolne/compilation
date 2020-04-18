package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

public class Tableau extends Expression {

    private Expression exp;
    private Idf nom;

    /**
     * Constructeur d'un tableau
     * @param n int
     */
    public Tableau(Idf nom, int n, Expression e) {
        super(n);
        this.nom = nom;
        noLigne = n;
        exp = e;
    }

    /**
     * Retourne le nom du tableau
     * @return
     */
    @Override
    public String getNom() {
        return nom.getNom()+"[indice]";
    }

    /**
     * Vérifie
     */
    @Override
    public void verifier() {
        if(!Tds.getInstance().identifier(nom.getNom(), noLigne,"tableau",0).getType().equals("tableau")){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": tableau " + nom + " non déclarée");
            Tds.getInstance().add(a.getMessage());

        }
        if(exp.isBool()){
            AnalyseSemantiqueException as = new AnalyseSemantiqueException(noLigne, ": l'expression doit être entière dans le tableau " + nom);
            Tds.getInstance().add(as.getMessage());
        }else{
            exp.verifier();
        }
    }

    /**
     * Retourne le toMIPS du tableau
     * @return le toMIPS du tableau
     */
    @Override
    public String toMIPS() {
        return null;
    }
}
