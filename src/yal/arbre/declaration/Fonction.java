package yal.arbre.declaration;

import yal.arbre.ArbreAbstrait;

public class Fonction extends ArbreAbstrait{

<<<<<<< HEAD
=======
    private ArbreAbstrait arbre;

>>>>>>> ab704d7d651fb044aec964cd41b3d75b2e405efc
    public Fonction(ArbreAbstrait a, int n){
        super(n);
        arbre = a;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        Tds.getInstance().suppBloc();
        return null;
    }
}