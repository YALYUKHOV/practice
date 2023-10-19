package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdentityFunctionTest {
    @Test
    void testApply() {
        MathFunction identityFunction = new IdentityFunction();

        assertEquals(0.0, identityFunction.apply(0.0), 0.0001);
        assertEquals(1.0, identityFunction.apply(1.0), 0.0001);
        assertEquals(-2.5, identityFunction.apply(-2.5), 0.0001);
    }
    @Test
    public void testToString() {
        MathFunction identity = new IdentityFunction();
        assertEquals("IdentityFunction", identity.toString());
    }

    @Test
    public void testEquals() {
        MathFunction identity1 = new IdentityFunction();
        MathFunction identity2 = new IdentityFunction();
        MathFunction sqrFunction = new SqrFunction();

        assertTrue(identity1.equals(identity2));
        assertTrue(identity2.equals(identity1));
        assertFalse(identity1.equals(sqrFunction));
    }

    @Test
    public void testHashCode() {
        MathFunction identity1 = new IdentityFunction();
        MathFunction identity2 = new IdentityFunction();

        assertEquals(identity1.hashCode(), identity2.hashCode());
    }

    @Test
    public void testClone() {
        MathFunction identity1 = new IdentityFunction();
        MathFunction clonedIdentity = new IdentityFunction(); // Создаем новый экземпляр
        assertEquals(identity1, clonedIdentity);
        assertNotSame(identity1, clonedIdentity); // Проверяем, что это разные объекты
    }
}
