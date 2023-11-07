package functions;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the tabulated function.");
                }
                Point point = new Point(node.x, node.y);
                node = node.next;
                return point;
            }
        };
    }
    static class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;
        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public String toString() {
            return "(" + x + "; " + y + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return Double.compare(node.x, x) == 0 && Double.compare(node.y, y) == 0;
        }

        @Override
        public int hashCode() {
            int result = Double.hashCode(x);
            result = 31 * result + Double.hashCode(y);
            return result;
        }

        public Node clone() {
            return new Node(x, y);
        }
    }
    protected int count;


    private Node head;
    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        }
        else {
            Node last = head.prev;
            last.next = newNode;
            head.prev = newNode;
            newNode.prev = last;
            newNode.next = head;
        }
        count++;
    }
    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }

        // Проверка на одинаковую длину массивов
        AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);

        // Проверка на сортировку массива xValues
        AbstractTabulatedFunction.checkSorted(xValues);
    }
    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xTo - xFrom > 1e-9) {
            double epsilon = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                addNode((xFrom + i * epsilon), source.apply(xFrom + i * epsilon));
            }
        }
        else if (xFrom - xTo > 1e-9) {
            double epsilon = (xFrom - xTo) / (count - 1);
            for (int i = 0; i < count; i++) {
                addNode((xTo + i * epsilon), source.apply(xTo + i * epsilon));
            }
        }
        else {
            for (int i = 0; i > count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        }
    }
    public int getCount() {
        return count;
    }
    public double leftBound() {
        return head.x;
    }
    public double rightBound() {
        return head.prev.x;
    }
    private Node getNode(int index) {
        if (index == 0) {
            return head;
        }
        else {
            Node tempNode = head;
            while (index != 0) {
                tempNode = tempNode.next;
                index--;
            }
            return tempNode;
        }
    }
    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        return getNode(index).x;
    }



    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        return getNode(index).y;
    }
    public void setY(int index, double y) {
        this.getNode(index).y = y;
    }
    public int indexOfX(double x) {
        Node tempNode = head;
        int index = 0;
        while (tempNode.x != x && tempNode != head.prev) {
            tempNode = tempNode.next;
            index++;
        }
        if (tempNode == head.prev) {
            if (tempNode.x - x == 1e-9) {
                return index;
            }
            else
                return -1;
        }
        else
            return index;
    }
    public int indexOfY(double y) {
        Node tempNode = head;
        int index = 0;
        while (tempNode.y != y && tempNode != head.prev) {
            tempNode = tempNode.next;
            index++;
        }
        if (tempNode == head.prev) {
            if (tempNode.y == y) {
                return index;
            }
            else
                return -1;
        }
        else
            return index;
    }
    public int floorIndexOfX(double x) {

        if (x < leftBound()) {
            throw new IllegalArgumentException("x is below the left table boundary");
        }
        if (x > rightBound()) {
            throw new IllegalArgumentException("x is above the right table boundary");
        }


        if (head.x - x > 1e-9)
            return 0;
        else if (head.prev.x - x < 1e-9)
            return count - 1;
        else {
            int index = 0;
            Node tempNode = head;
            while(true) {
                if (tempNode.x - x == 1e-9)
                    return index;
                else if (tempNode.x - x > 1e-9)
                    return (index - 1);
                tempNode = tempNode.next;
                index++;
            }
        }
    }
    protected double extrapolateLeft(double x) {
        if (head.prev == head)
            return head.y;
        else
            return (head.y + ((head.next.y - head.y) / (head.next.x - head.x)) * (x - head.x));
    }
    protected double extrapolateRight(double x) {
        if (head.prev == head)
            return head.y;
        else
            return (head.prev.prev.y + ((head.prev.y - head.prev.prev.y) / (head.prev.x - head.prev.prev.x)) * (x - head.prev.prev.x));
    }
    protected double interpolate(double x, int floorIndex) {
        if (head.prev == head)
            return head.y;
        else {
            double rightX = getX(floorIndex);
            double leftX = getX(floorIndex - 1);
            double rightY = getY(floorIndex);
            double leftY = getY(floorIndex - 1);
            return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
        }
    }
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (head.prev == head)
            return head.y;
        else
            return (leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX));
    }
    @Override
    public double apply(double x) {
        if (head.x - x > 1e-9){
            return extrapolateLeft(x);
        }
        else if (x - head.prev.x > 1e-9) {
            return extrapolateRight(x);
        }
        else {
            if (indexOfX(x) != -1) {
                return getY(indexOfX(x));
            }
            else {
                return interpolate(x, floorIndexOfX(x));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node currentNode = head;
        for (int i = 0; i < count; i++) {
            sb.append("(").append(currentNode.x).append("; ").append(currentNode.y).append(")");
            if (i < count - 1) {
                sb.append(", ");
            }
            currentNode = currentNode.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListTabulatedFunction that = (LinkedListTabulatedFunction) o;
        if (this.getCount() != that.getCount()) return false;
        Node thisNode = this.head;
        Node thatNode = that.head;
        for (int i = 0; i < this.getCount(); i++) {
            if (thisNode.x != thatNode.x || thisNode.y != thatNode.y) return false;
            thisNode = thisNode.next;
            thatNode = thatNode.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Node currentNode = head;
        for (int i = 0; i < count; i++) {
            result = 31 * result + Double.hashCode(currentNode.x);
            result = 31 * result + Double.hashCode(currentNode.y);
            currentNode = currentNode.next;
        }
        return result;
    }

    @Override
    public Object clone() {
        double[] clonedXValues = new double[count];
        double[] clonedYValues = new double[count];
        Node currentNode = head;
        for (int i = 0; i < count; i++) {
            clonedXValues[i] = currentNode.x;
            clonedYValues[i] = currentNode.y;
            currentNode = currentNode.next;
        }
        return new LinkedListTabulatedFunction(clonedXValues, clonedYValues);
    }
}
