package yal.tests;

import org.junit.jupiter.api.BeforeEach;
import yal.arbre.declaration.Symbole;

class SymboleTest {

    // Que des setter ou des getter donc pas de yal.tests

    Symbole s;

    @BeforeEach
    void setUp() {
        s = new Symbole("entier",6);
    }

}