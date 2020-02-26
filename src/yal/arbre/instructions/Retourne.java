package yal.arbre.instructions;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

public class Retourne extends Instruction {

    private Expression exp;

    /**
     * Constructeur d'une instruction
     *
     * @param n int
     */
    public Retourne(Expression e, int n) {
        super(n);
        exp = e;
    }

    @Override
    public void verifier() {
        System.out.println("retourne"+Tds.getInstance().getCptErreur());
        if(Gestionnaire.getInstance().isInFonction()){
            Gestionnaire.getInstance().addRetourne();
            exp.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": retour en dehors d'une fonction");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
