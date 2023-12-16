package functions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import junit.framework.Assert;
public class CompositeFunctionTest {
    MathFunction intFunc = new Integral(0.0, 2.0, new MathFunction() {
        @Override
        public double apply(double x) {
            return Math.pow(x, 2);
        }
    });
    MathFunction sqrFunc = new SqrFunction();
    MathFunction unitFunc = new UnitFunction();
    MathFunction test3 = new CompositeFunction(sqrFunc, intFunc);
    MathFunction test4 = new CompositeFunction(intFunc, unitFunc);

    @Test
    public void TestEquals() {
        Assert.assertEquals(2.6666666699994988, test3.apply(0));
        Assert.assertEquals(1.0, test4.apply(0));
    }
}