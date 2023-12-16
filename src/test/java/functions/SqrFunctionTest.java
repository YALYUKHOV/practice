package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SqrFunctionTest {

    @Test
    void testApply() {
        MathFunction sqrFunction = new SqrFunction();

        assertEquals(0.0, sqrFunction.apply(0.0), 0.0001);
        assertEquals(1.0, sqrFunction.apply(1.0), 0.0001);
        assertEquals(4.0, sqrFunction.apply(2.0), 0.0001);
        assertEquals(25.0, sqrFunction.apply(5.0), 0.0001);

    }
}
