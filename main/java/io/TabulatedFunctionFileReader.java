package io;
import functions.*;


import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {

    public static void main(String[] args) {
        // Путь к файлу
        String filePath = "input/function.txt";

        // Создаем фабрики для функций
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();

        // Читаем функции из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Читаем и выводим массивную функцию
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(reader, arrayFactory);
            System.out.println("Array Function:\n" + arrayFunction);

            // Читаем и выводим связанную функцию
            TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(reader, linkedListFactory);
            System.out.println("Linked List Function:\n" + linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
