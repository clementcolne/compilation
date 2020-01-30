package yal.arbre.expressions;

public class ExpressionACalculer extends Expression{

    private Expression expGauche;
    private Expression expDroite;
    private String oper;

    public ExpressionACalculer(Expression e1, Expression e2, String oper, int n){
        super(n);
        expGauche = e1;
        expDroite = e2;
        this.oper = oper;
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
