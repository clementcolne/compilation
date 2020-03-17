package yal.arbre.declaration;

import yal.exceptions.AnalyseSemantiqueException;
import yal.outils.Gestionnaire;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tds {

    public HashMap<Entree, ArrayList<Symbole>> variables;
    public ArrayList<String> erreurs;
    public int cptVariables;
    public int cptVariablesLocale;
    public int cptErreur;
    public int blocCourant;
    public int cptBloc;    // pour ne pas avoir 2x le même bloc -> problème de déclarations dans le même bloc
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
        cptVariables = 0;
        cptVariablesLocale = 0;
        blocCourant = 0;
        cptBloc = -1;
        ajoutBloc(blocCourant);
    }

    public void afficherTds(){
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            System.out.print(k.getKey().getNom());
            for(Symbole s: k.getValue()) {
                if(s.isVariableLocale() || s.isParametre()) {
                    System.out.println(" -> " + s.getNoBloc() + "(bloc) " + s.getIdfFonction() + "(idfFonc)");
                }
                if(s.isFonction()){
                    System.out.println(" -> " + s.getNoBloc() + "(bloc) " + s.getNbParametres() + "(paramètres)");
                }
                if(s.isVariable()){
                    System.out.println(" -> " + s.getNoBloc() + "(bloc) ");
                }
            }
        }
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
        ArrayList<Symbole> listSymb = new ArrayList<>();
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e.getNom())) {
                dedans = true;
                listSymb = k.getValue();
                entree = k.getKey();  // pour gérer le get(Object) -> trouver une façon plus légère
            }
        }
        // la variable n'est pas dedans -> on l'ajoute
        if(!dedans) {
            if (s.getType().equals("entier")) {
                // le symbole est une variable entière non locale à une fonction
                if (s.isVariable()) {
                    s.setDeplacement(cptVariables);
                    cptVariables -= 4;
                }
                ArrayList<Symbole> al = new ArrayList<>();
                al.add(s);
                variables.put(new Entree(e.getNom()), al);
            } else {
                ArrayList<Symbole> al = new ArrayList<>();
                al.add(s);
                variables.put(new Entree(e.getNom()), al);
            }
        }else {
            String type = "";
            // on vérifie si les numéros de blocs sont différents
            boolean sameBloc = false;
            int nbParam = -1;
            for (Symbole symb : variables.get(entree)) {
                if (s.getNoBloc() == symb.getNoBloc()) {
                    sameBloc = true;
                    type = symb.getType();
                    if(symb.isFonction()){
                        nbParam = symb.getNbParametres();
                    }
                }
            }

            // la variable est déjà déclarée dans le même bloc -> on vérifie son type
            if (sameBloc) {
                if (s.getType().equals(type)) {  // c'est exactement la même variable
                    cptErreur++;
                    if(s.getType().equals("fonction")) {
                        if(s.getNbParametres() == nbParam) {
                            AnalyseSemantiqueException a = new AnalyseSemantiqueException(s.getNoLig(), ": multiples déclarations de fonction " + e.getNom());
                            erreurs.add(a.getMessage());
                        }else{
                            // Surcharge de fonction
                            variables.get(entree).add(s);
                        }
                    }else if(s.isParametre()){
                        AnalyseSemantiqueException a = new AnalyseSemantiqueException(s.getNoLig(), ": multiples déclarations du paramètre "+e.getNom());
                        erreurs.add(a.getMessage());
                    }else{
                        AnalyseSemantiqueException a = new AnalyseSemantiqueException(s.getNoLig(), ": multiples déclarations de la variable "+e.getNom());
                        erreurs.add(a.getMessage());
                    }
                } else {  // types différents
                    if(type.equals("entier")){
                        // le symbole est une variable
                        if(s.isVariable()) {
                            s.setDeplacement(cptVariables);
                            cptVariables -= 4;
                        }
                    }
                    variables.get(entree).add(s);
                }
            }else{ // blocs différents
                if(type.equals("entier")){
                    s.setDeplacement(cptVariables);
                    if(s.isVariable()) {
                        s.setDeplacement(cptVariables);
                        cptVariables -= 4;
                    }
                }
                variables.get(entree).add(s);
            }
        }
        //System.out.println("Pile:");
        //afficherTds();
    }


    /**
     * Retourne le symbole correspondant à l'entrée dans la hashmap des variables
     * @param e Entree
     * @return le symbole correspondant à l'entrée dans la hashmap des variables
     */
    public Symbole identifier(String e, int n, String type, int nbParam)  {
        Symbole s = new Symbole("",-1, blocCourant,"");
        boolean dedans = false;
        boolean bonBloc = false;
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            if(k.getKey().getNom().equals(e)) {
                for(Symbole symb : k.getValue()){
                    if(pile.contains(symb.getNoBloc()) && symb.getType().equals(type)){
                        if(symb.isFonction()){
                            if(symb.getNbParametres()==nbParam) {
                                bonBloc = true;
                                s = symb;
                            }
                        }else {
                            bonBloc = true;
                            s = symb;
                        }
                    }
                }
                dedans = true;
            }
        }
        if(dedans && bonBloc) {
            return new Symbole(s.getType(), s.getNoLig(), blocCourant,s.getEtq());
        }else{
            return s;
        }
    }

    /**
     * Retourne la taille de la zone allouée aux variables dans la pile
     * @return la taille de la zone allouée aux variables dans la pile
     */
    public int getTailleZoneVariable() {
        return cptVariables;
    }

    /**
     * Retourne le nombre de variables déclarées
     * @return le nombre de variables déclarées
     */
    public int getNbVariables() {
        return cptVariables;
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
                            res = s.getDeplacement();
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * Met à jour le déplacement des variables locales et paramètres associées à la fonction idf
     * @param idfFonction
     */
    public void setDeplacementVarLoc(int idfFonction){
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            for(Symbole s : k.getValue()) {
                // pour chaque entrée, je parcours son arraylist de symboles
                // si le symbole est un paramètre et que il est lié à la fonction qui nous intéresse
                // alors on incrémente le compteur
                if(s.isParametre() && Gestionnaire.getInstance().getFonctionCourante() == idfFonction) {
                    s.setDeplacement(cptVariablesLocale);
                    System.out.println("Ajout paramètre déplacement : " + cptVariablesLocale);
                    cptVariablesLocale -= 4;
                }
            }
        }
        cptVariablesLocale -= 4;
        System.out.println("-8");
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            for(Symbole s : k.getValue()) {
                // pour chaque entrée, je parcours son arraylist de symboles
                // si le symbole est un paramètre et que il est lié à la fonction qui nous intéresse
                // alors on incrémente le compteur
                if(s.isVariableLocale() && Gestionnaire.getInstance().getFonctionCourante() == idfFonction) {
                    s.setDeplacement(cptVariablesLocale);

                    System.out.println("Ajout variable locale déplacement : " + cptVariablesLocale);
                    cptVariablesLocale -= 4;
                }
            }
        }
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
     * Renvoie le numéro du prochain bloc à prendre pour le donner en paramètre à une fonction
     * @return int
     */
    public int getCptBloc(){
        return cptBloc;
    }

    /**
     * Renvoie le numéro du prochain bloc à prendre pour le donner en paramètre à une variable locale
     * @return int
     */
    public void setCptBloc(){
        cptBloc++;
    }


    /**
     * Ajoute un bloc à la pile
     */
    public void ajoutBloc(int b){
        blocCourant = b;
        cptBloc ++;
        pile.add(b);
    }


    /**
     * Supprime un bloc à la pile : le bloc est fermé, on n'y revient plus
     */
    public void suppBloc(Integer b){
        pile.remove(b);
        blocCourant = pile.get(pile.size() - 1);
        // on ne décrémente pas blocCourant pour pouvoir supprimer des blocs et ne plus y revenir
    }

    /**
     * Retourne le nombre de paramètres d'une fonction
     * @param idfFonction identifiant de la fonction
     * @return le nombre de paramètres d'une fonction
     */
    public int getNbParametres(int idfFonction) {
        //System.out.println("Pile");
        //afficherTds();
        int cptParametre = 0;
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            for(Symbole s : k.getValue()) {
                // pour chaque entrée, je parcours son arraylist de symboles
                // si le symbole est un paramètre et que il est lié à la fonction qui nous intéresse
                // alors on incrémente le compteur
                if(s.isParametre() && s.getIdfFonction() == idfFonction) {
                    cptParametre++;
                }
            }
        }
        return cptParametre;
    }

    /**
     * Retourne la liste des paramètres d'une fonction
     * @param idfFonction identifiant de la fonction
     * @return l'arraylist de paramètres d'une fonction
     */
    public ArrayList<Symbole> getParametres(int idfFonction) {
        ArrayList<Symbole> list = new ArrayList<>();
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            for(Symbole s : k.getValue()) {
                // pour chaque entrée, je parcours son arraylist de symboles
                // si le symbole est un paramètre et que il est lié à la fonction qui nous intéresse
                // alors on incrémente le compteur
                if(s.isParametre() && s.getIdfFonction() == idfFonction) {
                    list.add(s);
                }
            }
        }
        return list;
    }

    /**
     * Retourne la liste des variables locales d'une fonction
     * @param idfFonction identifiant de la fonction
     * @return l'arraylist des variables locales d'une fonction
     */
    public ArrayList<Symbole> getVarLoc(int idfFonction) {
        ArrayList<Symbole> list = new ArrayList<>();
        for(Map.Entry<Entree, ArrayList<Symbole>> k : variables.entrySet()) {
            for(Symbole s : k.getValue()) {
                // pour chaque entrée, je parcours son arraylist de symboles
                // si le symbole est un paramètre et que il est lié à la fonction qui nous intéresse
                // alors on incrémente le compteur
                if(s.isVariableLocale() && s.getIdfFonction() == idfFonction) {
                    list.add(s);
                }
            }
        }
        return list;
    }

}
