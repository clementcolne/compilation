package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yal.arbre.declaration.Entree;

import static org.junit.jupiter.api.Assertions.*;

class EntreeTest {

    private Entree e;

    @BeforeEach
    void setUp() {
        e = new Entree("entree");
    }

    @Test
    void getNom() {
        assertEquals("entree", e.getNom());
    }
}