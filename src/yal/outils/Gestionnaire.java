package yal.outils;

import yal.arbre.declaration.Fonction;
import yal.arbre.expressions.Parametre;

import java.util.ArrayList;

public class Gestionnaire {

    private int idfEtiquette;
    private boolean cptProg;
    private boolean inFonction;
    private int cptRetourne;
    private boolean finProg;
    private int fonctionCourante;
    private String nomFoncCourante;
    private int ligFoncCourante;
    private ArrayList<Parametre> param;
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
        fonctionCourante = 0;
        param = new ArrayList<>();
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
     * Incrémente le compteur d'identifiant de fonction
     */
    public void setFonctionCourante() {
        this.fonctionCourante++;
    }

    /**
     * Renvoie l'identifiant de la fonction courante
     * @return int
     */
    public int getFonctionCourante() {
        return fonctionCourante;
    }

    /**
     * Renvoie le nom de la fonction courante
     * @return String
     */
    public String getNomFoncCourante() {
        return nomFoncCourante;
    }

    /**
     * Met à jour le nom de la fonction courante
     */
    public void setNomFoncCourante(String nomFoncCourante) {
        this.nomFoncCourante = nomFoncCourante;
    }

    /**
     * Renvoie la ligne de déclaration de la fonction courante
     * @return int
     */
    public int getLigFoncCourante() {
        return ligFoncCourante;
    }

    /**
     * Met à jour la ligne de déclaration de la fonction courante
     */
    public void setLigFoncCourante(int ligFoncCourante) {
        this.ligFoncCourante = ligFoncCourante;
    }

    /**
     * Ajoute un paramètre dans la liste des paramètres lors de l'appel d'une fonction
     * @param p
     */
    public void addParam(Parametre p){
        param.add(p);
    }

    /**
     * Renvoie le nombre de paramètre il y a dans l'appel de fonction en cours
     * Puis clear la liste pour le prochain appel
     * @return
     */
    public int getNbParam(){
        int res = param.size();
        param.clear();
        return res;
    }
}
