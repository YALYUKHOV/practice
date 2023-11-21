package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        // Путь к файлам
        String arrayFilePath = "output/array function.txt";
        String linkedListFilePath = "output/linked list function.txt";

        // Создаем табулированные функции
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};

        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);

        // Записываем в файлы
        try (BufferedWriter arrayWriter = new BufferedWriter(new FileWriter(arrayFilePath));
             BufferedWriter linkedListWriter = new BufferedWriter(new FileWriter(linkedListFilePath))) {

            FunctionsIO.writeTabulatedFunction(arrayWriter, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListWriter, linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
