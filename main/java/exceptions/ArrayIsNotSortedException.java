package exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    public ArrayIsNotSortedException() {
        super("Array is not sorted.");
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}