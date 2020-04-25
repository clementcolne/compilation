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

    /**
     * Retourne faux
     * @return faux
      */
    public boolean isBool(){
        return false;
    }

    /**
     * Retourne faux
     * @return faux
     */
    public boolean isIdf(){
        return false;
    }

    /**
     * Retourne faux
     * @return faux
     */
    public boolean isConstante(){
        return false;
    }

    /**
     * Retourne faux
     * @return faux
     */
    public boolean isTableau(){
        return false;
    }

    /**
     * retourne le nom de l'expression
     * @return String
     */
    public String toString(){
        return nom;
    }

    /**
     * Renvoie le résultat entier de l'expression pour vérifier si c'est une division par 0
     * @return int
     */
    public int getNombre(){
        return 0;
    }

}
