package yal.arbre.expressions.unaire;

import yal.arbre.expressions.Expression;

public class ExpressionParenthese extends Expression {

    private Expression exp;

    /**
     * Constructeur d'une expression
     * @param n int
     */
    public ExpressionParenthese(Expression e,int n) {
        super(n);
        exp = e;
    }

    /**
     * Retourne le nom de l'expression parenthésée
     * @return le nom de l'expression parenthésée
     */
    @Override
    public String getNom() {
        return "(" + exp.getNom() + ")";
    }

    /**
     * Vérifie l'expression
     */
    @Override
    public void verifier()  {
        exp.verifier();
    }

    /**
     * Retourne le code mips de l'expression
     * @return le code mips de l'expression
     */
    @Override
    public String toMIPS() {
        return exp.toMIPS();
    }

    /**
     * Retourne vrai si l'expression est booléenne, faux sinon
     * @return vrai si l'expression est booléenne, faux sinon
     */
    @Override
    public boolean isBool() {
        return exp.isBool();
    }
}
