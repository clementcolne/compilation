package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.expressions.Expression;

public class Addition extends ExpressionEntier {
    /**
     * Constructeur d'une expression
     * @param n int
     */
    public Addition(Expression e1, Expression e2, int n) {
        super(e1,e2,n);
    }

    /**
     * Retourne le nom de l'addition
     * @return le nom de l'addition
     */
    @Override
    public String getNom() {
        return expGauche.getNom()+" + "+expDroite.getNom();
    }
    /**
     * Retourne le code mips de l'addition
     * @return le code mips de l'addition
     */
    @Override
    public String toMIPS() {
        String res = expGauche.toMIPS() + "\n";
        res += "\t# Empiler $v0\n";
        res += "\tsw $v0,($sp)\n";
        res += "\tadd $sp,$sp,-4\n";
        res += expDroite.toMIPS() + "\n";
        res += "\t# Dépiler $v0\n";
        res += "\tadd $sp,$sp,4\n";
        res += "\tlw $t8,($sp)\n";

        res += "\tadd $v0, $t8, $v0\n";

        return res;
    }
}
