package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

public class ExpressionBoolEntierBooleen extends ExpressionBool {

    private Expression expGauche;
    private Expression expDroite;
    private String oper;

    public ExpressionBoolEntierBooleen(Expression e1, Expression e2, String o, int n){
        super(n);
        expGauche = e1;
        expDroite = e2;
        oper = o;
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

    @Override
    public boolean isBool() {
        return true;
    }
}
