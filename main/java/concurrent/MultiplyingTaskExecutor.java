package concurrent;

import functions.TabulatedFunction;
import functions.UnitFunction;
import functions.LinkedListTabulatedFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {

    public static void main(String[] args) {
        // Создание табулированной функции
        TabulatedFunction baseFunction = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);

        // Создание списка потоков
        List<Thread> threads = new ArrayList<>();

        // Создание и запуск задачи MultiplyingTask для каждого потока
        for (int i = 0; i < 10; i++) {
            MultiplyingTask multiplyingTask = new MultiplyingTask(baseFunction);
            Thread thread = new Thread(multiplyingTask);
            threads.add(thread);
            thread.start();
        }

        // Приостановка текущего потока
        try {
            Thread.sleep(2000); // Подождать 2 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Вывод табулированной функции
        System.out.println("Tabulated Function After Multiplying:");
        System.out.println(baseFunction);

        // Ожидание завершения всех потоков
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Вывод табулированной функции после завершения всех потоков
        System.out.println("\nTabulated Function After All Threads Completed:");
        System.out.println(baseFunction);
    }
}
