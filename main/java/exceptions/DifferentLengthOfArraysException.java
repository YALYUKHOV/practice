package exceptions;

public class DifferentLengthOfArraysException extends RuntimeException {
    public DifferentLengthOfArraysException() {
        super("Arrays have different lengths.");
    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
