package concurrent;

import functions.ArrayTabulatedFunction;
import functions.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SynchronizedTabulatedFunctionTest {

    double[] X = {2, 5, 7};
    double[] Y = {-4, 12, 6};

    SynchronizedTabulatedFunction syncTabFunc = new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(X, Y));

    @Test
    void methodsTest() {

        assertEquals(12, syncTabFunc.apply(5));
        assertEquals(3, syncTabFunc.getCount());
        assertEquals(7, syncTabFunc.getX(2));
        assertEquals(-4, syncTabFunc.getY(0));
        syncTabFunc.setY(1, -43);
        assertEquals(-43, syncTabFunc.getY(1));
        // {-4,-43,6}
        assertEquals(-1, syncTabFunc.indexOfX(12));
        assertEquals(1, syncTabFunc.indexOfY(-43));
        assertEquals(2, syncTabFunc.leftBound());
        assertEquals(7, syncTabFunc.rightBound());
    }

    @Test
    void iteratorTest() {
        int i = 0;
        for (Point point : syncTabFunc) {
            assertEquals(point.x, syncTabFunc.getX(i));
            assertEquals(point.y, syncTabFunc.getY(i));
            ++i;
        }
    }

    @Test
    void doSyncronouslyTest() {

        SynchronizedTabulatedFunction.Operation<double[]> op1 = function -> {
            double[] arr = new double[function.getCount()];
            for (int i = 0; i < function.getCount(); i++) {
                arr[i] = function.getY(i);
            }
            return arr;
        };
        double[] arr = syncTabFunc.doSynchronously(op1);
        for (int i = 0; i < syncTabFunc.getCount(); i++) {
            assertEquals(arr[i], syncTabFunc.getY(i));
        }

        SynchronizedTabulatedFunction.Operation<Void> op2 = function -> {


            syncTabFunc.setY(0, syncTabFunc.getY(0) * 2);
            syncTabFunc.setY(1, syncTabFunc.getY(1) * 2);

            return null;

        };

        op2.apply(syncTabFunc);

        for (int i = 0; i < 2; i++) {
            assertEquals(Y[i] * 2, syncTabFunc.getY(i));
        }

        SynchronizedTabulatedFunction.Operation<Double> op3 = function -> function.leftBound();

        assertEquals(syncTabFunc.doSynchronously(op3), 2);


    }
}