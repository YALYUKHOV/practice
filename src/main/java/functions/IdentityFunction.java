package functions;
public  class IdentityFunction implements MathFunction {

    public double apply(double x) {
        return x;
    }

    public String toString() {
        return "IdentityFunction{}";
    }

    public boolean equals(Object o) {
        return this.getClass() == o.getClass();
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public Object clone() {
        return new IdentityFunction();
    }
}