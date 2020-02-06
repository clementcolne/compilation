package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Condition extends Instruction {

    public Condition(Expression exp, int n, BlocDInstructions b){
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
