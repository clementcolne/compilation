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
        String ecrire = "\t#ecrire " + exp.toString();
        ecrire += exp.toMIPS() + "\n";
        ecrire += "\tmove $a0, $v0 # $a0 = $v0\n";
        ecrire += "\tli $v0, 1" + "\n\tsyscall\n";
        return ecrire;
    }

}
