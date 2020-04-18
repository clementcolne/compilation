package yal.arbre.instructions;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Idf;
import yal.exceptions.AnalyseSemantiqueException;

public class Affect extends Instruction {

    protected Expression partieD ;
    protected Expression partieTab;
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

    public Affect(Expression e, Expression e2, Idf id, int n){
        super(n);
        partieD = e;
        partieG = id;
        partieTab = e2;
    }

    /**
     * Vérifie s'il n'y a pas d'erreurs sémantiques
     */
    @Override
    public void verifier() {
        // On a un tableau à gauche
        if(partieTab != null){
            if(!Tds.getInstance().identifier(partieG.getNom(), noLigne,"tableau",0).getType().equals("tableau")){
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": tableau " + partieG.getNom() + " non déclarée");
                Tds.getInstance().add(a.getMessage());
            }
            if (!partieD.isBool()) {
                partieD.verifier();
            } else {
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un entier");
                Tds.getInstance().add(a.getMessage());
            }
        }else {
            if (!partieD.isBool() && !partieG.isBool()) {
                partieD.verifier();
                partieG.verifier();
            } else {
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un entier");
                Tds.getInstance().add(a.getMessage());
            }
        }

    }

    /**
     * Renvoie le code Mips
     * @return String
     */
    @Override
    public String toMIPS() {
        // Si tab1 = tab2 alors affectation par valeur
        // Si partieG tab[indice] alors récupérer le déplacement
        // Sinon, on garde ce qui est déjà écrit
        StringBuilder res = new StringBuilder();
        res.append("\t# " + partieG.getNom() + " = " + partieD.getNom() + "\n");
        res.append(partieD.toMIPS()+"\n");

        if(Tds.getInstance().identifierSymb(partieG.getNom(),0).equals("variable")) {
            res.append("\tsw $v0, " + Tds.getInstance().getDeplacement(partieG.getNom()) + "($s7)\n");
        }else{
            res.append("\tsw $v0, " + Tds.getInstance().getDeplacement(partieG.getNom()) + "($s2)\n");
        }
        return res.toString();
    }
}
