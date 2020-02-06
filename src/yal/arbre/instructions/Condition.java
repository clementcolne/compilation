package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;

public class Condition extends Instruction {

    public Condition(Expression exp, int n, ArbreAbstrait b){
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
