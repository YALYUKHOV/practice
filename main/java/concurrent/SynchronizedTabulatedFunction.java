package concurrent;

import functions.Point;
import functions.TabulatedFunction;
import operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunction implements TabulatedFunction {

    private final TabulatedFunction function;

    private final Object lock;

    public SynchronizedTabulatedFunction(TabulatedFunction function) {

        this.function = function;
        this.lock = this;
    }


    @Override
    public double apply(double x) {
        synchronized (lock) {
            return function.apply(x);
        }
    }

    @Override
    public int getCount() {
        synchronized (lock) {
            return function.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (lock) {
            return function.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (lock) {
            return function.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (lock) {
            function.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (lock) {
            return function.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (lock) {
            return function.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (lock) {
            return function.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (lock) {
            return function.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (lock) {
            Point[] pointsList = TabulatedFunctionOperationService.asPoints(function);
            return new Iterator<Point>() {
                int i;

                @Override
                public boolean hasNext() {
                    return i < pointsList.length;
                }

                @Override
                public Point next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    Point point = pointsList[i];
                    ++i;
                    return point;
                }
            };
        }
    }

    public <T> T doSynchronously(Operation<T> op) {
        synchronized (lock) {
            return op.apply(this);
        }
    }

    public interface Operation<T> {
        T apply(SynchronizedTabulatedFunction function);
    }
}