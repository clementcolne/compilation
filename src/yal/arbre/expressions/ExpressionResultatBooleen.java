package yal.arbre.expressions;

public class ExpressionResultatBooleen extends ExpressionBool {

    private Expression expGauche;
    private Expression expDroite;
    private String oper;

    /**
     * Constructeur d'une expression booléenne
     *
     * @param n int
     */
    public ExpressionResultatBooleen(Expression e1, Expression e2, String o, int n) {
        super(n);
        expGauche = e1;
        expDroite = e2;
        oper = o;
    }
}
