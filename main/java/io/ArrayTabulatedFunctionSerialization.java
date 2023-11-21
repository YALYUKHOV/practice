package io;

import functions.ArrayTabulatedFunction;
import operations.TabulatedDifferentialOperator;
import functions.TabulatedFunction;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArrayTabulatedFunctionSerialization {

    public static void main(String[] args) {
        // Путь к файлу для записи сериализованных данных
        String filePath = "output/serialized_array_functions.bin";

        try {
            // Создаем буферизованный поток для записи
            try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
                // Создаем табулированную функцию
                double[] xValues = {0.0, 1.0, 2.0, 3.0};
                double[] yValues = {0.0, 1.0, 4.0, 9.0};
                TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);

                // Находим первую и вторую производные
                TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
                TabulatedFunction firstDerivative = differentialOperator.derive(originalFunction);
                TabulatedFunction secondDerivative = differentialOperator.derive(firstDerivative);

                // Сериализуем все три функции
                FunctionsIO.serialize(outputStream, originalFunction);
                FunctionsIO.serialize(outputStream, firstDerivative);
                FunctionsIO.serialize(outputStream, secondDerivative);
            }

            // Создаем буферизованный поток для чтения
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))) {
                // Десериализуем три функции
                TabulatedFunction deserializedOriginalFunction = FunctionsIO.deserialize(inputStream);
                TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(inputStream);
                TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(inputStream);

                // Выводим значения функций в консоль
                System.out.println("Original Function:\n" + deserializedOriginalFunction);
                System.out.println("First Derivative:\n" + deserializedFirstDerivative);
                System.out.println("Second Derivative:\n" + deserializedSecondDerivative);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
