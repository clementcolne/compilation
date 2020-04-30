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
        // On a un tableau à gauche avec un indice
        if(partieTab != null){
            if(!partieTab.isBool()) {
                partieTab.verifier();
            }else{
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": il faut un indice de type entier pour "+partieG.getNom());
                Tds.getInstance().add(a.getMessage());
            }
            if(!Tds.getInstance().identifier(partieG.getNom(), noLigne,"tableau",0).getType().equals("tableau")){
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": tableau " + partieG.getNom() + " non déclarée");
                Tds.getInstance().add(a.getMessage());
            }
            // La partieD ne doit être ni un booléen ni un Idf d'un tableau
            if (!partieD.isBool()) {
                partieD.verifier();
            } else {
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un entier pour "+partieD.getNom());
                Tds.getInstance().add(a.getMessage());
            }
        }else {
            // On a un simple idf à gauche
            // C'est un idf de tableau
            if(Tds.getInstance().identifier(partieG.getNom(), noLigne,"tableau",0).getType().equals("tableau")){
                if((!Tds.getInstance().identifier(partieD.getNom(), noLigne,"tableau",0).getType().equals("tableau") && partieD.isIdf()) || !partieD.isIdf()){
                    AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": "+partieD.getNom()+" doit être un tableau");
                    Tds.getInstance().add(a.getMessage());
                }
            }
            else if (!partieD.isBool() && !partieG.isBool()) {
                if(Tds.getInstance().identifier(partieD.getNom(), noLigne,"tableau",0).getType().equals("tableau") && partieD.isIdf()){
                    AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": il faut un indice pour le tableau "+partieD.getNom());
                    Tds.getInstance().add(a.getMessage());
                }else {
                    partieD.verifier();
                    partieG.verifier();
                }
            }else{
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
        // Si tab1 = tab2 alors
            // vérification mips
            // affectation par valeur
        // Sinon si partieG tab[indice] alors
            // vérification pas hors bornes
            // récupérer le déplacement
        // Sinon, on garde ce qui est déjà écrit
        StringBuilder res = new StringBuilder();
        res.append("\t# " + partieG.getNom() + " = " + partieD.getNom() + "\n");


        // On a un appel de tableau à gauche
        if(partieTab != null){
            res.append(partieTab.toMIPS());
            res.append("\n\t# Tableau à gauche\n");
            res.append("\tmul $v0, $v0, -4\n");
            res.append("\tmflo $v0\n");
            // Dans $t8 il y a le déplacement du tableau
            // Dans $v0 il y a le déplacement dans le tableau, par rapport à la case d'indice 0
            int deplacement = Tds.getInstance().identifier(partieG.getNom(), noLigne,"tableau",0).getDeplacement() -4;
            res.append("\tlw $t8, " + deplacement + "($s7)\n");
            // se déplacer jusqu'au pointeur
            res.append("\tadd $t8, $t8, $v0\n");
            res.append(partieD.toMIPS()+"\n");
            res.append("\tsw $v0, ($t8)\n");
        }else {
            res.append("\n\t# Idf seul à gauche\n");
            res.append(partieD.toMIPS() + "\n");
            if (Tds.getInstance().identifierSymb(partieG.getNom(), 0).equals("variable")) {
                res.append("\tsw $v0, " + Tds.getInstance().getDeplacement(partieG.getNom()) + "($s7)\n");
            } else {
                res.append("\tsw $v0, " + Tds.getInstance().getDeplacement(partieG.getNom()) + "($s2)\n");
            }
        }
        return res.toString();
    }
}
