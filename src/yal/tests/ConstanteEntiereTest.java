package yal.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yal.arbre.expressions.ConstanteEntiere;

import static org.junit.jupiter.api.Assertions.*;

class ConstanteEntiereTest {

    ConstanteEntiere csteEntiere;

    @BeforeEach
    void setUp() {
        csteEntiere = new ConstanteEntiere("55", 6);
    }

    @Test
    void toMIPS() {
        String mips1 = "\n\tli $v0, 55\t#toMips() de ConstanteEntière";
        String mips2 = "\n\tli $v0, 5\t#toMips() de ConstanteEntière";
        String mips3 = "\n\tli $v0, 55\ttoMips() de ConstanteEntière";
        assertEquals(csteEntiere.toMIPS(),mips1);
        assertNotEquals(csteEntiere.toMIPS(),mips2);
        assertNotEquals(csteEntiere.toMIPS(),mips3);
    }
}