package yal.arbre;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.SemantiqueException;

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
                for (ArbreAbstrait a : programme) {
                    a.verifier();
                }
            } catch (AnalyseSemantiqueException e) {
                //throw new AnalyseSemantiqueException(0, "");
            }
        if(Tds.getInstance().getCptErreur() > 0) {
            throw new SemantiqueException(Tds.getInstance().afficheErreursSemantiques());
        }
    }

    /**
     * Renvoie le code Mips
     * @return String
     */
    @Override
    public String toMIPS() {
        if(!Tds.getInstance().getCptProg()) {
            String prog = ".data\n" +
                    "BackSlachN: .asciiz \"\\n\"\n.text\n\nmain:\n\n";
            prog += "\t#allocation mémoire pour les variables\n\tmove $s7, $sp\n" +
                    "\tadd $sp, $sp, " + Tds.getInstance().getTailleZoneVariable() + "\n\n";
            for (ArbreAbstrait a : programme) {
                prog += a.toMIPS() + "\n";
            }
            prog += "\nend:\n\t#Sortie de programme\n\tli $v0, 10\n\tsyscall\n";
            return prog;
        }else{
            String prog = "";
            for (ArbreAbstrait a : programme) {
                prog += a.toMIPS() + "\n";
            }
            return prog;
        }
    }

}
