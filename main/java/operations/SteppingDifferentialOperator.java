package operations;

import functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        if (Double.isNaN(step) || Double.isInfinite(step) || step <= 0) {
            throw new IllegalArgumentException("Step must be a positive finite number.");
        }
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        if (Double.isNaN(step) || Double.isInfinite(step) || step <= 0) {
            throw new IllegalArgumentException("Step must be a positive finite number.");
        }
        this.step = step;
    }
}