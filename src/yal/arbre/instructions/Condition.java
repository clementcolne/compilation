package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Condition extends Instruction {

    private Expression exp;
    private ArbreAbstrait arbre1;
    private ArbreAbstrait arbre2;
    private int etq;

    /**
     * Constructeur d'une condition
     * @param e expression
     * @param n int
     * @param b1 arbre 1
     * @param b2 arbre 2
     */
    public Condition(Expression e, int n, ArbreAbstrait b1, ArbreAbstrait b2){
        super(n);
        exp = e;
        arbre1 = b1;
        arbre2 = b2;
    }

    /**
     * Constructeur d'une condition
     * @param e expression
     * @param n int
     * @param b arbre
     */
    public Condition(Expression e, int n, ArbreAbstrait b){
        super(n);
        exp = e;
        arbre1 = b;
    }

    /**
     * Constructeur d'une condition
     * @param e expression
     * @param n int
     */
    public Condition(Expression e, int n){
        super(n);
    }

    /**
     * Vérifie l'expression de la condition
     */
    @Override
    public void verifier() {
        if(exp.isBool()) {
            exp.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un booléen");
            Tds.getInstance().add(a.getMessage());
        }

    }

    /**
     * Retourne le code mips de la condition
     * @return le code mips de la condition
     */
    @Override
    public String toMIPS() {
        etq = Tds.getInstance().getIdfEtiquette();
        String res = "\t# Condition\n"+exp.toMIPS()+"\n";
        res += "\tla $t8, Vrai\n";
        res += "\tbeq $v0,$t8,si"+etq+"\n";   // teste si la condition est vraie
        if(arbre2 != null){
            Tds.getInstance().setCptProg();
            res += arbre2.toMIPS()+"\n";
        }
        res += "\tj suite"+etq+"\n";
        res += "si"+etq+":\n";
        if(arbre1 != null){
            Tds.getInstance().setCptProg();
            res += arbre1.toMIPS()+"\n";
        }
        res += "\tj suite"+etq+"\n";
        res += "suite"+etq+":\n";
        return res;
    }
}
