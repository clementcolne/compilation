package yal.arbre.declaration;

import yal.arbre.expressions.Expression;

public class SymboleTableau extends Symbole {
    /**
     * Constructeur d'un symbole défini par son type et son déplacement
     * @param type String qui décrit le type du Symbole
     * @param n
     * @param bloc
     * @param etq
     * @param e
     * @param estDansFonction
     */
    public SymboleTableau(String type, int n, int bloc, String etq, Expression e, Boolean estDansFonction) {
        super(type, n, bloc, etq);
    }
}
