package io;

import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public final class FunctionsIO {
    // Приватный конструктор, бросающий исключение при попытке создания экземпляра
    private FunctionsIO() {
        throw new UnsupportedOperationException("Cannot instantiate this class");
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);

        // Записываем количество точек функции
        printWriter.println(function.getCount());

        // Записываем значения x и y для каждой точки
        for (int i = 0; i < function.getCount(); i++) {
            printWriter.printf("%f %f\n", function.getX(i), function.getY(i));
        }

        // Пробрасываем данные из буфера
        printWriter.flush();
    }

    public static void writeTabulatedFunction(OutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));

        // Записываем количество точек функции
        dataOutputStream.writeInt(function.getCount());

        // Записываем значения x и y для каждой точки
        for (int i = 0; i < function.getCount(); i++) {
            dataOutputStream.writeDouble(function.getX(i));
            dataOutputStream.writeDouble(function.getY(i));
        }

        // Пробрасываем данные из буфера
        dataOutputStream.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        try {
            // Читаем количество точек функции
            int count = Integer.parseInt(reader.readLine());

            // Создаем массивы для хранения точек
            double[] xValues = new double[count];
            double[] yValues = new double[count];

            // Получаем объект форматирования для правильного чтения дробных чисел
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

            // Читаем и заполняем массивы xValues и yValues
            for (int i = 0; i < count; i++) {
                String line = reader.readLine();
                String[] values = line.split(" ");
                xValues[i] = numberFormat.parse(values[0]).doubleValue();
                yValues[i] = numberFormat.parse(values[1]).doubleValue();
            }

            // Создаем и возвращаем функцию с помощью фабрики
            return factory.create(xValues, yValues);

        } catch (ParseException e) {
            throw new IOException("Error parsing the input data.", e);
        }
    }

    public static TabulatedFunction readTabulatedFunction(InputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream))) {
            // Читаем количество точек функции
            int count = dataInputStream.readInt();

            // Создаем массивы для хранения точек
            double[] xValues = new double[count];
            double[] yValues = new double[count];

            // Читаем и заполняем массивы xValues и yValues
            for (int i = 0; i < count; i++) {
                xValues[i] = dataInputStream.readDouble();
                yValues[i] = dataInputStream.readDouble();
            }

            // Создаем и возвращаем функцию с помощью фабрики
            return factory.create(xValues, yValues);

        } catch (IOException e) {
            throw new IOException("Error reading the input data.", e);
        }
    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream)) {
            objectOutputStream.writeObject(function);
            objectOutputStream.flush();
        }
    }


    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(stream)) {
            return (TabulatedFunction) objectInputStream.readObject();
        }
    }
}
