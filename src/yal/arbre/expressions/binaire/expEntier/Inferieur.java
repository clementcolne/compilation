package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Inferieur extends ExpressionEntier{

    private int etq;

    /**
     * Constructeur d'une expression
     * @param e1 expression gauche
     * @param e2 expression droite
     * @param n  int
     */
    public Inferieur(Expression e1, Expression e2, int n) {
        super(e1, e2, n);
    }

    /**
     * Retourne le nom de l'expression inférieur
     * @return le nom de l'expression inférieur
     */
    @Override
    public String getNom() {
        return expGauche.getNom()+" < "+expDroite.getNom();
    }

    /**
     * Vérifie les 2 expressions (gauche et droite) de l'expression
     */
    @Override
    public void verifier() {
        if(!expDroite.isBool() && !expGauche.isBool()) {
            expDroite.verifier();
            expGauche.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu est un entier");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Retourne le code mips de l'expression
     * @return le code mips de l'expression
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

        // Gauche en t8, droite en v0
        res.append("\t# Affectation de la valeur booléenne\n");
        res.append("\tble $v0,$t8,si"+etq+"\n");
        res.append("\tla $v0, Vrai\n");
        res.append("\tj suite"+etq+"\n");
        res.append("si"+etq+":\n");
        res.append("\tla $v0, Faux\n");
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
