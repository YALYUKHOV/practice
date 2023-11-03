package functions;


import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {
    double[] array1 = {7.0, 8.1, 9.2, 10.3, 11.4};
    double[] array2 = {12.5, 13.6, 14.7, 15.8, 16.9};
    ArrayTabulatedFunction arr = new ArrayTabulatedFunction(array1, array2);

    @Test
    public void testgetCount() throws Exception{
        int result = arr.getCount();
        assertEquals(5, result, 1e-9);
    }

    @Test
    public void testleftBound() throws Exception{
        double result = arr.leftBound();
        assertEquals(7.0, result, 1e-9);
    }

    @Test
    public void testrightBound() throws Exception{
        double result = arr.rightBound();
        assertEquals(11.4, result, 1e-9);
    }

    @Test
    public void testgetX() throws Exception{
        double result = arr.getX(2);
        assertEquals(9.2, result, 1e-9);
    }

    @Test
    public void testgetY() throws Exception{
        double result = arr.getY(1);
        assertEquals(13.6, result, 1e-9);
    }

    @Test
    public void testindexOfX() throws Exception{
        int result = arr.indexOfX(2.6);
        assertEquals(-1, result, 1e-9);
    }

    @Test
    public void testindexOfY() throws Exception{
        int result = arr.indexOfY(22);
        assertEquals(-1, result, 1e-9);
    }

    @Test
    public void testfloorIndexOfX()throws Exception {
        int result = arr.floorIndexOfX(9.3);
        assertEquals(2, result, 1e-9);
    }

    @Test
    public void testextrapolateLeft() throws Exception {
        double result = arr.extrapolateLeft(7.0);
        assertEquals(12.5, result, 1e-9);
    }

    @Test
    public void testextrapolateRight() throws Exception {
        double result = arr.extrapolateRight(7.0);
        assertEquals(12.5, result, 1e-9);
    }

    @Test
    public void testinterpolate1() throws Exception {
        double result = arr.interpolate(3.7, 2);
        assertEquals(9.2, result, 1e-9);
    }

    @Test
    public void testinterpolate11() throws Exception {
        double result = arr.interpolate(5.3, 4.8, 5.9, 9.4, 10.5);
        assertEquals(9.9, result, 1e-9);
    }

    @Test
    public void testToString() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{4.0, 5.0, 6.0});
        String expected = "[(1.0; 4.0), (2.0; 5.0), (3.0; 6.0)]";
        assertEquals(expected, function.toString());
    }

    @Test
    public void testEquals() {
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{4.0, 5.0, 6.0});
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{4.0, 5.0, 6.0});
        ArrayTabulatedFunction function3 = new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{4.0, 5.0, 7.0});

        assertTrue(function1.equals(function2));
        assertFalse(function1.equals(function3));
    }

    @Test
    public void testHashCode() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{4.0, 5.0, 6.0});

        int xValuesHashCode = java.util.Arrays.hashCode(function.getXValues());
        int yValuesHashCode = java.util.Arrays.hashCode(function.getYValues());
        int expectedHashCode = xValuesHashCode * 31 + yValuesHashCode;

        assertEquals(expectedHashCode, function.hashCode());
    }

    @Test
    public void testClone() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{4.0, 5.0, 6.0});
        ArrayTabulatedFunction clonedFunction = function.clone();

        assertEquals(function, clonedFunction);
        assertNotSame(function, clonedFunction);
    }
}
