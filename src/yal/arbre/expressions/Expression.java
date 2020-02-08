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

    public boolean isConstante(){
        return false;
    }

    /**
     * renvoie l'opérateur de l'expression
     * @return String
     */
    public String getOper(){return "";}

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
