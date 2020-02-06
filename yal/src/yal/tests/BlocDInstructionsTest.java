package yal.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yal.arbre.BlocDInstructions;

import static org.junit.jupiter.api.Assertions.*;

class BlocDInstructionsTest {

    BlocDInstructions bloc;

    @BeforeEach
    void setUp() {
        bloc = new BlocDInstructions(5);
    }


    @Test
    void toMIPS() {
        String prog = ".text\nmain:\n\n";
        prog += "\nend:\n\t#Sortie de programme\n\tli $v0, 10\n\tsyscall\n";
        assertEquals(prog, bloc.toMIPS());
        prog += "\nend:\n\t#Sortie de programme\n\tli $v0, 10\n\tsyscal\n";
        assertNotEquals(prog, bloc.toMIPS());
    }
}