package concurrent;

import functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {
    private final TabulatedFunction function;

    public MultiplyingTask(TabulatedFunction function) {
        this.function = function;
    }

    public void run() {
        int size = function.getCount();

        for (int i = 0; i < size; i++) {
            synchronized (function) {
                double x = function.getX(i);
                double y = function.getY(i);
                function.setY(i, y * 2);
                System.out.printf("%s for index %d complete\n", Thread.currentThread().getName(), i);
            }
        }

        System.out.printf("%s has completed its task\n", Thread.currentThread().getName());
    }
}
