package com.example.geektrust;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        Path pathForDataInitialization = FileSystems.getDefault().getPath("sample_input/initializeTest.txt");
//        printFile(pathForDataInitialization);
        FileReaderExperiment.initializeTree(pathForDataInitialization);
        // TODO in SIB

        System.out.println("\n-------------Post Init-------------\n");

        Path pathForDataQuery = FileSystems.getDefault().getPath("sample_input/queryTest.txt");

        FileReaderExperiment.modifyAndVerifyData(pathForDataQuery);

//        Path pathForBugFixes = FileSystems.getDefault().getPath("sample_input/bugFixes.txt");
//        FileReaderExperiment.modifyAndVerifyData(pathForBugFixes);




        // TODO checks to be added
//        System.out.println("Exists : " + Files.exists(pathForDataInitialization));
//        System.out.println("Readable : " + Files.isReadable(pathForDataInitialization));
//        System.out.println("Writeable : " + Files.isWritable(pathForDataInitialization));


    }

}
