package yal.arbre;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }
    
    @Override
    public String toString() {
        return programme.toString() ;
    }

    @Override
    public void verifier() {
    }
    
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
