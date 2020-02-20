package yal.arbre.declaration;

import yal.arbre.ArbreAbstrait;

public class Fonction extends ArbreAbstrait{

    private ArbreAbstrait arbre;

    public Fonction(ArbreAbstrait a, int n){
        super(n);
        arbre = a;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        Tds.getInstance().setCptProg();
        StringBuilder res = new StringBuilder();
        res.append("fonction" + Tds.getInstance().getIdfEtiquette() + ":");
        res.append(arbre.toMIPS());
        res.append("jr $ra");
        Tds.getInstance().suppBloc();
        return res.toString();
    }
}