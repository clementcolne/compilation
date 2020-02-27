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
        variables = new HashMap<>();
        erreurs = new ArrayList<>();
        pile = new ArrayList<>();
        cptErreur = 0;
        cpt = 0;
        blocCourant = 0;
        ajoutBloc();
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
            if(s.getType().equals("entier")) {
                s.setDeplacement(cpt);
                ArrayList<Symbole> al = new ArrayList<>();
                al.add(s);
                variables.put(new Entree(e.getNom()), al);
                cpt -= 4;
            }else{
                s.setDeplacement(cpt);
                ArrayList<Symbole> al = new ArrayList<>();
                al.add(s);
                variables.put(new Entree(e.getNom()), al);
                cpt -= 12;
            }
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
                    variables.get(entree).add(new Symbole(s.getType(), s.getNoLig(), blocCourant,s.getEtq()));
                }
            }
        }
    }

    /**
     * Retourne le symbole correspondant à l'entrée dans la hashmap des variables
     * @param e Entree
     * @return le symbole correspondant à l'entrée dans la hashmap des variables
     */
    public Symbole identifier(String e, int n)  {
        Symbole s = new Symbole("",-1, blocCourant,"");
        boolean dedans = false;
        boolean bonBloc = false;
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e)) {
                for(Symbole symb : k.getValue()){
                    if(pile.contains(symb.getNoBloc())){
                        bonBloc = true;
                        s = symb;
                    }
                }
                dedans = true;
            }
        }
        if(dedans && bonBloc) {
            return new Symbole(s.getType(), s.getNoLig(), blocCourant,s.getEtq());
        }else{
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(n, ": variable non déclarée");
            Tds.getInstance().add(a.getMessage());
        }
        return null;
    }

    /**
     * Retourne la taille de la zone allouée aux variables dans la pile
     * @return la taille de la zone allouée aux variables dans la pile
     */
    public int getTailleZoneVariable() {
        return cpt;
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
                for(Symbole s: k.getValue()) {
                    for(int i=pile.size()-1; i>=0; i--) {   // on prend le dernier bloc ouvert
                        if(s.getNoBloc()==pile.get(i)) {
                            res = k.getValue().get(i).getDeplacement();
                        }
                    }
                }
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

    /**
     * Affiche les erreurs sémantiques
     */
    public String afficheErreursSemantiques(){
        if(erreurs.size() > 0) {
            StringBuilder s = new StringBuilder();
            s.append(erreurs.get(0) + "\n");
            for (int i=1; i<erreurs.size();i++) {
                s.append(erreurs.get(i) + "\n");
            }
            return s.toString();
        }else{
            return "";
        }
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
        pile.add(blocCourant);
    }

    public void ajoutCptBloc(){
        blocCourant++;
    }

    /**
     * Supprime un bloc à la pile : le bloc est fermé, on n'y revient plus
     */
    public void suppBloc(){
        pile.remove(blocCourant);
        blocCourant = pile.get(pile.size()-1);
        // on ne décrémente pas blocCourant pour pouvoir supprimer des blocs et ne plus y revenir
    }
}
