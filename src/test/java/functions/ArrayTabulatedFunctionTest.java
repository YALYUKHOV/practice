package functions;
import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import exceptions.InterpolationException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import junit.framework.Assert;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunctionTest {
    double[] xValue = {1, 1.5, 2, 2.5, 3};
    double[] yValue = {2, 3, 4, 5, 6};
    ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValue, yValue);

    @Test
    public void getCount() {
        assertEquals(5, arrayTabulatedFunction.getCount());
        assertNotEquals(0, arrayTabulatedFunction.getCount());
    }

    @Test
    public void getX() {
        assertEquals(1.5, arrayTabulatedFunction.getX(1));
        assertNotEquals(0, arrayTabulatedFunction.getX(3));
    }

    @Test
    public void getY() {
        assertEquals(3, arrayTabulatedFunction.getY(1));
        assertNotEquals(0, arrayTabulatedFunction.getY(2));
    }

    @Test
    public void setY() {
        arrayTabulatedFunction.setY(0, 7);
        assertEquals(7, arrayTabulatedFunction.getY(0));
        assertNotEquals(0, arrayTabulatedFunction.getY(0));
    }

    @Test
    public void leftBound() {
        assertEquals(1, arrayTabulatedFunction.leftBound());
        assertNotEquals(0, arrayTabulatedFunction.leftBound());
    }

    @Test
    public void rightBound() {
        assertEquals(3, arrayTabulatedFunction.rightBound());
        assertNotEquals(0, arrayTabulatedFunction.rightBound());
    }

    @Test
    public void indexOfX() {
        assertEquals(1, arrayTabulatedFunction.indexOfX(1.5));
        assertNotEquals(0, arrayTabulatedFunction.indexOfX(1.5));
    }

    @Test
    public void indexOfY() {
        assertEquals(1, arrayTabulatedFunction.indexOfY(3));
        assertNotEquals(0, arrayTabulatedFunction.indexOfY(3));
    }

    @Test
    public void floorIndexOfX() {
        assertEquals(5, arrayTabulatedFunction.floorIndexOfX(10));
        assertNotEquals(0, arrayTabulatedFunction.floorIndexOfX(10));

        assertEquals(1, arrayTabulatedFunction.floorIndexOfX(1.5));
        assertNotEquals(0, arrayTabulatedFunction.floorIndexOfX(1.5));

        assertEquals(3, arrayTabulatedFunction.floorIndexOfX(2.7));
        assertNotEquals(0, arrayTabulatedFunction.floorIndexOfX(2.7));
    }

    @Test
    public void interpolate() {
        assertEquals(3.4, arrayTabulatedFunction.interpolate(1.7, 2));
        assertNotEquals(0, arrayTabulatedFunction.interpolate(1.7, 2));
    }

    @Test
    public void extrapolateLeft() {
        assertEquals(-10, arrayTabulatedFunction.extrapolateLeft(-5));
        assertNotEquals(0, arrayTabulatedFunction.extrapolateLeft(-5));
    }

    @Test
    public void extrapolateRight() {
        assertEquals(20, arrayTabulatedFunction.extrapolateRight(10));
        assertNotEquals(0, arrayTabulatedFunction.extrapolateRight(10));
    }

    /*@Test
    public void toStringTest() {
        assertEquals("(1.0;2.0) (1.5;3.0) (2.0;4.0) (2.5;5.0) (3.0;6.0) ", arrayTabulatedFunction.toString());
        assertNotEquals("(0;0)", arrayTabulatedFunction.toString());
    } */

    @Test
    public void equalsTest() {
        ArrayTabulatedFunction arrayTabulatedFunctionTest = new ArrayTabulatedFunction(xValue, yValue);
        assertTrue(arrayTabulatedFunction.equals(arrayTabulatedFunctionTest));
    }

    @Test
    public void cloneTest() {
        Object arrayTabulatedFunctionTest = arrayTabulatedFunction.clone();
        assertTrue(arrayTabulatedFunction.equals(arrayTabulatedFunctionTest));
    }

    @Test
    public void hashCodeTest() {
        ArrayTabulatedFunction arrayTabulatedFunction3 = new ArrayTabulatedFunction(xValue, yValue);
        assertEquals(arrayTabulatedFunction.hashCode(), arrayTabulatedFunction3.hashCode());
    }

    @Test
    public void ArrayTwoTestException() {
        boolean exceptionThrown = false;
        double[] xValue2 = {5};
        double[] yValue2 = {2};
        try {
            ArrayTabulatedFunction arrTabulatedFunction2 = new ArrayTabulatedFunction(xValue2, yValue2);
        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void ArrayTabulatedFunctionLengthException() {
        double[] xValue2 = {5, 6, 5};
        double[] yValue2 = {2, 6, 7, 95};
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            ArrayTabulatedFunction arrTabulatedFunction2 = new ArrayTabulatedFunction(xValue2, yValue2);
        });
    }

    @Test
    public void ArrayTabulatedFunctionSortedException() {
        double[] xValue5 = {2, 3, 4, 17, 3, 45, 0};
        double[] yValue5 = {2, 34, 5, 56, 7, 6, 5};
        assertThrows(ArrayIsNotSortedException.class, () -> {
            ArrayTabulatedFunction arrTabulatedFunction25 = new ArrayTabulatedFunction(xValue5, yValue5);
        });
    }

    @Test
    public void interpolateTestException() {
        assertThrows(InterpolationException.class, () -> {
            arrayTabulatedFunction.interpolate(2.5, 2);
        });
    }

    @Test
    public void arrayTabulatedIteratorTestException() {
        Iterator<Point> iterator = arrayTabulatedFunction.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(xValue[i], point.x);
            assertEquals(yValue[i], point.y);
            ++i;
        }
        i = 0;
        for (Point point : arrayTabulatedFunction) {
            assertEquals(xValue[i], point.x);
            assertEquals(yValue[i], point.y);
            ++i;
        }
    }

    @Test
    public void toSting2Test() {
        double[] xValueS = {0, 0.5, 1};
        double[] yValueS = {0, 0.25, 1};
        ArrayTabulatedFunction arrTest = new ArrayTabulatedFunction(xValueS, yValueS);
        assertEquals(arrTest.toString(), "ArrayTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]\n");
    }
}