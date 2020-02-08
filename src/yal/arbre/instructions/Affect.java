package yal.arbre.instructions;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;
import yal.exceptions.AnalyseSemantiqueException;

public class Affect extends Instruction {

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
    public void verifier(){
        if(!partieD.isBool() && !partieG.isBool()) {
            partieD.verifier();
            partieG.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un entier");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Renvoie le code Mips
     * @return String
     */
    @Override
    public String toMIPS() {
        String res = "\t# " + partieG.getNom() + " = " + partieD.getNom() + "\n";
        res += partieD.toMIPS()+"\n";
        if(partieD.isConstante()) {
            res += "\tsw $v0, " + Tds.getInstance().getDeplacement(partieG.getNom()) + "($s7)\n";
        }else{
            res += "\tsw $v0, " + Tds.getInstance().getDeplacement(partieG.getNom()) + "($s7)\n";
        }
        return res;
    }
}
