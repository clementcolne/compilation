package yal.arbre.expressions.unaire;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class NonExpression extends Expression {

    private Expression exp;
    private int etq;

    /**
     * Constructeur d'une expression
     * @param n int
     */
    public NonExpression(Expression e,int n) {
        super(n);
        exp = e;
    }

    /**
     * Retourne le nom de l'expression non
     * @return le nom de l'expression non
     */
    @Override
    public String getNom() {
        return "non "+exp.getNom();
    }

    /**
     * Vérifie l'expression
     */
    @Override
    public void verifier() {
        if(exp.isBool()){
            exp.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un booléen");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Retourne le code mips de l'expression non
     * @return le code mips de l'expression non
     */
    @Override
    public String toMIPS() {
        etq = Tds.getInstance().getIdfEtiquette();
        String res = "\t# Non"+exp.getNom()+"\n"+exp.toMIPS()+"\n";
        res += "\tla $t8, Vrai\n";
        res += "\tbeq $v0,$t8,si"+etq+"\n";   // teste si la condition est vraie
        res += "\tla $v0, Vrai\n";
        res += "\tjal suite"+etq+"\n";

        res += "si"+etq+":\n";
        res += "\tla $v0, Faux\n";
        res += "\tjal suite"+etq+"\n";
        res += "suite"+etq+":\n";
        return res;
    }

    /**
     * Retourne vrai
     * @return vrai
     */
    @Override
    public boolean isBool() {
        return true;
    }
}
