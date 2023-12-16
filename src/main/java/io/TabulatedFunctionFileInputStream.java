package io;

import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
public class TabulatedFunctionFileInputStream {
    public static void main(String args[]) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("C:\\Users\\net\\IdeaProjects\\aluk\\input\\array functions.bin"))) {
            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, factory);
            System.out.println(function);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции: ");
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputReader, factory);
            System.out.println(operator.derive(function));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}