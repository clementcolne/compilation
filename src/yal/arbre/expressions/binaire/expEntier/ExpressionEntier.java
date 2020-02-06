package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public abstract class ExpressionEntier extends Expression {

    protected Expression expDroite;
    protected Expression expGauche;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    protected ExpressionEntier(int n) {
        super(n);
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public void verifier() {
        if(!expDroite.isBool() && !expGauche.isBool()) {
            expDroite.verifier();
            expGauche.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un entier");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
