package yal.arbre;

public abstract class ArbreAbstrait {
    
    // numéro de ligne du début de l'instruction
    protected int noLigne ;
    
    protected ArbreAbstrait(int n) {
        noLigne = n ;
    }
    
    public int getNoLigne() {
            return noLigne ;
    }

    public abstract void verifier() ;
    public abstract String toMIPS();

}
