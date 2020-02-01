package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

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

    @Override
    public String getNom() {
        return super.getNom();
    }

    @Override
    public void verifier() {
        if((!expDroite.isBool() && !expGauche.isBool()) || (expDroite.isBool() && expGauche.isBool())) {
            expDroite.verifier();
            expGauche.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": les types attendus doivent être identiques");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        return super.toMIPS();
    }

    @Override
    public boolean isBool() {
        return true;
    }
}
