package yal.arbre.declaration;

public class SymboleParametre extends Symbole {

    private int idfFonction;

    /**
     * Constructeur d'un symbole défini par son type et son déplacement
     *
     * @param type String qui décrit le type du Symbole
     * @param n
     * @param bloc
     * @param etq
     */
    public SymboleParametre(String type, int n, int bloc, String etq, int idf) {
        super(type, n, bloc, etq);
        this.idfFonction = idf;
    }

    /**
     * Retourne vrai si le symbole est un paramètre, faux sinon
     * @return vrai si le symbole est un paramètre, faux sinon
     */
    @Override
    public boolean isParametre() {
        return true;
    }

    /**
     * Retourne l'identifiant de la fonction associée au symbole paramètre
     * @return l'identifiant de la fonction associée au symbole paramètre
     */
    @Override
    public int getIdfFonction() {
        return idfFonction;
    }

}
