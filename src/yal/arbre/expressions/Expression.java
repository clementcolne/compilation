package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    protected String nom;
    
    protected Expression(int n) {
        super(n) ;
    }

    public abstract String getNom();

}
