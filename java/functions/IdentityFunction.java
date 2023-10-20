package functions;

public class IdentityFunction implements MathFunction, Cloneable {

    @Override
    public double apply(double x) {
        return x;
    }

    @Override
    public String toString() {
        return "IdentityFunction";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    public IdentityFunction clone() {
        return this;
    }
}
