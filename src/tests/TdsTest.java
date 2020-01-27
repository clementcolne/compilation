package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yal.arbre.declaration.Entree;
import yal.arbre.declaration.Symbole;
import yal.arbre.declaration.Tds;

import static org.junit.jupiter.api.Assertions.*;

class TdsTest {

    Symbole s;
    Entree e;

    @BeforeEach
    void setUp() {
        Tds.getInstance().reset();
        s = new Symbole("entier",6);
        e = new Entree("variable");
    }

    @Test
    void ajouter() {
        Tds.getInstance().ajouter(e,s);
        assertEquals(Tds.getInstance().getNbVariables(),1);
    }

    @Test
    void identifier() throws Exception{
        Symbole sy = Tds.getInstance().identifier(e.getNom());
        assertEquals(sy.getType(),s.getType());
    }

    @Test
    void getTailleZoneVariable() {
    }

    @Test
    void getNbVariables() {
    }

    @Test
    void getDeplacement() {
    }

    @Test
    void getCptErreur() {
    }

    @Test
    void ajoutErreur() {
    }

    @Test
    void add() {
    }

    @Test
    void afficherErreurs() {
    }
}