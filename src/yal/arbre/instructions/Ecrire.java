package yal.arbre.instructions;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

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
        if(Tds.getInstance().identifier(exp.getNom(), noLigne,"tableau",0).getType().equals("tableau")){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": "+exp.getNom()+" doit être un entier ou un booléen");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Retourne le code mips d'écrire
     * @return le code mips d'écrire
     */
    @Override
    public String toMIPS() {
        StringBuilder ecrire = new StringBuilder();
        ecrire.append("\t#ecrire " + exp.getNom() + "\n");
        ecrire.append(exp.toMIPS() + "\n");
        ecrire.append("\tmove $a0, $v0\n"); // $v0 dans $a0
        if(!exp.isBool()) {
            ecrire.append("\tli $v0, 1" + "\n\tsyscall\n\n");
        }else{
            ecrire.append("\tli $v0, 4" + "\n\tsyscall\n\n");
        }
        ecrire.append("\t#retour à la ligne\n");
        ecrire.append("\tli $v0, 4 \n" +
                  "\tla $a0, BackSlachN\n" +
                  "\tsyscall\n");
        return ecrire.toString();
    }

}
