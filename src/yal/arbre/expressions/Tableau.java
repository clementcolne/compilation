package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

public class Tableau extends Expression {

    private Expression exp;
    private Idf nom;

    /**
     * Constructeur d'un tableau
     * @param n int
     */
    public Tableau(Idf nom, int n, Expression e) {
        super(n);
        this.nom = nom;
        noLigne = n;
        exp = e;
    }

    /**
     * Retourne le nom du tableau
     * @return
     */
    @Override
    public String getNom() {
        return nom.getNom()+"["+ exp.getNom()+"]";
    }

    /**
     * Vérifie
     */
    @Override
    public void verifier() {
        if(!Tds.getInstance().identifier(nom.getNom(), noLigne,"tableau",0).getType().equals("tableau")){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": tableau " + nom + " non déclarée");
            Tds.getInstance().add(a.getMessage());

        }
        if(exp.isBool()){
            AnalyseSemantiqueException as = new AnalyseSemantiqueException(noLigne, ": l'expression doit être entière dans le tableau " + nom);
            Tds.getInstance().add(as.getMessage());
        }else{
            exp.verifier();
        }
    }

    /**
     * Retourne le toMIPS du tableau
     * @return le toMIPS du tableau
     */
    public String toMIPS() {
        // condition pour vérifier l'indice et la taille du tableau
        StringBuilder res = new StringBuilder();
        res.append(exp.toMIPS());
        res.append("\t# Calcul du déplacement de l'indice\n");
        res.append("\tadd $v0, $v0, 1\n");
        res.append("\tmul $v0, $v0, -4\n");
        res.append("\tmflo $v0\n");
        res.append("\t# On récupère ce qu'il y a dans la case\n");
        // Dans $t8 il y a le déplacement du tableau
        // Dans $v0 il y a le déplacement dans le tableau, à soustraire à $t8
        res.append("\tlw $t8, " + Tds.getInstance().identifier(nom.getNom(), noLigne,"tableau",0).getDeplacement() + "($s7)");
        res.append("\tadd $t8, $t8, $v0\n");
        res.append("\tlw $v0, $t8($s7)");

        return res.toString();
    }
}
