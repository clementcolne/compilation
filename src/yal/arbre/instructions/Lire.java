package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Lire extends Instruction {

    protected Expression exp ;

    public Lire(Expression e, int n){
        super(n);
        exp = e;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
