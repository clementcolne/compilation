package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        return "\tli $v0, " + toString() + "\t#toMips() de ConstanteEntière\n";
    }

    @Override
    public String getNom() {
        return null;
    }
}
