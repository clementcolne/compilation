package yal.arbre.declaration;

import yal.arbre.ArbreAbstrait;

public class Fonction extends ArbreAbstrait{

    /* |
                FONCTION IDF:i DEBUT LINST:li FIN
                {: Tds.getInstance().ajouter(new Entree(i), new Symbole("fonction", ileft + 1,Tds.getInstance().getBlocCourant())) ;
                RESULT = new Fonction(li, ileft + 1) ; :}*/

    public Fonction(ArbreAbstrait a, int n){
        super(n);
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
