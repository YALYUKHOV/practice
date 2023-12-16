package operations;

import functions.MathFunction;
import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        this.step = step;
        if ((step == 0) || (step < 0) || (step == POSITIVE_INFINITY) || (step == NaN))
            throw new IllegalArgumentException();
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getStep() {
        return step;
    }
}