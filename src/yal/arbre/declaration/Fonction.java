package yal.arbre.declaration;

import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

import java.util.ArrayList;

public class Fonction {

    private ArbreAbstrait arbre;
    private int noLigne;
    private String etq;
    private int idf;
    private int bloc;
    private ArrayList<Symbole> parametres;
    private ArrayList<Symbole> varLoc;

    /**
     * Constructeur d'une fonction
     * @param a ArbreAbstrait
     * @param n numéro de ligne
     * @param idf identifiant
     * @param etq étiquette
     */
    public Fonction(ArbreAbstrait a, int n, int idf, String etq, int bloc){
        arbre = a;
        noLigne = n;
        this.idf = idf;
        this.etq = etq;
        this.bloc = bloc;
        Tds.getInstance().setDeplacementVarLoc(idf);
        parametres = Tds.getInstance().getParametres(idf);
        varLoc = Tds.getInstance().getVarLoc(idf);
    }

    /**
     * Vérifie la conformité de la fonction
     */
    public void verifier() {
        Tds.getInstance().ajoutBloc(bloc);
        Gestionnaire.getInstance().resetCptRetourne();
        Gestionnaire.getInstance().setCptProg();
        Gestionnaire.getInstance().setInFonction(true);
        arbre.verifier();
        if (Gestionnaire.getInstance().getCptRetourne() == 0) {
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": une fonction doit avoir au moins un retour");
            Tds.getInstance().add(a.getMessage());
        }
        Gestionnaire.getInstance().setInFonction(false);
        Gestionnaire.getInstance().resetCptProg();
        Gestionnaire.getInstance().resetCptRetourne();

        Tds.getInstance().suppBloc(bloc);
    }

    /**
     * Retourne le code MIPS de la fonction
     * @return le code MIPS de la fonction
     */
    public String toMIPS() {
        Tds.getInstance().ajoutBloc(bloc);
        StringBuilder res = new StringBuilder();
        res.append(etq + ":\n");


        // on alloue la mémoire pour les paramètres locaux
        res.append("\t# Allocation mémoire pour les " + varLoc.size() + " paramètres locaux\n");
        int cpt = varLoc.size()*-4;
        res.append("\tadd, $sp, $sp, " + cpt + "\n\n");

        //res.append("\t# chainage dynamique\n");
        //res.append("\tsw $s7, ($sp)\n"); // chainage dynamique -> contraire dans le retour
        //res.append("\tadd, $sp, $sp, -4\n\n");

        res.append("\t# Sauvegarde de l'adresse de retour\n");
        res.append("\tsw $ra, ($sp)\n"); // on empile l'adresse de retour de la fonction dans la pile
        res.append("\tadd, $sp, $sp, -4\n\n");

        Gestionnaire.getInstance().setCptProg();
        res.append(arbre.toMIPS());

        res.append("\tj erreurRetour\n\n");
        Tds.getInstance().suppBloc(bloc);
        return res.toString();
    }

    public void afficherInformations() {
        System.out.println("Paramètres : " + parametres.size());
        System.out.println("Déplacements : ");
        for(Symbole p : parametres) {
            System.out.println("* " + p.getDeplacement());
        }
        System.out.println("VarLoc : " + varLoc.size());
        System.out.println("Déplacements : ");
        for(Symbole p : varLoc) {
            System.out.println("* " + p.getDeplacement());
        }
    }
}
