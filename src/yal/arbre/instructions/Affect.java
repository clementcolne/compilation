package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Affect extends Expression {

    protected Expression partieG ;
    protected Idf partieD ;

    public Affect(Expression e, Idf id, int n){
        super(n);
        partieG = e;
        partieD = id;
    }
    @Override
    public void verifier() {
        partieG.verifier();
        partieD.verifier();
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
