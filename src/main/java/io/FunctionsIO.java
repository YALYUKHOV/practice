package io;

import functions.Point;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;

import javax.swing.text.NumberFormatter;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException("Functions IO не может иметь наследников и экземпляров");
    }

    static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point point : function) {
            printWriter.printf("%f %f\n", point.x, point.y);
        }
        printWriter.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        double[] xValues = new double[count];
        double[] yValues = new double[count];

        NumberFormat format = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        for (int i = 0; i < count; i++) {
            String value = reader.readLine();
            String[] xAndYValues = value.split(" ");
            try {
                xValues[i] = format.parse(xAndYValues[0]).doubleValue();
                yValues[i] = format.parse(xAndYValues[1]).doubleValue();
            } catch (ParseException e) {
                throw new IOException(e);
            }
        }
        return factory.create(xValues, yValues);
    }


    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        int pointCount = function.getCount();

        dataOutputStream.writeInt(pointCount);

        for (int i = 0; i < pointCount; i++) {
           /* double x = function.getX(i);
            double y = function.getY(i);*/
            dataOutputStream.writeDouble(function.getX(i));
            dataOutputStream.writeDouble(function.getY(i));
        }
        dataOutputStream.flush();
    }


    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        int length = dataInputStream.readInt();

        double[] xValues = new double[length];
        double[] yValues = new double[length];

        for (int i = 0; i < length; i++) {
            xValues[i] = dataInputStream.readDouble();
            yValues[i] = dataInputStream.readDouble();
        }


        return factory.create(xValues, yValues);

    }

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
        return (TabulatedFunction) objectInputStream.readObject();
    }


    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(function);

        out.flush();
    }
}