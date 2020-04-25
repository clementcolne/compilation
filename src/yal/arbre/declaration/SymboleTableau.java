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

        res.append(tailleTab.toMIPS() + "\n");
        if(Tds.getInstance().identifier(etq, noLig,"tableau",0).getNoBloc() == 0) {
            res.append("\tsw $v0, " + deplacement + "($s7)\n");
            res.append("\tmove $sp, " + deplacement + "($s7)\n");
        }else{
            res.append("\tsw $v0, " + deplacement + "($s2)\n");
            res.append("\tmove $sp, " + deplacement + "($s2)\n");
        }
        res.append("\taddu $sp, $sp, $v0\n\n");

        return res.toString();
    }
}
