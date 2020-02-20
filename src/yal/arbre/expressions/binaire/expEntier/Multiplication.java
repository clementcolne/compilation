package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.expressions.Expression;

public class Multiplication extends ExpressionEntier{
    /**
     * Constructeur d'une expression
     * @param e1 expression gauche
     * @param e2 expression droite
     * @param n  int
     */
    public Multiplication(Expression e1, Expression e2, int n) {
        super(e1, e2, n);
    }

    /**
     * Retourne le nom de la multiplication
     * @return le nom de la multiplication
     */
    @Override
    public String getNom() {
        return expGauche.getNom()+" * "+expDroite.getNom();
    }
    /**
     * Retourne le code mips de la multiplication
     * @return le code mips de la multiplication
     */
    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append(expGauche.toMIPS() + "\n");
        res.append("\t# Empiler $v0\n");
        res.append("\tsw $v0,($sp)\n");
        res.append("\tadd $sp,$sp,-4\n");
        res.append(expDroite.toMIPS() + "\n");
        res.append("\t# Dépiler $v0\n");
        res.append("\tadd $sp,$sp,4\n");
        res.append("\tlw $t8,($sp)\n");

        res.append("\tmul $v0, $t8, $v0\n");
        res.append("\tmflo $v0\n");
        return res.toString();
    }
}
