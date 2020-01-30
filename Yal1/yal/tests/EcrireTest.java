package yal.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.instructions.Ecrire;

class EcrireTest {

    Ecrire ecrire;

    @BeforeEach
    void setUp() {
        ecrire = new Ecrire(new ConstanteEntiere("5", 17), 17);
    }

    @Test
    void toMIPS() {
        String testEcrire;

        testEcrire = "\t#ecrire 5\n" +
                "\tli $v0, 5\t#toMips() de ConstanteEntière\n" +
                "\tmove $a0, $v0 # $a0 = $v0\n" +
                "\tli $v0, 1\n" +
                "\tsyscall\n";

        Assertions.assertEquals(testEcrire, ecrire.toMIPS());

        testEcrire = "\t#ecrire 5\n" +
                "\tli $v0, 5\t#toMips() de ConstanteEntière\n" +
                "\tmove $a0, $v0 # $a0 = $v0\n" +
                "\tli $v0, 1\n" +
                "\tsyscall";

        Assertions.assertNotEquals(testEcrire, ecrire.toMIPS());
    }
}