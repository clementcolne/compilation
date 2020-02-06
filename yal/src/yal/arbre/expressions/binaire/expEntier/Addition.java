package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.expressions.Expression;

public class Addition extends ExpressionEntier {
    /**
     * Constructeur d'une expression
     * @param n int
     */
    public Addition(Expression e1, Expression e2, int n) {
        super(e1,e2,n);
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
        res += "\tadd $v0, $t8, $v0\n";

        return res;
    }
}
