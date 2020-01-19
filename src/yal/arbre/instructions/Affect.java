package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Affect extends Expression {

    protected Expression exp ;
    protected String idf ;        // Voir si c'est utile

    public Affect(Expression e, String id, int n){
        super(n);
        exp = e;
        idf = id;
    }
    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
