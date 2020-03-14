package yal.arbre.declaration;

import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

public class Fonction {

    private ArbreAbstrait arbre;
    private int noLigne;
    private String etq;
    private String idf;
    private int bloc;

    /**
     * Constructeur d'une fonction
     * @param a ArbreAbstrait
     * @param n numéro de ligne
     * @param idf identifiant
     * @param etq étiquette
     */
    public Fonction(ArbreAbstrait a, int n, String idf, String etq, int bloc){
        arbre = a;
        noLigne = n;
        this.idf = idf;
        this.etq = etq;
        this.bloc = bloc;
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
        Gestionnaire.getInstance().setFonctionCourante(idf);
        StringBuilder res = new StringBuilder();
        res.append(etq + ":\n");
        // Ajout des variables locales
        res.append("\t# Sauvegarde de l'adresse de retour\n");
        res.append("\tsw $ra, ($sp)\n"); // on empile l'adresse de retour de la fonction dans la pile
        res.append("\tadd, $sp, $sp, -4\n\n");
        //res.append("\tsw $s7, 4($sp)\n"); // chainage dynamique -> contraire dans le retour
        Gestionnaire.getInstance().setCptProg();
        res.append(arbre.toMIPS());
        res.append("\tj erreurRetour\n\n");
        Gestionnaire.getInstance().setFonctionCourante("");
        Tds.getInstance().suppBloc(bloc);
        return res.toString();
    }
}