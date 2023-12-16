package functions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import junit.framework.Assert;

public class IdentityFunctionTest {
    MathFunction test=new IdentityFunction();
    @Test
    public void test(){
        Assert.assertEquals(15.0, test.apply(15));
    }
    @Test
    public void toStringTest() {
        MathFunction identityFunction = new IdentityFunction();
        String str = identityFunction.toString();
        boolean bool = str.equals("IdentityFunction{}");
        assertTrue(bool);
    }

    @Test
    public void equalsTest() {
        MathFunction identityFunction = new IdentityFunction();
        MathFunction identityFunction2 = new IdentityFunction();
        boolean bool = identityFunction.equals(identityFunction2);
        assertTrue(bool);
    }

    @Test
    public void cloneTest() {
        IdentityFunction identityFunction = new IdentityFunction();
        boolean bool = identityFunction.equals(identityFunction.clone());
        assertTrue(bool);
    }
    @Test
    public void testHashCode() {
        IdentityFunction f1 = new IdentityFunction();
        IdentityFunction f2 = new IdentityFunction();
        Assert.assertEquals(f1.hashCode(), f2.hashCode());
    }
}