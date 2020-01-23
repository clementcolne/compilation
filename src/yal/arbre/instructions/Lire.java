package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Lire extends Instruction {

    protected Expression expr ;

    public Lire(Expression e, int n){
        super(n);
        expr = e;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
