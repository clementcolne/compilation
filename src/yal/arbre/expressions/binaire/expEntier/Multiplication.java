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
     * Renvoie le résultat entier de l'expression pour vérifier si c'est une division par 0
     * @return int
     */
    @Override
    public int getNombre(){
        return expGauche.getNombre() * expDroite.getNombre();
    }

    /**
     * Retourne le code mips de la multiplication
     * @return le code mips de la multiplication
     */
    @Override
    public String toMIPS() {
        String res = "";
        res += expGauche.toMIPS() + "\n";
        res += "\t# Empiler $v0\n";
        res += "\tsw $v0,($sp)\n";
        res += "\tadd $sp,$sp,-4\n";
        res += expDroite.toMIPS() + "\n";
        res += "\t# Dépiler $v0\n";
        res += "\tadd $sp,$sp,4\n";
        res += "\tlw $t8,($sp)\n";

        res += "\tmul $v0, $t8, $v0\n";
        res += "\tmflo $v0\n";
        return res;
    }
}
