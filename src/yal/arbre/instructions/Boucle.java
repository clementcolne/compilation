package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Boucle extends Instruction{

    private int etq;
    private ArbreAbstrait arbre;
    private Expression exp;

    /**
     * Constructeur d'une instruction
     * @param n int
     */
    public Boucle(Expression e, ArbreAbstrait a,int n) {
        super(n);
        arbre = a;
        exp = e;
    }

    /**
     * Vérifie l'expression de la boucle
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
     * Retourne le code mips de la boucle
     * @return le code mips de la boucle
     */
    @Override
    public String toMIPS() {
        etq = Tds.getInstance().getIdfEtiquette();
        Tds.getInstance().setCptProg();
        String res = "\t# Boucle\n";
        res += "loop"+etq+":\n";
        res += exp.toMIPS()+"\n";
        res += "\tla $t8, Faux\n";
        res += "\tbeq $v0,$t8, suite"+etq+"\n";
        res += arbre.toMIPS()+"\n";
        res += "\tj loop"+etq+"\n";
        res += "suite"+etq+":\n";

        return res;
    }
}
