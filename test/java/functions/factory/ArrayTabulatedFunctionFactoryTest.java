package functions.factory;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayTabulatedFunctionFactoryTest {
    @Test
    public void testCreate() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};

        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction function = factory.create(xValues, yValues);

        assertTrue(function instanceof ArrayTabulatedFunction);

        // Проверка значений
        for (int i = 0; i < xValues.length; i++) {
            assertEquals(xValues[i], function.getX(i), 1e-9);
            assertEquals(yValues[i], function.getY(i), 1e-9);
        }
    }
}
