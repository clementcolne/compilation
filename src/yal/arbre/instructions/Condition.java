package yal.arbre.instructions;

import yal.arbre.expressions.Idf;

public class Condition extends Instruction {

    public Condition(String exp, Idf i, int n){
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
