package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        // test
        return "\n\tli $v0, " + toString() + "\t#toMips() de ConstanteEntière";
    }

}
