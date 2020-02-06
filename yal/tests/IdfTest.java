package yal.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yal.arbre.expressions.Idf;

import static org.junit.jupiter.api.Assertions.*;

class IdfTest {

    private Idf idf;

    @BeforeEach
    void setUp() {
        idf = new Idf("nom", 3);
    }


    @Test
    void toMIPS() {
        String result = null;
        assertEquals(result, idf.toMIPS());
    }
}