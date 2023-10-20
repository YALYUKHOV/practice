package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AbstractTabulatedFunctionTest {

    @Test
    void testInterpolate() {
        MockTabulatedFunction function = new MockTabulatedFunction(0.0, 1.0, 0.0, 1.0);

        assertEquals(0.0, function.interpolate(0.0, 0), 1e-10);
        assertEquals(1.0, function.interpolate(1.0, 0), 1e-10);
        assertEquals(0.5, function.interpolate(0.5, 0), 1e-10);

    }

    @Test
    void testApply() {
        MockTabulatedFunction function = new MockTabulatedFunction(0.0, 1.0, 0.0, 1.0);

        assertEquals(0.0, function.apply(0.0), 1e-10);
        assertEquals(1.0, function.apply(1.0), 1e-10);
        assertEquals(0.5, function.apply(0.5), 1e-10);

    }
}
