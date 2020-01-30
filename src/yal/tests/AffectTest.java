package yal.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.Idf;
import yal.arbre.instructions.Affect;

import static org.junit.jupiter.api.Assertions.*;

class AffectTest {

    private Affect affect;

    @BeforeEach
    void setUp() {
        affect = new Affect(new ConstanteEntiere("nom", 3), new Idf("nom", 3), 3);
    }

    @Test
    void toMIPS() {
        String result = "\tli $v0, nom\t#toMips() de ConstanteEntière\n" +
                "\tsw $v0, -1($s7)\n";
        assertEquals(result, affect.toMIPS());
    }
}