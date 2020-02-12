package yal.arbre.declaration;

import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tds {

    public HashMap<Entree, ArrayList<Symbole>> variables;
    public ArrayList<String> erreurs;
    public int cpt;
    public int cptErreur;
    public int idfEtiquette;
    public boolean cptProg;
    public int blocCourant;
    public ArrayList<Integer> pile;
    private static Tds tds = new Tds();
    public static Tds getInstance() {
        return tds;
    }

    /**
     * Constructeur de Tds
     */
    private Tds() {
        variables = new HashMap<Entree, ArrayList<Symbole>>();
        erreurs = new ArrayList<>();
        pile = new ArrayList<>();
        cptErreur = 0;
        cpt = 0;
        idfEtiquette = 0;
        blocCourant = 0;
        cptProg = false;
    }

    /**
     * Ajoute une entrée et son symbole dans la hashmap des variables
     * @param e Entree
     * @param s Symbole
     * @throws Exception Lève une exception si l'entrée e est déjà présente dans la hashmap des variables
     */
    public void ajouter(Entree e, Symbole s) throws AnalyseSemantiqueException {
        boolean dedans = false;
        Entree entree=new Entree("");
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e.getNom())) {
                dedans = true;
                entree = k.getKey();  // pour gérer le get(Object) -> trouver une façon plus légère
            }
        }
        // la variable n'est pas dedans -> on l'ajoute
        if(!dedans) {
            s.setDeplacement(cpt*(-4));
            ArrayList<Symbole> al = new ArrayList<>();
            al.add(s);
            variables.put(new Entree(e.getNom()), al);
            cpt++;
        }else {
            String type = "";
            // on vérifie si les numéros de blocs sont différents
            int bloc = s.getNoBloc();
            boolean sameBloc = false;
            for (Symbole symb : variables.get(entree)) {
                if (bloc == symb.getNoBloc()) {
                    sameBloc = true;
                    type = symb.getType();
                }
            }

            // la variable est déjà déclarée dans le même bloc -> on vérifie son type
            if (sameBloc) {
                if (s.getType().equals(type)) {  // c'est exactement la même variable
                    int noLig = s.getNoLig();
                    cptErreur++;
                    AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLig, ": multiples déclarations de la variable");
                    erreurs.add(a.getMessage());
                } else {  // on ajoute un symbole à l'AL de l'Entree
                    variables.get(entree).add(new Symbole(s.getType(), s.getNoLig(), blocCourant));
                }
            }
        }
    }

    /**
     * Retourne le symbole correspondant à l'entrée dans la hashmap des variables
     * @param e Entree
     * @return le symbole correspondant à l'entrée dans la hashmap des variables
     */
    public Symbole identifier(String e) throws Exception {
        Symbole s = new Symbole("",-1, blocCourant);
        boolean dedans = false;
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e)) {
                //s = k.getValue();
                dedans = true;
            }
        }
        if(dedans) {
            return new Symbole(s.getType(), s.getNoLig(), blocCourant);
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
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e)) {
                //res = k.getValue().getDeplacement();
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

    /**
     * renvoie un identifiant unique pour l'étiquette de la condition
     * @return int
     */
    public int getIdfEtiquette() {
        idfEtiquette++;
        return idfEtiquette;
    }

    /**
     * Renvoie combien de bloc d'instruction on a
     * @return int
     */
    public boolean getCptProg() {
        return cptProg;
    }

    /**
     * Renvoie le numéro de bloc courant
     * @return int
     */
    public int getBlocCourant() {
        return blocCourant;
    }

    /**
     * Ajoute un bloc à la pile
     */
    public void ajoutBloc(){
        blocCourant++;
        pile.add(blocCourant);
    }

    /**
     * Supprime un bloc à la pile : le bloc est fermé, on n'y revient plus
     */
    public void suppBloc(){
        pile.remove(blocCourant);
        // on ne décrémente pas blocCourant pour pouvoir supprimer des blocs et ne plus y revenir
    }
}
