package yal.arbre.expressions.unaire;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class ExpressionInverse extends Expression {

    private Expression exp;

    public ExpressionInverse(Expression e, int n){
        super(n);
        exp = e;
    }

    @Override
    public String getNom() {
        return "- "+exp.getNom();
    }

    @Override
    public String getOper() {
        return null;
    }

    @Override
    public void verifier() {
        if(!exp.isBool()){
            exp.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un entier");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        String res = "";
        res += exp.toMIPS() + "\n";
        res += "\t# Empiler $v0\n";
        res += "\tsw $v0,($sp)\n";
        res += "\tadd $sp,$sp,-4\n";

        res += "\t# Dépiler $v0\n";
        res += "\tadd $sp,$sp,4\n";
        res += "\tlw $t8,($sp)\n";

        res += "\tmul $v0, $t8, -1\n";
        res += "\tmflo $v0\n";
        return res;
    }
}
