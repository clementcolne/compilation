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
    public int idfCondition;
    public int idfBoucle;
    public boolean cptProg;

    private static Tds tds = new Tds();

    public static Tds getInstance() {
        return tds;
    }

    /**
     * Constructeur de Tds
     */
    private Tds() {
        variables = new HashMap<Entree, Symbole>();
        erreurs = new ArrayList<>();
        cptErreur = 0;
        cpt = 0;
        idfBoucle = 0;
        idfCondition = 0;
        cptProg = false;
    }

    /**
     * Ajoute une entrée et son symbole dans la hashmap des variables
     * @param e Entree
     * @param s Symbole
     * @throws Exception Lève une exception si l'entrée e est déjà présente dans la hashmap des variables
     */
    public void ajouter(Entree e, Symbole s) throws AnalyseSemantiqueException {
        Symbole symb = new Symbole("",-1);
        boolean dedans = false;
        for(Map.Entry<Entree, Symbole> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e.getNom())) {
                symb = k.getValue();
                dedans = true;
            }
        }
        if(!dedans) {  // on n'a pas trouvé la variable -> elle n'est pas déclarée
            s.setDeplacement(cpt*(-4));
            variables.put(new Entree(e.getNom()), s);
            cpt++;
        }else{
            int noLig = s.getNoLig();
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
        boolean dedans = false;
        for(Map.Entry<Entree, Symbole> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e)) {
                s = k.getValue();
                dedans = true;
            }
        }
        if (dedans) {
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

    /**
     * Renvoie le deplacement de la variable voulue
     * @param e nom de la variable
     * @return int le déplacement
     */
    public int getDeplacement(String e) {
        int res = -1;
        for(Map.Entry<Entree, Symbole> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e)) {
                res = k.getValue().getDeplacement();
            }
        }
        return res;
    }

    /**
     * Renvoie le nombre d'erreurs sémantiques trouvées
     * @return int
     */
    public int getCptErreur() {
        return cptErreur;
    }


    /**
     * Ajoute le message de l'exception de l'erreur sémantique à la liste des messages des erreurs
     * @param e String
     */
    public void add(String e){
        cptErreur ++ ;
        erreurs.add(e);
    }


    /**
     * Remet à 0 le singleton
     */
    public void reset(){
        variables.clear();
        erreurs.clear();
    }


    public void setCptProg(){
        cptProg = true;
    }

    /**
     * Affiche les erreurs sémantiques
     */
    public String afficheErreursSemantiques(){
        if(erreurs.size() > 0) {
            String s = erreurs.get(0) + "\n";
            for (int i=1; i<erreurs.size();i++) {
                s += erreurs.get(i) + "\n";
            }
            return s;
        }else{
            return "";
        }
    }



    public void setIdfBoucle() {
        idfBoucle ++;
    }

    /**
     * renvoie un identifiant unique pour l'étiquette de la condition
     * @return int
     */
    public int getIdfCondition() {
        idfCondition ++;
        return idfCondition;
    }

    /**
     * renvoie un identifiant unique pour l'étiquette de la boucle
     * @return int
     */
    public int getIdfBoucle() {
        return idfBoucle;
    }

    /**
     * Renvoie combien de bloc d'instruction on a
     * @return int
     */
    public boolean getCptProg() {
        return cptProg;
    }

}
