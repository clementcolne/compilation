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
        Tds.getInstance().setDeplacementVarLoc(idf);
        StringBuilder res = new StringBuilder();
        res.append(etq + ":\n");
        res.append("\t# Sauvegarde $sp dans $s2\n");
        res.append("\tmove $s2, $sp\n"); // $s2 = $sp
        res.append("\t# Empile les paramètres\n");
        // pour tous les paramètres de la fonction
        for(Symbole p : parametres) {
            System.out.println(p.getDeplacement());
        }
        /*for(Symbole p : parametres) {
            // on charge la valeur du paramètre dans $v0
            res.append("\tlw $v0, " + p.getDeplacement() + "($7)\n");
            // on empile $v0 dans la pile dédiée à la fonction
            res.append("\t\n");
        }*/
        res.append("\t\n");
        res.append("\t# Sauvegarde de l'adresse de retour\n");
        res.append("\tsw $ra, ($sp)\n"); // on empile l'adresse de retour de la fonction dans la pile
        res.append("\tadd, $sp, $sp, -4\n\n");
        //res.append("\t# chainage dynamique\n");
        //res.append("\tsw $s7, ($sp)\n"); // chainage dynamique -> contraire dans le retour
        //res.append("\tadd, $sp, $sp, -4\n\n");
        Gestionnaire.getInstance().setCptProg();
        res.append(arbre.toMIPS());
        res.append("\tj erreurRetour\n\n");
        Tds.getInstance().suppBloc(bloc);
        return res.toString();
    }
}
