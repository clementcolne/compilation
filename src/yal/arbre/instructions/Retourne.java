package yal.arbre.instructions;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

public class Retourne extends Instruction {

    private Expression exp;
    private int noLig;

    /**
     * Constructeur d'une instruction
     *
     * @param n int
     */
    public Retourne(Expression e, int n) {
        super(n);
        exp = e;
        this.noLig = n;
    }

    @Override
    public void verifier() {
        if(Gestionnaire.getInstance().isInFonction()){
            Gestionnaire.getInstance().addRetourne();
            if(!exp.isBool()) {
                exp.verifier();
            }else{
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": il faut retourner un entier uniquement");
                Tds.getInstance().add(a.getMessage());
            }
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": retour en dehors d'une fonction");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("\t# retourne "+exp.getNom()+"\n");
        res.append(exp.toMIPS() + "\n"); // stocké dans $v0
        res.append("\tadd, $sp, $sp, 4\n"); // on libère l'espace mémoire de la pile
        res.append("\tlw $ra, ($sp)\n"); // on récupère l'adresse retour
        res.append("\tjr $ra\n");
        return res.toString();
    }
}
