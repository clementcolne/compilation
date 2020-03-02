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

    /**
     * Retourne une instance de Gestionnaire
     * @return une instance de Gestionnaire
     */
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

    /**
     * Fixe à vrai cptProg
     */
    public void setCptProg(){
        cptProg = true;
    }

    /**
     * Fixe à faux cptProg
     */
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

    /**
     * Ajoute la fonction en paramètre à la liste des fonctions déclarées
     * @param f fonction
     */
    public void addFonc(Fonction f){
        declFonc.add(f);
    }

    /**
     * Affiche la fonction
     * @return String
     */
    public String afficheFonction(){
        StringBuilder res = new StringBuilder();
        for(Fonction f: declFonc){
            res.append(f.toMIPS());
        }
        return res.toString();
    }

    /**
     * Vérifie si la fonction est conforme
     */
    public void verifierFonc() {
        for(Fonction f: declFonc){
            f.verifier();
        }
    }

    /**
     * Remet à 0 le compteur de retourne
     */
    public void resetCptRetourne(){
        cptRetourne = 0;
    }

    /**
     * Fixe la fonction en paramètre comme fonction dans laquelle la compilation se situe
     * @param b fonction
     */
    public void setInFonction(boolean b){
        inFonction = b;
    }

    /**
     * Retourne vrai si la compilation se situe dans une fonction, faux sinon
     * @return vrai si la compilation se situe dans une fonction, faux sinon
     */
    public boolean isInFonction() {
        return inFonction;
    }

    /**
     * Retourne le compteur de retourne
     * @return le compteur de retourne
     */
    public int getCptRetourne() {
        return cptRetourne;
    }

    /**
     * Incrémente de 1 le compteur de retourne
     */
    public void addRetourne(){
        cptRetourne++;
    }

    /**
     * Fixe à vrai la fin du programme
     */
    public void setFinProg(){
        finProg = true;
    }

    /**
     * Retourne vrai si la compilation arrive au fin du programme, faux sinon
     * @return vrai si la compilation arrive au fin du programme, faux sinon
     */
    public boolean isFinProg() {
        return finProg;
    }

    /**
     * Définit la fonction en paramètre comme fonction courante
     * @param fonctionCourante nom de la fonction
     */
    public void setFonctionCourante(String fonctionCourante) {
        this.fonctionCourante = fonctionCourante;
    }
}
