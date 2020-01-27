package yal.arbre;

public abstract class ArbreAbstrait {
    
    // numéro de ligne du début de l'instruction
    protected int noLigne ;

    /**
     * Constructeur de l'arbre abstrait
     * @param n int
     */
    protected ArbreAbstrait(int n) {
        noLigne = n ;
    }

    /**
     * Renvoie le numéro de ligne de l'instruction
     * @return int
     */
    public int getNoLigne() {
            return noLigne ;
    }

    /**
     * Vérifie s'il n'y a pas d'erreurs sémantiques
     */
    public abstract void verifier() ;

    /**
     * Renvoie le code Mips
     * @return String
     */
    public abstract String toMIPS();

}
