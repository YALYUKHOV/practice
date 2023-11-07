package operations;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TabulatedDifferentialOperatorTest {


    @Test
    public void Arraytestderive2() throws Exception {
        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);

        double[] xValues = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yValues = {2.0, 4.0, 6.0, 8.0, 10.0};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction derivative_function = operator.derive(function);
        assertEquals(2.0, derivative_function.getY(0));
        assertEquals(2.0, derivative_function.getY(1));
        assertEquals(2.0, derivative_function.getY(2));
        assertEquals(2.0, derivative_function.getY(3));
        assertEquals(2.0, derivative_function.getY(4));
    }
}
