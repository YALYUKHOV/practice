package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        // Пути к файлам
        String arrayFilePath = "output/array function.bin";
        String linkedListFilePath = "output/linked list function.bin";

        // Создаем табулированные функции
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};

        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);

        // Записываем в файлы
        try (BufferedOutputStream arrayOutputStream = new BufferedOutputStream(new FileOutputStream(arrayFilePath));
             BufferedOutputStream linkedListOutputStream = new BufferedOutputStream(new FileOutputStream(linkedListFilePath))) {

            FunctionsIO.writeTabulatedFunction(arrayOutputStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListOutputStream, linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
