package concurrent;

import functions.ConstantFunction;
import functions.LinkedListTabulatedFunction;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) {
        // Создание табулированной функции
        LinkedListTabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(
                new ConstantFunction(-1.0),
                1.0,
                1000.0,
                1000
        );

        // Создание объектов потоков для чтения и записи
        Thread readThread = new Thread(new ReadTask(linkedListFunction));
        Thread writeThread = new Thread(new WriteTask(linkedListFunction, 0.5));

        // Запуск потоков
        readThread.start();
        writeThread.start();

        try {
            // Ожидание завершения потоков
            readThread.join();
            writeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
