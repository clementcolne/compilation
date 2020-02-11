package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {

    /**
     * Constructeur d'une constante entière
     * @param texte String
     * @param n int
     */
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    /**
     * Renvoie le code Mips
     * @return String
     */
    @Override
    public String toMIPS() {
        return "\tli $v0, " + toString();
    }

    /**
     * Retourne vrai
     * @return vrai
     */
    @Override
    public boolean isConstante() {
        return true;
    }

    /**
     * Renvoie le nom de l'expression
     * @return String
     */
    @Override
    public String getNom() {
        return cste;
    }
}
