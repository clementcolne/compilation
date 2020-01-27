package yal.arbre;

import yal.arbre.declaration.Tds;
import yal.exceptions.AnalyseSemantiqueException;

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
    public void verifier() {
        try {
            for (ArbreAbstrait a : programme) {
                a.verifier();
            }
        }catch(AnalyseSemantiqueException e){
            throw new AnalyseSemantiqueException(0,"");
        }
    }

    /**
     * Renvoie le code Mips
     * @return String
     */
    @Override
    public String toMIPS() {
        String prog = "";
        prog = ".data\n" +
                "BackSlachN: .asciiz \"\\n\"\n.text\n\nmain:\n\n";
        prog += "\t#allocation mémoire pour les variables\n\tmove $s7, $sp\n" +
                "\tadd $sp, $sp, " + Tds.getInstance().getTailleZoneVariable() + "\n\n";
        for(ArbreAbstrait a : programme) {
            prog += a.toMIPS()+"\n";
        }
        prog += "\nend:\n\t#Sortie de programme\n\tli $v0, 10\n\tsyscall\n";
        return prog;
    }

}
