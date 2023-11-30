package operations;

import concurrent.SynchronizedTabulatedFunction;
import functions.MathFunction;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import operations.TabulatedFunctionOperationService;
import functions.Point;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        int size = points.length;
        double[] xValues = new double[size];
        double[] yValues = new double[size];

        for (int i = 0; i < size - 1; i++) {
            double h = points[i + 1].getX() - points[i].getX();
            xValues[i] = points[i].getX();
            yValues[i] = (points[i + 1].getY() - points[i].getY()) / h;
        }

        // Set the last point using the left difference
        xValues[size - 1] = points[size - 1].getX();
        yValues[size - 1] = (points[size - 1].getY() - points[size - 2].getY()) / (points[size - 1].getX() - points[size - 2].getX());

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        if (function instanceof SynchronizedTabulatedFunction) {
            return ((SynchronizedTabulatedFunction) function).doSynchronously(this::derive);
        } else {
            return new SynchronizedTabulatedFunction(function).doSynchronously(this::derive);
        }
    }
}
