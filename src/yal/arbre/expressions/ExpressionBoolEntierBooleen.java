package yal.arbre.expressions;

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

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
