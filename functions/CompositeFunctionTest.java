package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeFunctionTest {

    @Test
    void testApply() {
        // Создаем функции для тестирования
        MathFunction f1 = new SqrFunction();
        MathFunction f2 = new SinFunction();
        MathFunction f3 = new IdentityFunction();

        // Создаем композицию f2(f1(x))
        MathFunction composite1 = new CompositeFunction(f2, f1);

        // Создаем композицию f3(f2(x))
        MathFunction composite2 = new CompositeFunction(f3, f2);

        assertEquals(0.0, composite1.apply(0.0), 0.0001);
        assertEquals(1.0, composite2.apply(Math.PI / 2), 0.0001);

    }
}
