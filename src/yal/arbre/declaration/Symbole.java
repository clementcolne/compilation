package yal.arbre.declaration;

import java.util.Objects;

public class Symbole {

    private String type;
    private int deplacement;
    private int noLig;
    private int noBloc;
    private String etq;
    protected int nbParametres;

    /**
     * Constructeur d'un symbole défini par son type et son déplacement
     * @param type String qui décrit le type du Symbole
     */
    public Symbole(String type, int n, int bloc, String etq) {
        this.type = type;
        this.deplacement = 0;
        this.noLig = n;
        this.noBloc = bloc;
        this.etq = etq;
    }

    /**
     * Fixe la valeur du déplacement du Symbole
     * @param deplacement valeur entier du déplacement
     */
    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    /**
     * Retourne la valeur du déplacement
     * @return la valeur du déplacement
     */
    public int getDeplacement() {
        return deplacement;
    }

    /**
     * Renvoie le type du symbole
     * @return string
     */
    public String getType() {
        return type;
    }

    /**
     * Renvoie la ligne du symbole
     * @return int
     */
    public int getNoLig() {
        return noLig;
    }

    /**
     * Renvoie le bloc dans lequel la variable est déclarée
     * @return int
     */
    public int getNoBloc() {
        return noBloc;
    }

    /**
     * Fixe l'étiquette du symbole avec l'étiquette en paramètre
     * @param e l'étiquette du symbole avec l'étiquette en paramètre
     */
    public void setEtq(String e){
        this.etq = e;
    }

    /**
     * Retourne l'étiquette du symbole
     * @return l'étiquette du symbole
     */
    public String getEtq() {
        return etq;
    }

    /**
     * Retourne vrai si le symbole est une fonction, faux sinon
     * @return vrai si le symbole est une fonction, faux sinon
     */
    public boolean isFonction() {
        return false;
    }

    /**
     * Retourne vrai si le symbole est une variable locale, faux sinon
     * @return vrai si le symbole est une variable locale, faux sinon
     */
    public boolean isVariableLocale() {
        return false;
    }

    /**
     * Retourne vrai si le symbole est une variable, faux sinon
     * @return vrai si le symbole est une variable, faux sinon
     */
    public boolean isVariable() {
        return false;
    }

    /**
     * Retourne vrai si le symbole est un paramètre, faux sinon
     * @return vrai si le symbole est un paramètre, faux sinon
     */
    public boolean isParametre() {
        return false;
    }

    /**
     * Retourne l'identifiant de la fonction associée au symbole paramètre
     * @return l'identifiant de la fonction associée au symbole paramètre
     */
    public int getIdfFonction() {
        return -1;
    }

    /**
     * Renvoie le nombre de paramètre si c'est une fonction
     * @return
     */
    public int getNbParametres(){
        return nbParametres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbole)) return false;
        Symbole symbole = (Symbole) o;
        return getNoBloc() == symbole.getNoBloc() &&
                getNbParametres() == symbole.getNbParametres() &&
                Objects.equals(getType(), symbole.getType());
    }

}
