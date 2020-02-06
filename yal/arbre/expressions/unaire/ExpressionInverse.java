package yal.arbre.expressions.unaire;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class ExpressionInverse extends Expression {

    private Expression e;

    public ExpressionInverse(Expression e, int n){
        super(n);
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void verifier() {
        if(!e.isBool()){
            e.verifier();
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
