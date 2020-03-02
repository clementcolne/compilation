package yal.arbre.declaration;

import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

public class Fonction {

    private ArbreAbstrait arbre;
    private int noLigne;
    private String etq;
    private String idf;

    public Fonction(ArbreAbstrait a, int n, String idf, String etq){
        arbre = a;
        noLigne = n;
        this.idf = idf;
        this.etq = etq;
    }


    public void verifier() {

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

    }


    public String toMIPS() {
        Gestionnaire.getInstance().setFonctionCourante(idf);
        StringBuilder res = new StringBuilder();
        res.append(etq + ":\n");
        res.append("\t# Sauvegarde de l'adresse de retour\n");
        res.append("\tsw $ra, ($sp)\n"); // on empile l'adresse de retour de la fonction dans la pile
        res.append("\tadd, $sp, $sp, -4\n\n");
        //res.append("\tsw $s7, 4($sp)\n"); // chainage dynamique
        Gestionnaire.getInstance().setCptProg();
        res.append(arbre.toMIPS());

        Gestionnaire.getInstance().setFonctionCourante("");
        return res.toString();
    }
}