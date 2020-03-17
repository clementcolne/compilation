package yal.arbre.expressions;

import yal.arbre.declaration.Tds;
import yal.arbre.instructions.Instruction;
import yal.exceptions.AnalyseSemantiqueException;

public class Parametre extends Instruction {

    private String idf;

    /**
     * Constructeur d'une instruction
     *
     * @param n int
     */
    public Parametre(int n, String idf) {
        super(n);
        this.idf = idf;
    }

    @Override
    public void verifier() {
            if(!Tds.getInstance().identifier(idf,noLigne,"entier",0).getType().equals("entier")){
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": paramètre "+idf+" non déclaré");
                Tds.getInstance().add(a.getMessage());
            }
    }

    @Override
    public String toMIPS() {
        // on empile le paramètre, faire passer par $v0, afin qu'il devienne une variable locale
        // comment on fait avec s2 ??? on décalle s2 où ? à chaque variable ou tout d'un coup ??
        return null;
    }
}
