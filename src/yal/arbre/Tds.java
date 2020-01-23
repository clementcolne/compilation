package yal.arbre;

import java.util.HashMap;
import java.util.Map;

public class Tds {

    public HashMap<Entree, Symbole> variables;
    public int cpt;

    private static Tds tds = new Tds();

    public static Tds getInstance() {
        return tds;
    }

    /**
     * Constructeur de Tds
     */
    private Tds() {
        cpt = 0;
        variables = new HashMap<Entree, Symbole>();
    }

    /**
     * Ajoute une entrée et son symbole dans la hashmap des variables
     * @param e Entree
     * @param s Symbole
     * @throws Exception Lève une exception si l'entrée e est déjà présente dans la hashmap des variables
     */
    public void ajouter(Entree e, Symbole s) throws Exception {
        if(!variables.containsKey(e)) {
            // la variable est déclarée pour la première fois
            s.setDeplacement(cpt*(-4));
            variables.put(e, s);
            cpt++;
        }else{
            throw new Exception();
        }
    }

    /**
     * Retourne le symbole correspondant à l'entrée dans la hashmap des variables
     * @param e Entree
     * @return le symbole correspondant à l'entrée dans la hashmap des variables
     */
    public Symbole identifier(Entree e) throws Exception {
        return variables.get(e);
    }

    /**
     * Retourne la taille de la zone allouée aux variables dans la pile
     * @return la taille de la zone allouée aux variables dans la pile
     */
    public int getTailleZoneVariable() {
        return cpt*(-4);
    }

    /**
     * Retourne le nombre de variables déclarées
     * @return le nombre de variables déclarées
     */
    public int getNbVariables() {
        return cpt;
    }

    public int getDeplacement(String e) {
        int res = -1;
        for(Map.Entry<Entree, Symbole> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e)) {
                res = k.getValue().getDeplacement();
            }
        }
        return res;
    }
}
