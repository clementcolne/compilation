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
    public void verifier() {
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
        StringBuilder res = new StringBuilder();
        res.append("\t# " + partieG.getNom() + " = " + partieD.getNom() + "\n");
        res.append(partieD.toMIPS()+"\n");
       /* if(partieD.isConstante()) {  //TODO vérifier la condition
            res.append("\tsw $v0, " + Tds.getInstance().getDeplacement(partieG.getNom()) + "($s7)\n");
        }else{*/
            res.append("\tsw $v0, " + Tds.getInstance().getDeplacement(partieG.getNom()) + "($s7)\n");
       // }
        return res.toString();
    }
}
