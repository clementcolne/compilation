package yal.arbre.instructions;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Affect extends Expression {

    protected Expression partieD ;
    protected Idf partieG ;

    public Affect(Expression e, Idf id, int n){
        super(n);
        partieD = e;
        partieG = id;
    }
    @Override
    public void verifier() {
        partieD.verifier();
        partieG.verifier();
    }

    @Override
    public String toMIPS() {
        String res = partieD.toMIPS();
        res += "\tsw $v0, " + Tds.getInstance().getDeplacement(partieG.getNom()) + "($s7)\n";
        return res;
    }

    @Override
    public String getNom() {
        return null;
    }
}
