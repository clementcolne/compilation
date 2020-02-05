package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Condition extends Instruction {

    public Condition(Expression exp, Idf i, int n){
        super(n);
    }

    public Condition(Expression exp, int n){
        super(n);
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
