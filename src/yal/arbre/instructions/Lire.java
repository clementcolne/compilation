package yal.arbre.instructions;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Idf;

public class Lire extends Instruction {

    protected Idf idf ;

    /**
     * Constructeur de Lire
     * @param i Idf
     * @param n int
     */
    public Lire(Idf i, int n){
        super(n);
        idf = i;
    }

    /**
     * Vérifie s'il n'y a pas d'erreurs sémantiques
     */
    @Override
    public void verifier() {
        idf.verifier();
    }

    /**
     * Renvoie le code Mips
     * @return String
     */
    @Override
    public String toMIPS() {
        String result = "\t# lecture  entier (syscall 5)\n" +
                "\taddi $v0, $zero, 5\n" +
                "\tsyscall # $v0 contient le résultat de l'entrée de l'utilisateur\n";
        result += "\tsw $v0, " + Tds.getInstance().getDeplacement(idf.getNom()) + "($s7)\n";
        return result;
    }
}
