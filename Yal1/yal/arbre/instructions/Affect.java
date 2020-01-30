package yal.arbre.instructions;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;

public class Affect extends Expression {

    protected Expression partieD ;
    protected Idf partieG ;

    /**
     * Constructeur d'un affectation
     * @param e Expression
     * @param id Idf
     * @param n int
     */
    public Affect(Expression e, Idf id, int n){
        super(n);
        partieD = e;
        partieG = id;
    }

    /**
     * Vérifie s'il n'y a pas d'erreurs sémantiques
     */
    @Override
    public void verifier() {
        partieD.verifier();
        partieG.verifier();
    }

    /**
     * Renvoie le code Mips
     * @return String
     */
    @Override
    public String toMIPS() {
        String res = partieD.toMIPS();
        res += "\tsw $v0, " + Tds.getInstance().getDeplacement(partieG.getNom()) + "($s7)\n";
        return res;
    }

    /**
     * Renvoie l'affectation
     * @return String
     */
    @Override
    public String getNom() {
        return null;
    }
}
