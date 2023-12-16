package concurrent;

import functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {
    private TabulatedFunction tabulatedFunction;

    public MultiplyingTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            synchronized (tabulatedFunction) {
                tabulatedFunction.setY(i, tabulatedFunction.getY(i) * 2);
            }
        }
        String thread = Thread.currentThread().getName();
        System.out.println("thread " + thread + "has completed the task.");
    }
}