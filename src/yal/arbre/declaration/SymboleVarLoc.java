package yal.arbre.declaration;

public class SymboleVarLoc extends Symbole {

    private String fonction;

    /**
     * Constructeur d'un symbole défini par son type et son déplacement
     *
     * @param type String qui décrit le type du Symbole
     * @param n
     * @param bloc
     * @param etq
     */
    public SymboleVarLoc(String type, int n, int bloc, String etq, String fonction) {
        super(type, n, bloc, etq);
        this.fonction = fonction;
    }

    /**
     * Retourne vrai si le symbole est une variable locale, faux sinon
     * @return vrai si le symbole est une variable locale, faux sinon
     */
    @Override
    public boolean isVariableLocale() {
        return true;
    }

}
