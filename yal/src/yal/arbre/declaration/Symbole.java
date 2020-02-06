package yal.arbre.declaration;

public class Symbole {

    private String type;
    private int deplacement;
    private int noLig;

    /**
     * Constructeur d'un symbole défini par son type et son déplacement
     * @param type String qui décrit le type du Symbole
     */
    public Symbole(String type, int n) {
        this.type = type;
        this.deplacement = 0;
        this.noLig = n;
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
}
