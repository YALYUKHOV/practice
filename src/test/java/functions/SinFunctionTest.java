package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SinFunctionTest {

    @Test
    void testApply() {
        MathFunction sinFunction = new SinFunction();

        assertEquals(0.0, sinFunction.apply(0.0), 0.0001);
        assertEquals(1.0, sinFunction.apply(Math.PI / 2), 0.0001);
        assertEquals(-1.0, sinFunction.apply(-Math.PI / 2), 0.0001);
        assertEquals(0.866, sinFunction.apply(Math.PI / 3), 0.001); // Пример с округлением

    }
}
