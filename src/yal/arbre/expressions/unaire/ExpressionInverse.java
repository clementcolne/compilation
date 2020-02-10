package yal.arbre.expressions.unaire;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class ExpressionInverse extends Expression {

    private Expression exp;

    /**
     * Constructeur d'une ExpressionInverse
     * @param e expression
     * @param n int
     */
    public ExpressionInverse(Expression e, int n){
        super(n);
        exp = e;
    }

    /**
     * Retourne le nom de l'expression inverse
     * @return le nom de l'expression inverse
     */
    @Override
    public String getNom() {
        return "- "+exp.getNom();
    }

    /**
     * Vérifie l'expression de l'expression inverse
     */
    @Override
    public void verifier() {
        if(!exp.isBool()){
            exp.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un entier");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Retourne le code mips de l'expression inverse
     * @return le code mips de l'expression inverse
     */
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
