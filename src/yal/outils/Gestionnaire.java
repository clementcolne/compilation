package yal.outils;

import yal.arbre.declaration.Fonction;

import java.util.ArrayList;

public class Gestionnaire {

    private int idfEtiquette;
    private boolean cptProg;
    private boolean inFonction;
    private int cptRetourne;
    private boolean finProg;
    private String fonctionCourante;
    private ArrayList<Fonction> declFonc;
    private static Gestionnaire gestionnaire = new Gestionnaire();


    public static Gestionnaire getInstance() {
        return gestionnaire;
    }


    /**
     * Constructeur de Gestionnaire
     */
    private Gestionnaire() {
        idfEtiquette = 0;
        cptRetourne = 0;
        cptProg = false;
        inFonction = false;
        finProg = false;
        fonctionCourante = "";
        declFonc = new ArrayList<>();
    }

    public void setCptProg(){
        cptProg = true;
    }

    public void resetCptProg(){
        cptProg = false;
    }

    /**
     * renvoie un identifiant unique pour l'étiquette de la condition
     * @return int
     */
    public int getIdfEtiquette() {
        idfEtiquette++;
        return idfEtiquette;
    }

    /**
     * Renvoie combien de bloc d'instruction on a
     * @return int
     */
    public boolean getCptProg() {
        return cptProg;
    }

    public void addFonc(Fonction f){
        declFonc.add(f);
    }

    public String afficheFonction(){
        StringBuilder res = new StringBuilder();
        for(Fonction f: declFonc){
            res.append(f.toMIPS());
        }
        return res.toString();
    }

    public void verifierFonc() {
        for(Fonction f: declFonc){
            f.verifier();
        }
    }

    public void resetCptRetourne(){
        cptRetourne = 0;
    }

    public void setInFonction(boolean b){
        inFonction = b;
    }

    public boolean isInFonction() {
        return inFonction;
    }

    public int getCptRetourne() {
        return cptRetourne;
    }

    public void addRetourne(){
        cptRetourne++;
    }

    public void setFinProg(){
        finProg = true;
    }

    public boolean isFinProg() {
        return finProg;
    }

    public String getFonctionCourante() {
        return fonctionCourante;
    }

    public void setFonctionCourante(String fonctionCourante) {
        this.fonctionCourante = fonctionCourante;
    }
}
