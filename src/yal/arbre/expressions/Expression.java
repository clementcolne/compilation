package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    protected String nom;

    /**
     * Constructeur d'une expression
     * @param n int
     */
    protected Expression(int n) {
        super(n) ;
    }

    /**
     * Renvoie le nom de l'expression
     * @return String
     */
    public abstract String getNom();

    public boolean isBool(){
        return false;
    }

    /**
     * retourne le nom de l'expression
     * @return String
     */
    public String toString(){
        return nom;
    }

}
