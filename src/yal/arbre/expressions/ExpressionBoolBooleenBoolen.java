package yal.arbre.expressions;

public class ExpressionBoolBooleenBoolen extends ExpressionBool {

    private ExpressionBool expGauche;
    private ExpressionBool expDroite;
    private String oper;

    public ExpressionBoolBooleenBoolen(ExpressionBool e1, ExpressionBool e2, String o, int n){
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
