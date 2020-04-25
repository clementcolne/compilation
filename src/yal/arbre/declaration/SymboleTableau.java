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
    public SymboleTableau(String type, int n, int bloc, String etq, Expression e, Boolean estDansFonction, int idf) {
        super(type, n, bloc, etq);
        this.estDansFonction = estDansFonction;
        this.tailleTab = e;
        this.idfFonction = idf;
    }

    /**
     * Retourne l'identifiant de la fonction associée au symbole paramètre
     * @return l'identifiant de la fonction associée au symbole paramètre
     */
    @Override
    public int getIdfFonction() {
        return idfFonction;
    }

    @Override
    public boolean isTableau() {
        return true;
    }

    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        tailleTab.toMIPS();
        res.append("\tsw $v0, ($sp)\n");
        res.append("\tadd, $sp, $sp, -4\n");
        res.append("\taddu $sp, $sp, $v0\n\n");

        return res.toString();
    }
}
