package yal.arbre.declaration;

import yal.arbre.ArbreAbstrait;

public class Fonction extends ArbreAbstrait{

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