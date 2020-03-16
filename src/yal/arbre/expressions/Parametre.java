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
        try {
            if(!Tds.getInstance().identifier(idf,noLigne,"entier").getType().equals("entier")){
                AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": paramètre "+idf+" non déclaré");
                Tds.getInstance().add(a.getMessage());
            }
        }catch (Exception e){
        }
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
