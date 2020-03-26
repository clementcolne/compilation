package yal.arbre.expressions;

public class TailleTableau extends Expression {
import yal.arbre.declaration.Tds;


    private Idf nom;
    /**
     * Constructeur d'une taille de tableau
     * @param n int
     */
    public TailleTableau(Idf nom, int n) {
        super(n);
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public void verifier() {
        if(!Tds.getInstance().identifier(nom.getNom(), noLigne,"tableau",0).getType().equals("tableau")){
            AnalyseSemantiqueException a = new AnalyseSemantiqueException(noLigne, ": tableau " + nom + " non déclarée");
            Tds.getInstance().add(a.getMessage());
        }
    }

    @Override
    public String toMIPS() {
        return "\tli $v0, " + Tds.getInstance().identifier(nom, noLigne, "tableau", 0).getTailleTableau() + "\n";
    }
}
