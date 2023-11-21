package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import exceptions.InterpolationException;
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

    @Test
    public void testCheckLengthIsTheSame() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 2.0, 3.0};

        assertDoesNotThrow(() -> {
            AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);
        });
    }

    @Test
    public void testCheckLengthIsTheSameException() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 2.0};

        assertThrows(DifferentLengthOfArraysException.class, () -> {
            AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);
        });
    }

    @Test
    public void testCheckSorted() {
        double[] xValues = {1.0, 2.0, 3.0};

        assertDoesNotThrow(() -> {
            AbstractTabulatedFunction.checkSorted(xValues);
        });
    }

    @Test
    public void testCheckSortedException() {
        double[] xValues = {3.0, 2.0, 1.0};

        assertThrows(ArrayIsNotSortedException.class, () -> {
            AbstractTabulatedFunction.checkSorted(xValues);
        });
    }



}
