package yal.arbre.declaration;

public class Entree {

    private String nom;

    /**
     * Constructeur d'une Entree
     * @param nom String
     */
    public Entree(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoie le nom de l'Entree
     * @return String
     */
    public String getNom() {
        return nom;
    }

}
