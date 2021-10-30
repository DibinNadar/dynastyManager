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
        inputString = inputString.substring(1,inputString.length()-1);

        Path pathForDataQuery = FileSystems.getDefault().getPath(inputString);
        InputProcessor.modifyAndVerifyData(pathForDataQuery);

//        String inputPathLocal = "G:\\GeekTrust\\MeetTheFamily\\java-maven-starter-kit\\sample_input\\queryTest.txt";
//
//        Path pathForDataQueryLocal = FileSystems.getDefault().getPath(inputPathLocal);
//
//        System.out.println("Path                      :"+pathForDataQueryLocal);
//        System.out.println("");
//
//        InputProcessor.modifyAndVerifyData(pathForDataQueryLocal);


        // TODO checks to be added
//        System.out.println("Exists : " + Files.exists(pathForDataInitialization));
//        System.out.println("Readable : " + Files.isReadable(pathForDataInitialization));
//        System.out.println("Writeable : " + Files.isWritable(pathForDataInitialization));

//        G:\GeekTrust\MeetTheFamily\java-maven-starter-kit>mvn package
//        D:\temp>java -jar geektrust.jar G:\GeekTrust\MeetTheFamily\java-maven-starter-kit\sample_input\queryTest.txt

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