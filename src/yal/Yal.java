package yal;

import yal.analyse.AnalyseurLexical;
import yal.analyse.AnalyseurSyntaxique;
import yal.arbre.ArbreAbstrait;
import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseException;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Yal {
    
    public Yal(String nomFichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;


            arbre.verifier();
            if(Tds.getInstance().getCptErreur() == 0) {
                System.out.println("COMPILATION OK");
                String nomSortie = nomFichier.replaceAll("[.]yal", ".mips") ;
                PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie))) ;
                flot.println(arbre.toMIPS());
                flot.close() ;
            }else{
              //  if(Tds.getInstance().getCptErreur() == 1) {
                    Tds.getInstance().afficherErreurs();
                /*    System.out.println(Tds.getInstance().getCptErreur() + " ERREUR");
                }else{
                    Tds.getInstance().afficherErreurs();
                    System.out.println(Tds.getInstance().getCptErreur() + " ERREURS");
                }*/
            }



        }
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Yal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar yal.jar <fichierSource.yal>") ;
            System.exit(1) ;
        }
        new Yal(args[0]) ;
    }
    
}
