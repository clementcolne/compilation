package yal.arbre.declaration;

public class SymboleVariable extends Symbole {
    /**
     * Constructeur d'un symbole défini par son type et son déplacement
     *
     * @param type String qui décrit le type du Symbole
     * @param n
     * @param bloc
     * @param etq
     */
    public SymboleVariable(String type, int n, int bloc, String etq) {
        super(type, n, bloc, etq);
    }

    /**
     * Retourne vrai si le symbole est une variable, faux sinon
     * @return vrai si le symbole est une variable, faux sinon
     */
    @Override
    public boolean isVariable() {
        return true;
    }

}
