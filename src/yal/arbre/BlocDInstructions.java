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
        prog = ".text\nmain:\n\n";
        for(ArbreAbstrait a : programme) {
            prog += a.toMIPS()+"\n";
        }
        prog += "\nend:\n\t#Sortie de programme\n\tli $v0, 10\n\tsyscall\n";
        return prog;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
