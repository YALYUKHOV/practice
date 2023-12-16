package functions;
import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected abstract double interpolate(double x, double leftX, double rightX, double leftY, double rightY);

    public abstract double apply(double x);

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Массивы разной длины");
        }
    }

    public static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if (xValues[i] > xValues[i + 1]) throw new ArrayIsNotSortedException("массив не отсортирован");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" size = ");
        sb.append(getCount());
        sb.append("\n");
        for (Point point : this) {
            sb.append("[");
            sb.append(point.x);
            sb.append("; ");
            sb.append(point.y);
            sb.append("]\n");
        }
        return sb.toString();
    }
}