package yal.arbre;

public class Symbole {

    private String type;
    private int deplacement;

    /**
     * Constructeur d'un symbole défini par son type et son déplacement
     * @param type String qui décrit le type du Symbole
     */
    public Symbole(String type) {
        this.type = type;
        deplacement = 0;
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

}
