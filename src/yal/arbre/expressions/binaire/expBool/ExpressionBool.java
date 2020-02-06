package yal.arbre.expressions.binaire.expBool;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public abstract class ExpressionBool extends Expression {

    protected Expression expGauche;
    protected Expression expDroite;

    /**
     * Constructeur d'une expression booléenne
     *
     * @param n int
     */
    protected ExpressionBool(int n) {
        super(n);
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public void verifier() {
        if(expDroite.isBool() && expGauche.isBool()) {
            expDroite.verifier();
            expGauche.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un booléen");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        return null;
    }


}
