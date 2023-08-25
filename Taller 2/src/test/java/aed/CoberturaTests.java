package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoberturaTests {
    Cobertura cobertura = new Cobertura();

    @Test
    void testFizzBuzz() {
        assertEquals(cobertura.fizzBuzz(15), "FizzBuzz");
        assertEquals(cobertura.fizzBuzz(9), "Fizz");
        assertEquals(cobertura.fizzBuzz(10), "Buzz");
        assertEquals(cobertura.fizzBuzz(7), "7");
    }

    @Test
    void testNumeroCombinatorio() {
        assertEquals(cobertura.numeroCombinatorio(2, 0), 1);
        assertEquals(cobertura.numeroCombinatorio(2, 2), 1);
        assertEquals(cobertura.numeroCombinatorio(3, 1), 3);
        assertEquals(cobertura.numeroCombinatorio(0, 2), 0);
    }

    @Test
    void testRepeticionesConsecutivas() {
        assertTrue(cobertura.repeticionesConsecutivas(new int[]{1,1,2,2,2,4}) == 3);
        assertFalse(cobertura.repeticionesConsecutivas(new int[0]) != 0);
        assertTrue(cobertura.repeticionesConsecutivas(new int[]{4}) == 1); // Caso donde se rompe
    }
}
