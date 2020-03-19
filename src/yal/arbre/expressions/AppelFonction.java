package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

import java.util.ArrayList;

public class AppelFonction extends Expression{

    // ATTENTION: le 1er param se trouve en dernier dans l'AL !

    private Idf idf;
    private int noLig;
    private int nbParam;
    private Expression exp;
    private ArbreAbstrait arbre;
    private ArrayList<Expression> expList;

    /**
     * Constructeur d'une expression
     * @param n int
     */
    public AppelFonction(Idf idf, int n) {
        super(n);
        this.idf = idf;
        this.noLig = n;
        expList = Gestionnaire.getInstance().getExp();
        nbParam = expList.size();
        Gestionnaire.getInstance().resetParam();
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
        expList = Gestionnaire.getInstance().getExp();
        nbParam = expList.size();
        Gestionnaire.getInstance().resetParam();
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
        expList = Gestionnaire.getInstance().getExp();
        nbParam = expList.size();
        Gestionnaire.getInstance().resetParam();
    }


    /**
     * Retourne le nom de la fonction pour le commentaire MIPS
     * @return le nom de la fonction pour le commentaire MIPS
     */
    @Override
    public String getNom() {
        StringBuilder res = new StringBuilder(idf.getNom());
        res.append("(");
        if(expList.size()>0) {
            res.append(expList.get(0).getNom());
        }
        if(expList.size()>=1) {
            for (int i = 1; i < expList.size(); i++) {
                res.append("," + expList.get(i).getNom());
            }
        }
        res.append(")");
        return res.toString();
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
        for(Expression e: expList){
            if(e.isBool()){
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(e.getNoLigne(), ": le type doit être entier dans : "+e.getNom());
                Tds.getInstance().add(a.getMessage());
            }else{
                e.verifier();
            }
        }
    }

    /**
     * Retourne le code MIPS de l'appel de fonction
     * @return le code MIPS de l'appel de fonction
     */
    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();

        //mettre s2

        /*expList.get(expList.size()-1).toMIPS();
        // empiler $v0
        for(int i=0; i<expList.size()-1; i++){
            expList.get(i).toMIPS();
            // empiler $v0
        }*/

        res.append("\tjal " + Tds.getInstance().identifier(idf.getNom(), noLig,"fonction",nbParam).getEtq() + "\n"); // jump à la fonction
        return res.toString();
    }
}
