package yal.arbre.expressions.binaire;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Difference extends Expression {

    private Expression expGauche;
    private Expression expDroite;
    private int etq;

    /**
     * Constructeur d'une expression
     * @param n int
     */
    public Difference(Expression e1, Expression e2, int n) {
        super(n);
        expDroite = e2;
        expGauche = e1;
    }

    /**
     * Retourne le nom de l'expression différence
     * @return le nom de l'expression différence
     */
    @Override
    public String getNom() {
        return expGauche.getNom()+" != "+expDroite.getNom();
    }

    /**
     * Vérifie les expressions (gauche et droite) de l'expression différence
     */
    @Override
    public void verifier() {
        if((!expDroite.isBool() && !expGauche.isBool()) || (expDroite.isBool() && expGauche.isBool())) {
            expDroite.verifier();
            expGauche.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu doit être identique");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Retourne le code mips de l'expression différence
     * @return le code mips de l'expression différence
     */
    @Override
    public String toMIPS() {
        etq = Tds.getInstance().getIdfEtiquette();
        StringBuilder res = new StringBuilder();
        res.append(expGauche.toMIPS() + "\n");
        res.append("\t# Empiler $v0\n");
        res.append("\tsw $v0,($sp)\n");
        res.append("\tadd $sp,$sp,-4\n");

        res.append(expDroite.toMIPS() + "\n");
        res.append("\t# Dépiler $v0\n");
        res.append("\tadd $sp,$sp,4\n");
        res.append("\tlw $t8,($sp)\n");

        res.append("\t# Affectation de la valeur booléenne\n");
        res.append("\tbne $v0,$t8,si"+etq+"\n");
        res.append("\tla $v0, Faux\n");
        res.append("\tj suite"+etq+"\n");
        res.append("si"+etq+":\n");
        res.append("\tla $v0, Vrai\n");
        res.append("\tj suite"+etq+"\n");
        res.append("suite"+etq+":\n");

        return res.toString();
    }

    /**
     * Retourne vrai
     * @return vrai
     */
    @Override
    public boolean isBool() {
        return true;
    }
}
