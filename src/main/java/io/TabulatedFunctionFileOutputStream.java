package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class TabulatedFunctionFileOutputStream {
    public static void main(String args[]) {
        try (BufferedOutputStream arrFileOut = new BufferedOutputStream(new FileOutputStream("C:\\Users\\net\\IdeaProjects\\aluk\\output\\array functions.bin"));
             BufferedOutputStream listFileOut = new BufferedOutputStream(new FileOutputStream("C:\\Users\\net\\IdeaProjects\\aluk\\output\\linked list functions.bin"))) {
            double[] xValues = {1, 2, 3, 4};
            double[] yValues = {5, 6, 7, 8};
            ArrayTabulatedFunction arr = new ArrayTabulatedFunction(xValues, yValues);
            LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xValues, yValues);

            FunctionsIO.writeTabulatedFunction(arrFileOut, arr);
            FunctionsIO.writeTabulatedFunction(listFileOut, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}