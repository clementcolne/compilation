package yal.arbre.expressions.binaire.expEntier;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Inferieur extends ExpressionEntier{

    private int etq;

    /**
     * Constructeur d'une expression
     *
     * @param e1
     * @param e2
     * @param n  int
     */
    public Inferieur(Expression e1, Expression e2, int n) {
        super(e1, e2, n);
        etq = Tds.getInstance().getIdfEtiquette();
    }

    /**
     * renvoie l'opérateur de l'expression
     * @return String
     */
    @Override
    public String getOper(){
        return "<";
    }

    @Override
    public String getNom() {
        return expGauche.getNom()+" < "+expDroite.getNom();
    }

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

    @Override
    public String toMIPS() {    // TODO trouver un moyen de factoriser
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
        res += "\tblt $v0,$t8,si"+etq+"\n";
        res += "\tla $v0, Vrai\n";
        res += "\tjal suite"+etq+"\n";
        res += "si"+etq+":\n";
        res += "\tla $v0, Faux\n";
        res += "\tjal suite"+etq+"\n";
        res += "suite"+etq+":\n";

        return res;
    }

    @Override
    public boolean isBool() {
        return true;
    }
}
