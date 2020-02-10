package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public abstract class ExpressionEntier extends Expression {

    protected Expression expDroite;
    protected Expression expGauche;

    /**
     * Constructeur d'une expression
     * @param n int
     */
    protected ExpressionEntier(Expression e1, Expression e2, int n) {
        super(n);
        expDroite = e2;
        expGauche = e1;
    }

    /**
     * Retourne le nom de l'expression
     * @return le nom de l'expression
     */
    @Override
    public String getNom() {
        return null;
    }

    /**
     * Vérifie les 2 expressions (gauche et droite) de l'expression
     */
    @Override
    public void verifier() {
        if(!expDroite.isBool() && !expGauche.isBool()) {
            expDroite.verifier();
            expGauche.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un entier");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Renvoie le résultat entier de l'expression pour vérifier si c'est une division par 0
     * @return int
     */
    public int getNombre(){
        return 0;
    }

    /**
     * Retourne le code mips de l'expression
     * @return le code mips de l'expression
     */
    @Override
    public String toMIPS() {
        return null;
    }
}
