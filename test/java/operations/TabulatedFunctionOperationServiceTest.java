package operations;

import exceptions.InconsistentFunctionsException;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TabulatedFunctionOperationServiceTest {

    @Test
    public void asPointsTest() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(xValues, yValues);

        Point[] expectedPoints = new Point[]{
                new Point(1.0, 4.0),
                new Point(2.0, 5.0),
                new Point(3.0, 6.0)
        };

        Point[] arrayOfPoints = TabulatedFunctionOperationService.asPoints(func1);

        assertArrayEquals(expectedPoints, arrayOfPoints);
    }

    double[] xValue = {1, 1.5, 2, 2.5, 3};
    double[] yValue1 = {2, 3, 4, 5, 6};
    double[] yValue2 = {8, 9, 10, 11, 12};
    TabulatedFunctionFactory factory1 = new LinkedListTabulatedFunctionFactory();
    TabulatedFunctionOperationService operation1 = new TabulatedFunctionOperationService(factory1);
    TabulatedFunctionOperationService operation2 = new TabulatedFunctionOperationService();
    ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(xValue, yValue1);
    ArrayTabulatedFunction func2 = new ArrayTabulatedFunction(xValue, yValue2);
    LinkedListTabulatedFunction func3 = new LinkedListTabulatedFunction(xValue, yValue1);
    LinkedListTabulatedFunction func4 = new LinkedListTabulatedFunction(xValue, yValue2);
    @Test
    void testAdd() {
        // Create two tabulated functions


        TabulatedFunction result1 = operation1.add(func1, func2);
        for (int i = 0; i < result1.getCount(); i++) {
            assertEquals(yValue1[i] + yValue2[i], result1.getY(i));
        }

        TabulatedFunction result2 = operation2.add(func1, func4);
        for (int i = 0; i < result2.getCount(); i++) {
            assertEquals(yValue1[i] + yValue2[i], result2.getY(i));
        }
    }

    @Test
    void testSubtract() {
        // Create two tabulated functions
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues1 = {2.0, 3.0, 4.0};
        double[] yValues2 = {1.0, 2.0, 3.0};

        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);

        TabulatedFunction function1 = factory.create(xValues, yValues1);
        TabulatedFunction function2 = factory.create(xValues, yValues2);

        TabulatedFunction result = service.subtract(function1, function2);

        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(1.0, result.getY(i), 1e-9);
        }
    }

    @Test
    void testMultiply() {
        TabulatedFunction result1 = operation1.multiply(func1, func2);
        for (int i = 0; i < result1.getCount(); i++) {
            assertEquals(yValue1[i] * yValue2[i], result1.getY(i));
        }

        TabulatedFunction result2 = operation2.multiply(func1, func4);
        for (int i = 0; i < result2.getCount(); i++) {
            assertEquals(yValue1[i] * yValue2[i], result2.getY(i));
        }
    }

    @Test
    void testDivide() {
        TabulatedFunction result1 = operation1.divide(func1, func2);
        for (int i = 0; i < result1.getCount(); i++) {
            assertEquals(yValue1[i] / yValue2[i], result1.getY(i));
        }

        TabulatedFunction result2 = operation2.divide(func1, func4);
        for (int i = 0; i < result2.getCount(); i++) {
            assertEquals(yValue1[i] / yValue2[i], result2.getY(i));
        }
    }


}
