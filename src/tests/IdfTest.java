package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.Idf;
import yal.arbre.instructions.Affect;

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