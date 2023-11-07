package exceptions;

public class InterpolationException extends RuntimeException {
    public InterpolationException() {
        super("Interpolation error.");
    }

    public InterpolationException(String message) {
        super(message);
    }
}
