package operations;

import functions.SqrFunction;
import operations.LeftSteppingDifferentialOperator;
import operations.MiddleSteppingDifferentialOperator;
import operations.RightSteppingDifferentialOperator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferentialOperatorTest {

    @Test
    public void testLeftSteppingDifferentialOperator() {
        // Создаем объект квадратичной функции
        SqrFunction sqrFunction = new SqrFunction();
        // Создаем дифференциальный оператор для левой разностной производной с шагом 0.1
        LeftSteppingDifferentialOperator leftOperator = new LeftSteppingDifferentialOperator(0.1);
        // Вычисляем производную квадратичной функции в точке 2.0
        double result = leftOperator.derive(sqrFunction).apply(2.0);
        // Ожидаемый результат: производная функции sqrFunction в точке 2.0 равна 4.0
        assertEquals(3.9, result, 1e-9);
    }

    @Test
    public void testRightSteppingDifferentialOperator() {
        // Аналогично тест для правой разностной производной
        SqrFunction sqrFunction = new SqrFunction();
        RightSteppingDifferentialOperator rightOperator = new RightSteppingDifferentialOperator(0.1);
        double result = rightOperator.derive(sqrFunction).apply(2.0);
        assertEquals(4.1, result, 1e-9);
    }

    @Test
    public void testMiddleSteppingDifferentialOperator() {
        // Тест для средней разностной производной
        SqrFunction sqrFunction = new SqrFunction();
        MiddleSteppingDifferentialOperator middleOperator = new MiddleSteppingDifferentialOperator(0.1);
        double result = middleOperator.derive(sqrFunction).apply(2.0);
        // Ожидаемый результат: производная функции sqrFunction в точке 2.0 равна 4.0
        assertEquals(4.0, result, 1e-9);
    }
}
