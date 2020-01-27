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
        int deplacement = Tds.getInstance().getDeplacement(exp.getNom());
        String ecrire = "\t#ecrire " + exp.toString();
        ecrire += exp.toMIPS() + "\n";
        if(deplacement >= 0) {
            ecrire += "\tlw $a0, " + deplacement + "($s7)\n";
        }else{
            ecrire += "\tmove $a0, $v0\n";
        }
        ecrire += "\tli $v0, 1" + "\n\tsyscall\n";
        ecrire += "\n\t#retour à la ligne";
        ecrire += "\n\tli $v0, 4 \n" +
                  "\tla $a0, BackSlachN\n" +
                  "\tsyscall\n";
        return ecrire;
    }

}
