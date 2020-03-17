package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

public class AppelFonction extends Expression{

    private Idf idf;
    private int noLig;
    private int nbParam;
    private Expression exp;
    private ArbreAbstrait arbre;

    /**
     * Constructeur d'une expression
     * @param n int
     */
    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
        this.noLig = n;
        nbParam = Gestionnaire.getInstance().getNbParam();
    }

    /**
     * Constructeur d'une expression
     * @param n int
     */
    public AppelFonction(Idf idf, int n, Expression p, ArbreAbstrait a) {
        super(n);
        this.idf = idf;
        this.noLig = n;
        this.arbre = a;
        this.exp = p;
    }

    /**
     * Constructeur d'une expression
     * @param n int
     */
    public AppelFonction(Idf idf, int n, Expression p) {
        super(n);
        this.idf = idf;
        this.noLig = n;
        this.exp = p;
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
        if(!Tds.getInstance().identifier(idf.getNom(),noLig,"fonction",nbParam).getType().equals("fonction")){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": fonction "+idf.getNom()+" non déclarée");
            Tds.getInstance().add(a.getMessage());
        }
        if(exp != null){
            exp.verifier();
        }
        if(arbre != null){
            arbre.verifier();
        }
    }

    /**
     * Retourne le code MIPS de l'appel de fonction
     * @return le code MIPS de l'appel de fonction
     */
    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        if(arbre != null) {
            // On fait appel aux toMIPS de paramètre
            res.append(arbre.toMIPS());
        }
        res.append("\tjal " + Tds.getInstance().identifier(idf.getNom(), noLig,"fonction",nbParam).getEtq() + "\n"); // jump à la fonction
        return res.toString();
    }
}
