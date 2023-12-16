package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConstantFunctionTest {

    @Test
    void testConstantFunction() {
        ConstantFunction constantFunction = new ConstantFunction(5.0);
        assertEquals(5.0, constantFunction.apply(0.0), 0.0001);
        assertEquals(5.0, constantFunction.apply(10.0), 0.0001);
        assertEquals(5.0, constantFunction.getConstant(), 0.0001);
    }

    @Test
    void testZeroFunction() {
        ZeroFunction zeroFunction = new ZeroFunction();
        assertEquals(0.0, zeroFunction.apply(0.0), 0.0001);
        assertEquals(0.0, zeroFunction.apply(10.0), 0.0001);
        assertEquals(0.0, zeroFunction.getConstant(), 0.0001);
    }

    @Test
    void testUnitFunction() {
        UnitFunction unitFunction = new UnitFunction();
        assertEquals(1.0, unitFunction.apply(0.0), 0.0001);
        assertEquals(1.0, unitFunction.apply(10.0), 0.0001);
        assertEquals(1.0, unitFunction.getConstant(), 0.0001);
    }
}
