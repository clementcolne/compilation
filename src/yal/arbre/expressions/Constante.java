package yal.arbre.expressions;

public abstract class Constante extends Expression {

    protected String cste ;
    
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }
    
    @Override
    public void verifier() {
    }

    @Override
    public String toString() {
        return cste ;
    }

}
