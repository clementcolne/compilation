package yal.arbre.expressions.binaire;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Egalite extends Expression {

    private Expression expDroite;
    private Expression expGauche;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    protected Egalite(int n) {
        super(n);
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
