package functions;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MockTabulatedFunction extends AbstractTabulatedFunction {
    private double x0, x1, y0, y1;

    public MockTabulatedFunction(double x0, double x1, double y0, double y1) {
        if (x0 > x1) {
            throw new IllegalArgumentException("x0 should be less than or equal to x1");
        }
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
        count = 2;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < x0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (floorIndex == 0) {
            return interpolate(x, x0, x1, y0, y1);
        }
        return Double.NaN;
    }

    @Override
    public int indexOfX(double x) {
        if (x >= x0 && x <= x1) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int indexOfY(double y) {
        if (y == y0 || y == y1) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public double getX(int index) {
        if (index == 0) {
            return x0;
        } else if (index == 1) {
            return x1;
        }
        throw new IllegalArgumentException("Invalid index");
    }

    @Override
    public double getY(int index) {
        if (index == 0) {
            return y0;
        } else if (index == 1) {
            return y1;
        }
        throw new IllegalArgumentException("Invalid index");
    }

    @Override
    public void setY(int index, double value) {
        if (index == 0) {
            y0 = value;
        } else if (index == 1) {
            y1 = value;
        } else {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    @Override
    public double leftBound() {
        return x0;
    }

    @Override
    public double rightBound() {
        return x1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return y0;
    }

    @Override
    protected double extrapolateRight(double x) {
        return y1;
    }


    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (currentIndex == 0) {
                    currentIndex++;
                    return new Point(x0, y0);
                } else {
                    currentIndex++;
                    return new Point(x1, y1);
                }
            }
        };
    }
}
