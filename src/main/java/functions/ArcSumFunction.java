package functions;

public class ArcSumFunction implements MathFunction{
    @Override
    public double apply(double x){
        return (Math.asin(x)+Math.acos(x));
    }
}
