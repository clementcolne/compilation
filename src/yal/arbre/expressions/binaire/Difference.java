package yal.arbre.expressions.binaire;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Difference extends Expression {

    private Expression expGauche;
    private Expression expDroite;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public Difference(Expression e1, Expression e2, int n) {
        super(n);
        expDroite = e2;
        expGauche = e1;
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public void verifier() {
        if((!expDroite.isBool() && !expGauche.isBool()) || (expDroite.isBool() && expGauche.isBool())) {
            expDroite.verifier();
            expGauche.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu doit être identique");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
