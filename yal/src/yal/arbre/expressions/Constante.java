package yal.arbre.expressions;

public abstract class Constante extends Expression {

    protected String cste ;

    /**
     * Constructeur d'une constante
     * @param texte String
     * @param n int
     */
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }

    /**
     * Vérifie les erreurs sémantiques
     */
    @Override
    public void verifier() {
    }

    /**
     * Renvoie la constante
     * @return String
     */
    @Override
    public String toString() {
        return cste ;
    }

    public boolean isBool(){
        return false;
    }

}
