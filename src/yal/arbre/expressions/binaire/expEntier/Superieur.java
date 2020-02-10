package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Superieur extends ExpressionEntier{

    private int etq;
    /**
     * Constructeur d'une expression
     * @param e1 expression gauche
     * @param e2 expression droite
     * @param n  int
     */
    public Superieur(Expression e1, Expression e2, int n) {
        super(e1, e2, n);
    }

    /**
     * Retourne le nom de l'expression supérieur à
     * @return le nom de l'expression supérieur à
     */
    @Override
    public String getNom() {
        return expGauche.getNom()+" > "+expDroite.getNom();
    }

    /**
     * Vérifie les 2 expressions (gauche et droite) de la comparaison
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
     * Retourne le code mips de l'expression supérieur à
     * @return le code mips de l'expression supérieur à
     */
    @Override
    public String toMIPS() {
        etq = Tds.getInstance().getIdfEtiquette();
        String res = expGauche.toMIPS() + "\n";
        res += "\t# Empiler $v0\n";
        res += "\tsw $v0,($sp)\n";
        res += "\tadd $sp,$sp,-4\n";

        res += expDroite.toMIPS() + "\n";
        res += "\t# Dépiler $v0\n";
        res += "\tadd $sp,$sp,4\n";
        res += "\tlw $t8,($sp)\n";

        // Gauche en t8, droite en v0
        res += "\t# Affectation de la valeur booléenne\n";
        res += "\tbge $v0,$t8,si"+etq+"\n";
        res += "\tla $v0, Vrai\n";
        res += "\tjal suite"+etq+"\n";
        res += "si"+etq+":\n";
        res += "\tla $v0, Faux\n";
        res += "\tjal suite"+etq+"\n";
        res += "suite"+etq+":\n";

        return res;
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
