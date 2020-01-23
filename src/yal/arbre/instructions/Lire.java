package yal.arbre.instructions;

import yal.arbre.expressions.Idf;

public class Lire extends Instruction {

    protected Idf idf ;

    public Lire(Idf i, int n){
        super(n);
        idf = i;
    }

    @Override
    public void verifier() {
        idf.verifier();
    }

    @Override
    public String toMIPS() {
        String result = "\t# lecture  entier (syscall 5)\n" +
                "\taddi $v0, $zero, 5\n" +
                "\tsyscall # $v0 contient le résultat de l'entrée de l'utilisateur\n";
        return result;
    }
}
