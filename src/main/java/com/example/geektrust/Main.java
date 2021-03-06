package com.example.geektrust;

import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {

    static {
        InputStream test = getFileFromResourceAsStream("initializeLengaburuDynasty.txt");
        InputProcessor.initializeTree(test);
    }

    public static void main(String[] args) {

        String inputString = Arrays.toString(args);
        inputString = inputString.substring(1, inputString.length() - 1);
        Path pathForDataQuery = FileSystems.getDefault().getPath(inputString);
        InputProcessor.modifyAndVerifyData(pathForDataQuery);

    }

    private static InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader loader = Main.class.getClassLoader();
        InputStream stream = loader.getResourceAsStream(fileName);

        if (stream == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        } else {
            return stream;
        }
    }
}