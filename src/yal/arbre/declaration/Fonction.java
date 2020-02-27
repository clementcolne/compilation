package yal.arbre.declaration;

import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

public class Fonction {

    private ArbreAbstrait arbre;
    private int noLigne;
    private String etq;

    public Fonction(ArbreAbstrait a, int n, String idf){
        arbre = a;
        noLigne = n;
        etq = "fonction" + Gestionnaire.getInstance().getIdfEtiquette();
        Tds.getInstance().identifier(idf,n).setEtq(etq);
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
        StringBuilder res = new StringBuilder();
        res.append(etq + ":\n");
        Gestionnaire.getInstance().setCptProg();
        res.append(arbre.toMIPS());
        Tds.getInstance().suppBloc();
        return res.toString();
    }
}