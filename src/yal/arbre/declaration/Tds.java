package yal.arbre.declaration;

import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tds {

    public HashMap<Entree, Symbole> variables;
    public ArrayList<String> erreurs;
    public int cpt;
    public int cptErreur;

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
        cptErreur = 0;
        erreurs = new ArrayList<>();
    }

    /**
     * Ajoute une entrée et son symbole dans la hashmap des variables
     * @param e Entree
     * @param s Symbole
     * @throws Exception Lève une exception si l'entrée e est déjà présente dans la hashmap des variables
     */
    public void ajouter(Entree e, Symbole s) throws AnalyseSemantiqueException {
        Symbole symb = new Symbole("",-1);
        for(Map.Entry<Entree, Symbole> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e.getNom())) {
                symb = k.getValue();
            }
        }
        if(symb.getType() != "entier") {  // on n'a pas trouvé la variable -> elle n'est pas déclarée
            s.setDeplacement(cpt*(-4));
            variables.put(new Entree(e.getNom()), s);
            cpt++;
        }else{
            int noLig = symb.getNoLig();
            cptErreur ++;
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLig,": multiples déclarations de la variable");
            erreurs.add(a.getMessage());
        }
    }

    /**
     * Retourne le symbole correspondant à l'entrée dans la hashmap des variables
     * @param e Entree
     * @return le symbole correspondant à l'entrée dans la hashmap des variables
     */
    public Symbole identifier(String e) throws Exception {
        Symbole s = new Symbole("",-1);
        for(Map.Entry<Entree, Symbole> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e)) {
                s = k.getValue();
            }
        }
        if (s.getType() == "entier") {
            return new Symbole(s.getType(), s.getNoLig());
        }else{
            throw new Exception();
        }
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

    public int getCptErreur() {
        return cptErreur;
    }

    public void ajoutErreur(){
        cptErreur ++ ;
    }

    public void add(String e){
        erreurs.add(e);
    }

    public void afficherErreurs(){
        for(String e: erreurs){
            System.out.println(e);
        }
    }
}
