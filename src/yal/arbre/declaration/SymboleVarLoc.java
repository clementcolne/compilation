package yal.arbre.declaration;

public class SymboleVarLoc extends Symbole {

    private int idfFonction;

    /**
     * Constructeur d'un symbole défini par son type et son déplacement
     *
     * @param type String qui décrit le type du Symbole
     * @param n
     * @param bloc
     * @param etq
     */
    public SymboleVarLoc(String type, int n, int bloc, String etq, int idf) {
        super(type, n, bloc, etq);
        this.idfFonction = idf;
    }

    /**
     * Retourne vrai si le symbole est une variable locale, faux sinon
     * @return vrai si le symbole est une variable locale, faux sinon
     */
    @Override
    public boolean isVariableLocale() {
        return true;
    }

    /**
     * Retourne l'identifiant de la fonction associée au symbole de la variable locale
     * @return l'identifiant de la fonction associée au symbole de la variable locale
     */
    @Override
    public int getIdfFonction() {
        return idfFonction;
    }
}
