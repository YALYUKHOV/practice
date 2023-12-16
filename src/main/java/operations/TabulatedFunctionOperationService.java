package operations;

import exceptions.InconsistentFunctionsException;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import java.util.function.BinaryOperator;

public class TabulatedFunctionOperationService {

    private TabulatedFunctionFactory factory;
    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory GetFactory() {
        return factory;
    }
    public void SetFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int length = tabulatedFunction.getCount();
        Point [] pointArray = new Point[length];

        int i = 0;
        for (Point point: tabulatedFunction)
        {
            pointArray[i] = point;
            i++;
        }
        return pointArray;
    }
    private interface BiOperation{
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation)  {
        if (a.getCount() !=  b.getCount())
            try {
                throw new InconsistentFunctionsException("different lengths of functions arrays");
            } catch (InconsistentFunctionsException e) {
                throw new RuntimeException(e);
            }

        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);
        int count = a.getCount();

        double[] xValues = new double[count];
        double[] yValues = new double[count];

        for (int i = 0; i < count; i++) {
            xValues[i] = pointsA[i].x;
            yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
        }

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction Addition(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        BiOperation operation = (u, v) -> u + v;
        return doOperation(firstFunction, secondFunction, operation);
    }
    public TabulatedFunction Subtraction(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        BiOperation operation = (u, v) -> u - v;
        return doOperation(firstFunction, secondFunction, operation);
    }
    public TabulatedFunction multiply(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        BiOperation operation = (u, v) -> u * v;
        return doOperation(firstFunction, secondFunction, operation);
    }

    public TabulatedFunction divide(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        BiOperation operation = (u, v) -> {
            if (v != 0) {
                return u / v;
            } else {
                throw new ArithmeticException("Деление на 0");
            }
        };
        return doOperation(firstFunction, secondFunction, operation);
    }
    public enum Operation {
        Addition, Subtraction, multiply, divide
    }

}