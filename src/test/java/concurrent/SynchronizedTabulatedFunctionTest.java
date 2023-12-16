package concurrent;

import functions.ArrayTabulatedFunction;
import functions.Point;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SynchronizedTabulatedFunctionTest {
    double[] xValues = {1, 2, 3, 4};
    double[] yValues = {5, 6, 7, 8};
    ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
    SynchronizedTabulatedFunction syncFunction = new SynchronizedTabulatedFunction(function);

    @Test
    public void getCountTest() {
        assertEquals(4, syncFunction.getCount());
    }

    @Test
    public void getXTest() {
        assertEquals(2, syncFunction.getX(1));
    }

    @Test
    public void getYTest() {
        assertEquals(6, syncFunction.getY(1));
    }

    @Test
    public void setYTest() {
        syncFunction.setY(2, 5.5);
        assertEquals(5.5, syncFunction.getY(2));
    }

    @Test
    public void indexOfXTest() {
        assertEquals(3, syncFunction.indexOfX(4));
    }

    @Test
    public void indexOfYTest() {
        assertEquals(3, syncFunction.indexOfY(8));
    }

    @Test
    public void rightBoundTest() {
        assertEquals(4, syncFunction.rightBound());
    }

    @Test
    public void leftBoundTest() {
        assertEquals(1, syncFunction.leftBound());
    }

    @Test
    public void iteratorTest() {
        Iterator<Point> iterator = syncFunction.iterator();
        int i = 0;
        for (; iterator.hasNext(); i++) {
            Point point = iterator.next();
            assertEquals(xValues[i], point.x);
            assertEquals(yValues[i], point.y);
        }
    }
    @Test
    public void doSynchronously() {
        SynchronizedTabulatedFunction.Operation<Double> operation = func -> {
            double sum = 0;
            for (Point el : syncFunction)
                sum += el.y;
            return sum;
        };
        double sumOfY = syncFunction.doSynchronously(operation);
        assertEquals(26, sumOfY);
    }
}