package functions;

public class Integral implements MathFunction {

    private static final double INCREMENT = 1E-4;
    private final double lowLim;
    private final double upLim;
    private MathFunction func;

    public Integral(double lowLim, double upLim, MathFunction Func) {
        this.lowLim = lowLim;
        this.upLim = upLim;
        this.func = Func;
    }

    private double IntegralCount(double a, double b, MathFunction function) {
        double area = 0;
        if (a > b) {
            double tempA = a;
            a = b;
            b = tempA;
        }
        for (double i = a + INCREMENT; i < b; i += INCREMENT) {
            double dFromA = i - a;
            area += (INCREMENT / 2) * (function.apply(a + dFromA) + function.apply(a + dFromA - INCREMENT));
        }
        return area;
    }

    public double apply(double x) {
        return IntegralCount(lowLim, upLim, func);
    }
}