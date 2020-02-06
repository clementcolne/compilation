package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

public class NonExpression extends Expression {

    private Expression e;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public NonExpression(Expression e,int n) {
        super(n);
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void verifier() {
        if(e.isBool()){
            e.verifier();
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
