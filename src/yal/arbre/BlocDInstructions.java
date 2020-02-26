package yal.arbre;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.SemantiqueException;
import yal.outils.Gestionnaire;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;

    /**
     * Constructeur d'un bloc d'instruction
     * @param n int
     */
    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }

    /**
     * Ajoute un arbre abstrait
     * @param a ArbreAbstrait
     */
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }

    /**
     * Renvoie le bloc d'instruction
     * @return String
     */
    @Override
    public String toString() {
        return programme.toString() ;
    }

    /**
     * Vérifie s'il n'y a pas d'erreurs sémantiques
     */
    @Override
    public void verifier() throws SemantiqueException{
            try {
                if(!Gestionnaire.getInstance().getCptProg()) {
                    Gestionnaire.getInstance().verifierFonc();
                    for (ArbreAbstrait a : programme) {
                        a.verifier();
                    }
                    Gestionnaire.getInstance().setFinProg();
                }else{   // pour éviter la boucle infinie d'apppels
                    for (ArbreAbstrait a : programme) {
                        a.verifier();
                    }
                }

            } catch (AnalyseSemantiqueException e) {
            } catch (Exception e) {
            }
        if(Tds.getInstance().getCptErreur() > 0 && Gestionnaire.getInstance().isFinProg()) {
            throw new SemantiqueException(Tds.getInstance().afficheErreursSemantiques());
        }
    }

    /**
     * Renvoie le code Mips
     * @return String
     */
    @Override
    public String toMIPS() {
        if(!Gestionnaire.getInstance().getCptProg()) {
            StringBuilder prog = new StringBuilder();
            prog.append(".data\n" +
                    "BackSlachN: .asciiz \"\\n\"\nVrai: .asciiz \"vrai\"\nFaux: .asciiz \"faux\"\nErreurDivisionZero: .asciiz \"Erreur : Division par zéro\"\n.text\n\nmain:\n\n");
            prog.append("\t#allocation mémoire pour les variables\n\tmove $s7, $sp\n" +
                    "\tadd $sp, $sp, " + Tds.getInstance().getTailleZoneVariable() + "\n\n");
            for (ArbreAbstrait a : programme) {
                prog.append(a.toMIPS() + "\n");
            }
            prog.append("\nend:\n\t#Sortie de programme\n\tli $v0, 10\n\tsyscall\n");
            prog.append("erreurDivisionZero:\n\tli $v0, 4 \n\tla $a0, ErreurDivisionZero\n\tsyscall\n\t#Sortie de programme\n\tli $v0, 10\n\tsyscall\n");
            prog.append(Gestionnaire.getInstance().afficheFonction());
            return prog.toString();
        }else{
            StringBuilder prog = new StringBuilder();
            for (ArbreAbstrait a : programme) {
                prog.append(a.toMIPS() + "\n");
            }
            return prog.toString();
        }
    }

}
