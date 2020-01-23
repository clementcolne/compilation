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
        expr.verifier();
    }

    @Override
    public String toMIPS() {
        String result = "\t# lecture  entier (syscall 5)\n" +
                "\taddi $v0, $zero, 5\n" +
                "\tsyscall # $v0 contient le résultat de l'entrée de l'utilisateur\n";
        return result;
    }
}
