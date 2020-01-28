package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yal.arbre.BlocDInstructions;
import yal.arbre.declaration.Tds;
import yal.arbre.expressions.Idf;
import yal.arbre.instructions.Lire;

import static org.junit.jupiter.api.Assertions.*;

class LireTest {

    private Lire lire;

    @BeforeEach
    void setUp() {
        lire = new Lire(new Idf("nom", 3), 3);
    }

    @Test
    void toMIPS() {
        String result = "\t# lecture  entier (syscall 5)\n" +
                "\taddi $v0, $zero, 5\n" +
                "\tsyscall # $v0 contient le résultat de l'entrée de l'utilisateur\n" +
                "\tsw $v0, -1($s7)\n";
        assertEquals(lire.toMIPS(), result);
    }
}