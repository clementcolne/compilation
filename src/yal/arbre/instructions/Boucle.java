package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

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
        etq = Gestionnaire.getInstance().getIdfEtiquette();
        Gestionnaire.getInstance().setCptProg();
        StringBuilder res = new StringBuilder();
        res.append("\t# Boucle\n");
        res.append("loop"+etq+":\n");
        res.append(exp.toMIPS()+"\n");
        res.append("\tla $t8, Faux\n");
        res.append("\tbeq $v0,$t8, suite"+etq+"\n");
        res.append(arbre.toMIPS()+"\n");
        res.append("\tj loop"+etq+"\n");
        res.append("suite"+etq+":\n");

        return res.toString();
    }
}
