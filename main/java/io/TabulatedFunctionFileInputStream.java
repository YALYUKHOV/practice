package io;

import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TabulatedFunctionFileInputStream {

    public static void main(String[] args) {
        // Путь к файлу с бинарными данными
        String binaryFilePath = "input/binary function.bin";

        // Создаем фабрику для функций
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();

        // Читаем функцию из бинарного файла
        try (BufferedInputStream inputStream = new BufferedInputStream(TabulatedFunctionFileInputStream.class.getClassLoader().getResourceAsStream(binaryFilePath))) {
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(inputStream, arrayFactory);
            System.out.println("Array Function:\n" + arrayFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Читаем функцию из консоли
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции:");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(System.in);
            TabulatedFunction linkedListFunction = readTabulatedFunction(bufferedInputStream, linkedListFactory);
            System.out.println("Linked List Function:\n" + linkedListFunction);

            // Выводим производную функции
            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
            TabulatedFunction derivative = differentialOperator.derive(linkedListFunction);
            System.out.println("Derivative:\n" + derivative);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        return FunctionsIO.readTabulatedFunction(inputStream, factory);
    }

    public static TabulatedFunction readTabulatedFunction(InputStreamReader inputStream, TabulatedFunctionFactory factory) throws IOException {
        return FunctionsIO.readTabulatedFunction(new BufferedReader(inputStream), factory);
    }
}
