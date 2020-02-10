package yal.arbre.expressions.binaire.expBool;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;

public class BoolOu extends ExpressionBool{
    /**
     * Constructeur d'une expression booléenne
     *
     * @param n int
     */
    public BoolOu(Expression e1, Expression e2, int n) {
        super(e1,e2,n);
    }

    public String getNom() {
        return expGauche.getNom()+" et "+expDroite.getNom();
    }

    @Override
    public String toMIPS() {
        int etq1 = Tds.getInstance().getIdfEtiquette();
        int etq2 = Tds.getInstance().getIdfEtiquette();

        String res = expGauche.toMIPS() + "\n";
        res += "\t# Empiler $v0\n";
        res += "\tsw $v0,($sp)\n";
        res += "\tadd $sp,$sp,-4\n";

        res += expDroite.toMIPS() + "\n";
        res += "\tla $t8, Faux\n";


        res += "\tbeq $v0, $t8, si"+etq1+"\n";
        res += "\tla $v0, Vrai\n";
        res += "\tjal suite"+etq1+"\n";
        res += "\tsi"+etq1+":\n";
        res += "\t# Dépiler $v0\n";
        res += "\tadd $sp,$sp,4\n";
        res += "\tlw $t8,($sp)\n";
        res += "\tla $v0, Faux\n";
        res += "\tbeq $t8, $v0, si"+etq2+"\n";
        res += "\tla $v0, Vrai\n";
        res += "\tjal suite"+etq1+"\n";
        res += "\tsi"+etq2+":\n";
        res += "\tla $v0, Faux\n";
        res += "\tjal suite"+etq1+"\n";   // peut-être pas utile
        res += "\tsuite"+etq1+":\n";

        return res;
    }

    @Override
    public boolean isBool() {
        return true;
    }
}
