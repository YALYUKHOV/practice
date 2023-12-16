package io;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;
public class LinkedListTabulatedFunctionSerialization {
    public static void main(String args[]) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\net\\IdeaProjects\\aluk\\output\\serialized linked list functions.bin"))) {
            double[] xValue = {1, 2, 3};
            double[] yValue = {4, 5, 6};

            TabulatedFunction function = new LinkedListTabulatedFunction(xValue, yValue);
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
            TabulatedFunction firstDerive = operator.derive(function);
            TabulatedFunction secondDerive = operator.derive(firstDerive);

            FunctionsIO.serialize(out, function);
            FunctionsIO.serialize(out, firstDerive);
            FunctionsIO.serialize(out, secondDerive);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\net\\IdeaProjects\\aluk\\output\\serialized linked list functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(in));
            System.out.println(FunctionsIO.deserialize(in));
            System.out.println(FunctionsIO.deserialize(in));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}