package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

public class Division extends ExpressionEntier{
    /**
     * Constructeur d'une expression
     * @param e1 expression gauche
     * @param e2 expression droite
     * @param n  int
     */
    public Division(Expression e1, Expression e2, int n) {
        super(e1, e2, n);
    }

    /**
     * Retourne le nom de la division
     * @return le nom de la division
     */
    @Override
    public String getNom() {
        return expGauche.getNom()+" / "+expDroite.getNom();
    }
    /**
     * Vérifie qu'une division par 0 n'est pas explicitement demandée
     */
    @Override
    public void verifier() {
        super.verifier();
        // division par 0 explicite
        if(expDroite.getNom().equals("0")){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": division par 0");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Retourne le code mips de la division
     * @return le code mips de la division
     */
    @Override
    public String toMIPS() {
        int etq = Gestionnaire.getInstance().getIdfEtiquette();
        StringBuilder res = new StringBuilder();
        res.append(expGauche.toMIPS() + "\n");
        res.append("\t# Empiler $v0\n");
        res.append("\tsw $v0,($sp)\n");
        res.append("\tadd $sp,$sp,-4\n");
        res.append(expDroite.toMIPS() + "\n");
        res.append("\t# Dépiler $v0\n");
        res.append("\tadd $sp,$sp,4\n");
        res.append("\tlw $t8,($sp)\n");
        res.append("\t# évaluation de l'opérande droite de la division\n");
        res.append("\tbeqz $v0, erreurDivisionZero\n");
        res.append("\tdiv $v0, $t8, $v0\n");
        res.append("\tj suite"+etq+"\n");
        res.append("suite"+etq+":\n");

        return res.toString();
    }
}
