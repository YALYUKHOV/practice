package functions;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTabulatedFunctionTest {
    double[] array1 = {1.5, 2.6, 3.7, 4.8, 5.9};
    double[] array2 = {6.1, 7.2, 8.3, 9.4, 10.5};
    LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(array1, array2);

    @Test
    public void testgetCount() throws Exception {
        int result = list.getCount();
        assertEquals(5, result, 1e-9);
    }

    @Test
    public void testleftBound() throws Exception {
        double result = list.leftBound();
        assertEquals(1.5, result, 1e-9);
    }

    @Test
    public void testrightBound() throws Exception {
        double result = list.rightBound();
        assertEquals(5.9, result, 1e-9);
    }

    @Test
    public void testgetX() throws Exception {
        double result = list.getX(3);
        assertEquals(4.8, result, 1e-9);
    }

    @Test
    public void testgetY() throws Exception {
        double result = list.getY(3);
        assertEquals(9.4, result, 1e-9);
    }

    @Test
    public void testindexOfX() throws Exception {
        int result = list.indexOfX(2.6);
        assertEquals(1, result, 1e-9);
    }

    @Test
    public void testindexOfY() throws Exception {
        int result = list.indexOfY(7.2);
        assertEquals(1, result, 1e-9);
    }

    @Test
    public void testfloorIndexOfX() throws Exception {
        int result = list.floorIndexOfX(3);
        assertEquals(1, result, 1e-9);
    }

    @Test
    public void testextrapolateLeft() throws Exception {
        double result = list.extrapolateLeft(1.5);
        assertEquals(6.1, result, 1e-9);
    }

    @Test
    public void testextrapolateRight() throws Exception {
        double result = list.extrapolateRight(5.9);
        assertEquals(10.5, result, 1e-9);
    }

    @Test
    public void testinterpolate1() throws Exception {
        double result = list.interpolate(3.7, 2);
        assertEquals(8.3, result, 1e-9);
    }

    @Test
    public void testinterpolate11() throws Exception {
        double result = list.interpolate(5.3, 4.8, 5.9, 9.4, 10.5);
        assertEquals(9.9, result, 1e-9);
    }

    @Test
    public void testToString() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 6.0}
        );
        String expected = "[(1.0; 4.0), (2.0; 5.0), (3.0; 6.0)]";
        assertEquals(expected, function.toString());
    }

    @Test
    public void testEquals() {
        LinkedListTabulatedFunction function1 = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 6.0}
        );

        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 6.0}
        );

        LinkedListTabulatedFunction function3 = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 7.0} // Different y-values
        );

        assertTrue(function1.equals(function2));
        assertFalse(function1.equals(function3));
    }

    @Test
    public void testHashCode() {
        LinkedListTabulatedFunction function1 = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 6.0}
        );

        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 6.0}
        );

        assertEquals(function1.hashCode(), function2.hashCode());
    }

    @Test
    public void testClone() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 6.0}
        );

        LinkedListTabulatedFunction clonedFunction = (LinkedListTabulatedFunction) function.clone();

        assertTrue(function.equals(clonedFunction));
        assertNotSame(function, clonedFunction);
    }


}
