package yal.arbre.expressions.binaire;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Egalite extends Expression {

    private Expression expDroite;
    private Expression expGauche;
    private int etq;

    /**
     * Constructeur d'une expression
     *
     * @param n int
     */
    public Egalite(Expression e1, Expression e2, int n) {
        super(n);
        expDroite = e2;
        expGauche = e1;
    }

    @Override
    public String getNom() {
        return expGauche.getNom()+" == "+expDroite.getNom();
    }

    /**
     * renvoie l'opérateur de l'expression
     * @return String
     */
    @Override
    public String getOper(){
        return "==";
    }

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

        res += "\t# Affectation de la valeur booléenne\n";
        res += "\tbeq $v0,$t8,si"+etq+"\n";
        res += "\tla $v0, Faux\n";
        res += "\tjal suite"+etq+"\n";
        res += "si"+etq+":\n";
        res += "\tla $v0, Vrai\n";
        res += "\tjal suite"+etq+"\n";
        res += "suite"+etq+":\n";

        return res;
    }

    @Override
    public boolean isBool() {
        return true;
    }
}
