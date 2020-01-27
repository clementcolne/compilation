package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yal.arbre.declaration.Symbole;

import static org.junit.jupiter.api.Assertions.*;

class SymboleTest {

    // Que des setter ou des getter donc pas de tests

    Symbole s;

    @BeforeEach
    void setUp() {
        s = new Symbole("entier",6);
    }

}