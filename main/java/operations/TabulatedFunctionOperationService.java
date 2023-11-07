package operations;

import exceptions.InconsistentFunctionsException;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int size = tabulatedFunction.getCount();
        Point[] points = new Point[size];
        int i=0;
        for (Point point : tabulatedFunction) {
            points[i] = point;
            i++;
        }

        return points;
    }

    private TabulatedFunctionFactory factory;


    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory(); // Значение по умолчанию
    }
    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }



    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        // Проверьте, что a и b имеют одинаковое количество записей и соответствующие x-значения
        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException("Inconsistent functions for binary operation");
        }

        int size = a.getCount();
        double[] xValues = new double[size];
        double[] yValues = new double[size];

        for (int i = 0; i < size; i++) {
            double x = a.getX(i);
            double y1 = a.getY(i);
            double y2 = b.getY(i);
            double result = operation.apply(y1, y2);

            xValues[i] = x;
            yValues[i] = result;
        }

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction add(TabulatedFunction a, TabulatedFunction b) {
        BiOperation op = (u, v) -> u + v;
        return doOperation(a, b, op);
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, new BiOperation() {
            @Override
            public double apply(double u, double v) {
                return u - v;
            }
        });
    }

    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u * v);
    }

    public TabulatedFunction divide(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u / v);
    }
}

