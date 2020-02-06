package yal.arbre.instructions;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    /**
     * Constructeur de Ecrire
     * @param e Expression
     * @param n int
     */
    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    /**
     * Vérifie s'il n'y a pas d'erreurs sémantiques
     */
    @Override
    public void verifier() {
        exp.verifier();
    }

    /**
     * Renvoie le code Mips
     * @return String
     */
    @Override
    public String toMIPS() {
        String ecrire = "\t#ecrire " + exp.getNom() + "\n";
        ecrire += exp.toMIPS() + "\n";
        ecrire += "\tmove $a0, $v0\n"; // $v0 dans $a0
        ecrire += "\tli $v0, 1" + "\n\tsyscall\n\n";
        ecrire += "\t#retour à la ligne\n";
        ecrire += "\tli $v0, 4 \n" +
                  "\tla $a0, BackSlachN\n" +
                  "\tsyscall\n";
        return ecrire;
    }

}
