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
     * Retourne le code mips de lire
     * @return le code mips de lire
     */
    @Override
    public String toMIPS() {
        StringBuilder result = new StringBuilder();
        result.append("\t# lecture  entier (syscall 5)\n" +
                "\taddi $v0, $zero, 5\n" +
                "\tsyscall # $v0 contient le résultat de l'entrée de l'utilisateur\n");
        result.append("\tsw $v0, " + Tds.getInstance().getDeplacement(idf.getNom()) + "($s7)\n");
        return result.toString();
    }
}
