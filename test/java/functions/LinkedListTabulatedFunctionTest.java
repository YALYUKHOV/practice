package functions;


import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

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





    @Test
    public void testGetXWithInvalidIndex() {
        // Создаем объект с допустимыми данными
        double[] validXValues = {1.0, 2.0, 3.0};
        double[] validYValues = {4.0, 5.0, 6.0};
        LinkedListTabulatedFunction validFunction = new LinkedListTabulatedFunction(validXValues, validYValues);

        // Попробуем получить X с недопустимым индексом
        assertThrows(IllegalArgumentException.class, () -> {
            double x = validFunction.getX(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            double x = validFunction.getX(validFunction.getCount());
        });
    }

    @Test
    public void testGetYWithInvalidIndex() {
        // Создаем объект с допустимыми данными
        double[] validXValues = {1.0, 2.0, 3.0};
        double[] validYValues = {4.0, 5.0, 6.0};
        LinkedListTabulatedFunction validFunction = new LinkedListTabulatedFunction(validXValues, validYValues);

        // Попробуем получить Y с недопустимым индексом
        assertThrows(IllegalArgumentException.class, () -> {
            double y = validFunction.getY(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            double y = validFunction.getY(validFunction.getCount());
        });
    }


    @Test
    public void testCheckLengthIsTheSame() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};
        // Создаем объект LinkedListTabulatedFunction
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        // Проверяем, что метод не выбрасывает исключение
        assertDoesNotThrow(() -> function.checkLengthIsTheSame(xValues, yValues));
    }

    @Test
    public void testCheckSorted() {
        double[] xValues = {1.0, 2.0, 3.0};
        // Создаем объект LinkedListTabulatedFunction
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, xValues);

        // Проверяем, что метод не выбрасывает исключение
        assertDoesNotThrow(() -> function.checkSorted(xValues));
    }

    @Test
    public void testCheckLengthIsTheSameException() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0};

        // Проверяем, что метод checkLengthIsTheSame выбрасывает исключение DifferentLengthOfArraysException
        assertThrows(DifferentLengthOfArraysException.class, () -> LinkedListTabulatedFunction.checkLengthIsTheSame(xValues, yValues));
    }

    @Test
    public void testCheckSortedException() {
        double[] xValues = {1.0, 2.0, 3.0}; // Отсортированный массив
        // Создаем объект LinkedListTabulatedFunction
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, xValues);
    }




    /*@Test
    public void testiterator2() throws Exception {
        Iterator<Point> iterator = list.iterator();
        double sum_node_x = 0;
        double sum_node_y = 0;
        double sum_point_x = 0;
        double sum_point_y = 0;
        for (Point point : list) {
            sum_node_x += point.getX();
            sum_point_x += point.getX();
            sum_node_y += point.getY();
            sum_point_y += point.getY();
        }
        assertEquals(sum_node_x, sum_point_x, 1e-9);
        assertEquals(sum_node_y, sum_point_y, 1e-9);
    }*/

    @Test
    void ToString() {
        double[] arrayOfX = {3, 4, 6};
        double[] arrayOfY = {5, 2, -2};
        LinkedListTabulatedFunction tabFunc = new LinkedListTabulatedFunction(arrayOfX, arrayOfY);
        AbstractTabulatedFunction func = new LinkedListTabulatedFunction(arrayOfX, arrayOfY);
        assertEquals("LinkedListTabulatedFunction size = 3\n[3.0; 5.0]\n[4.0; 2.0]\n[6.0; -2.0]\n",
                func.toString());
    }


}
