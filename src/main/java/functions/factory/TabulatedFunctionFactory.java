package functions.factory;

import functions.MathFunction;
import functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] var1, double[] var2);
    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);
}