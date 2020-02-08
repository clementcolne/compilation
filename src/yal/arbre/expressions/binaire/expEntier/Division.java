package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.expressions.Expression;

public class Division extends ExpressionEntier{
    /**
     * Constructeur d'une expression
     *
     * @param e1
     * @param e2
     * @param n  int
     */
    public Division(Expression e1, Expression e2, int n) {
        super(e1, e2, n);
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
        String res = "";
        res += expGauche.toMIPS() + "\n";
        res += "\tmove $t8, $v0\n";
        res += expDroite.toMIPS() + "\n";
        res += "\tdivu $v0, $t8, $v0\n";

        return res;
    }
}
