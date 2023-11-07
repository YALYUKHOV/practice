package functions;

import exceptions.InterpolationException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static org.junit.Assert.assertThrows;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;
    protected int count;

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the tabulated function.");
                }

                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }


    public ArrayTabulatedFunction(double[] xValues, double[] yValues ){
        if (xValues.length < 2 || yValues.length < 2) {
            throw new IllegalArgumentException("Table length must be at least 2 points");
        }

        // Проверка на одинаковую длину массивов
        AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);

        // Проверка на сортировку массива xValues
        AbstractTabulatedFunction.checkSorted(xValues);

        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        count = xValues.length;
    }
    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        if (count < 2) {
            throw new IllegalArgumentException("Table length must be at least 2 points");
        }
        xValues = new double[count];
        yValues = new double[count];
        double epsilon;
        if(xTo - xFrom > pow(10,-9))
        {
            epsilon = (xTo - xFrom)/(count-1);
            for(int i=0; i<count;i++)
            {
                xValues[i]=xFrom + i*epsilon;
                yValues[i]=source.apply(xFrom + i*epsilon);
            }
        }
        else if(xFrom - xTo > pow(10,-9))
        {

            epsilon = (xFrom - xTo)/(count-1);
            for(int i=0; i<count;i++)
            {
                xValues[i]=xFrom + i*epsilon;
                yValues[i]=source.apply(xFrom + i*epsilon);
            }
        }
        else {
            for(int i=0; i<count;i++) {
                xValues[i] = xFrom;
                yValues[i] = source.apply(xFrom);
            }
        }
    }
    public int getCount()
    {
        return count;
    }
    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
        return xValues[index];
    }
    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
        return yValues[index];
    }
    public void setY(int index, double val) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
        yValues[index] = val;
    }
    public void setX(int index, double val) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
        xValues[index] = val;
    }

    public double leftBound(){
        return xValues[0];
    }
    public double rightBound(){
        return xValues[count-1];
    }
    public int indexOfX(double x) {
        int i = 0;
        while (i < count)
        {
            if(abs(xValues[i] - x) < 1e-9)
                return i;
            i++;
        }
        return -1;
    }
    public int indexOfY(double y) {
        int i = 0;
        while (i < count)
        {
            if(abs(yValues[i] - y) < 1e-9)
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("x is less than the left bound");
        }
        int i = 0;
        if (xValues[i] - x > 1e-9)
            return 0;
        else if (x - xValues[count-1] > 1e-9)
            return count - 1;
        else {
            while(true) {
                if (abs(xValues[i] - x) < 1e-9)
                    return i;
                else if (xValues[i] - x > 1e-9)
                    return i - 1;
                i++;
            }
        }
    }
    protected double extrapolateLeft(double x) {
        if (count==1)
            return yValues[0];
        else
            return (yValues[0] + ((yValues[1] - yValues[0]) / (xValues[1] - xValues[0])) * (x - xValues[0]));
    }
    protected double extrapolateRight(double x) {
        if (count==1)
            return yValues[0];
        else
            return (yValues[count-2] + ((yValues[count-1] - yValues[count-2]) / (xValues[count-1] - xValues[count-2])) * (x - xValues[count-2]));
    }
    protected double interpolate(double x, int floorIndex) {
        if (count==1)
            return yValues[0];
        else {
            if (x < xValues[floorIndex] || x > xValues[floorIndex + 1]) {
                throw new InterpolationException("Value x is outside the interpolation interval.");
            }
            double rightX = getX(floorIndex);
            double leftX = getX(floorIndex - 1);
            double rightY = getY(floorIndex);
            double leftY = getY(floorIndex - 1);
            return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
        }
    }
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (count==1)
            return yValues[0];
        else
            return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
    }

    @Override
    public double apply(double x) {

        if(xValues[0]-x>1e-9){
            return extrapolateLeft(x);
        }
        else if(x - xValues[count-1]>1e-9){
            return extrapolateRight(x);
        }
        else{
            if(indexOfX(x)!= -1){
                return getY(indexOfX(x));
            }
            else{
                return interpolate(x, floorIndexOfX(x));
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < count; i++) {
            builder.append("(").append(xValues[i]).append("; ").append(yValues[i]).append(")");
            if (i < count - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayTabulatedFunction that = (ArrayTabulatedFunction) o;

        if (count != that.count) return false;
        return Arrays.equals(xValues, that.xValues) && Arrays.equals(yValues, that.yValues);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(xValues);
        result = 31 * result + Arrays.hashCode(yValues);
        return result;
    }

    public ArrayTabulatedFunction clone() {
        return new ArrayTabulatedFunction(Arrays.copyOf(xValues, count), Arrays.copyOf(yValues, count));
    }

    public double[] getXValues() {
        return xValues;
    }

    public double[] getYValues() {
        return yValues;
    }
}
