package yal.arbre.expressions;

import yal.arbre.declaration.Tds;

public class TailleTableau extends Expression {
    /**
     * Constructeur d'une taille de tableau
     * @param n int
     */
    public TailleTableau(Idf nom, int n) {
        super(n);
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return "\tli $v0, " + Tds.getInstance().identifier(nom, noLigne, "tableau", 0).getTailleTableau() + "\n";
    }
}
