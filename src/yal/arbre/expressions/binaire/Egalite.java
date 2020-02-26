package yal.arbre.expressions.binaire;

import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;

public class Egalite extends Expression {

    private Expression expDroite;
    private Expression expGauche;
    private int etq;

    /**
     * Constructeur d'une expression
     * @param n int
     */
    public Egalite(Expression e1, Expression e2, int n) {
        super(n);
        expDroite = e2;
        expGauche = e1;
    }

    /**
     * Retourne le nom de l'expression égalité
     * @return le nom de l'expression égalité
     */
    @Override
    public String getNom() {
        return expGauche.getNom()+" == "+expDroite.getNom();
    }

    /**
     * Vérifie les expressions (gauche et droite) de l'expression égalité
     */
    @Override
    public void verifier()   {
        if((!expDroite.isBool() && !expGauche.isBool()) || (expDroite.isBool() && expGauche.isBool())) {
            expDroite.verifier();
            expGauche.verifier();
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": le type attendu doit être identique");
            Tds.getInstance().add(a.getMessage());
        }
    }

    /**
     * Retourne le code mips de l'expression égalité
     * @return le code mips de l'expression égalité
     */
    @Override
    public String toMIPS() {
        etq = Gestionnaire.getInstance().getIdfEtiquette();
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
        res.append("\tbeq $v0,$t8,si"+etq+"\n");
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
