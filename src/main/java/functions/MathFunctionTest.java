package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathFunctionTest {

    @Test
    void testAndThen() {
        // Создаем функции для тестирования
        MathFunction f1 = new SqrFunction();
        MathFunction f2 = new SinFunction();
        MathFunction f3 = new IdentityFunction();

        // Создаем цепочки функций
        MathFunction composite1 = f1.andThen(f2).andThen(f3);
        MathFunction composite2 = f1.andThen(f1);

        assertEquals(0.0, composite1.apply(0.0), 0.0001);
        assertEquals(1, composite1.apply(Math.PI / 2), 0.0001);

        assertEquals(16.0, composite2.apply(2.0), 0.0001);
        assertEquals(256.0, composite2.apply(4.0), 0.0001);
    }
}
