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

    public Condition(Expression e, int n, ArbreAbstrait b1, ArbreAbstrait b2){
        super(n);
        exp = e;
        arbre1 = b1;
        arbre2 = b2;
        etq = Tds.getInstance().getIdfCondition();
        Tds.getInstance().setIdfCondition();
    }

    public Condition(Expression e, int n, ArbreAbstrait b){
        super(n);
        exp = e;
        arbre1 = b;
    }

    public Condition(Expression e, int n){
        super(n);
    }

    @Override
    public void verifier() {
        if(exp.isBool()) {
            exp.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un booléen");
            Tds.getInstance().add(a.getMessage());
        }

    }

    @Override
    public String toMIPS() {
        String res = exp.toMIPS();
        switch (exp.getOper()){
            case "==":
                res += "\tbeq $v0,$t8,si"+etq+"\n";
                break;
            case "!=":
                res += "\tbne $v0,$t8,si"+etq+"\n";
                break;
            case "<":
                res += "\tblt $v0,$t8,si"+etq+"\n";
                break;
            case ">":
                res += "\tbgt $v0,$t8,si"+etq+"\n";
                break;
            default:
                break;
        }
        if(arbre2 != null){
            Tds.getInstance().setCptProg();
            res += arbre2.toMIPS();
        }
        res += "si"+etq+":\n";
        if(arbre1 != null){
            Tds.getInstance().setCptProg();
            res += arbre1.toMIPS();
        }
        res += "\tjal suite"+etq+"\n";
        res += "suite"+etq+":\n";
        return res;
    }
}
