package yal.arbre.expressions;

public class ExpressionInverse extends Expression {

    public ExpressionInverse(String e, int n){
        super(n);
        nom = e;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
