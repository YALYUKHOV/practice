package operations;

import static org.junit.jupiter.api.Assertions.*;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedFunctionOperationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TabulatedFunctionOperationServiceTest {
    @Test
    public void asPointsTest() {
        double[] xValue = {1, 1.5, 2, 2.5, 3};
        double[] yValue1 = {2, 3, 4, 5, 6};
        double[] yValue2 = {8, 9, 10, 11, 12};
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(xValue, yValue1);
        Point[] arrayOfPoints = TabulatedFunctionOperationService.asPoints(func1);
        int i = 0;
        for (Point point : arrayOfPoints) {
            assertEquals(point.x, xValue[i]);
            assertEquals(point.y, yValue1[i]);
            ++i;
        }
    }

    double[] xValues = {1, 2, 3, 4};
    double[] yValues = {5, 6, 7, 8};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xValues, yValues);
    double[] xValues1 = {1, 2, 3, 4};
    double[] yValues1 = {13, 14, 15, 16};
    LinkedListTabulatedFunction list1 = new LinkedListTabulatedFunction(xValues1, yValues1);
    TabulatedFunctionOperationService operation = new TabulatedFunctionOperationService();
    @Test
    public void addTest() {
        TabulatedFunction func = operation.add(list, list1);
        for (int i = 0; i != func.getCount(); i++) {
            assertEquals(yValues[i] + yValues1[i], func.getY(i));
        }
    }

    @Test
    public void subtractTest() {
        TabulatedFunction func = operation.subtraction(list, list1);
        for (int i = 0; i != func.getCount(); i++) {
            assertEquals(yValues[i] - yValues1[i], func.getY(i));
        }
    }

    @Test
    public void multiplicationTest() {
        TabulatedFunction func = operation.multiplication(list, list1);
        for (int i = 0; i != func.getCount(); i++) {
            assertEquals(yValues[i] * yValues1[i], func.getY(i));
        }
    }

    @Test
    public void divisionTest() {
        TabulatedFunction func = operation.division(list, list1);
        for (int i = 0; i != func.getCount(); i++) {
            assertEquals(yValues[i] / yValues1[i], func.getY(i));
        }
    }
}