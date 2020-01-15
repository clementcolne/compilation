package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        String ecrire = "\t#ecrire "+exp+"\n";
        ecrire += "\tli $v0, 1\n\tli $a0," +exp+"\n\tsyscall\n";
        return ecrire;
    }

}
