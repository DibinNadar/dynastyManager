package com.example.geektrust;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

// TODO HANDLE ERROR WHEN WRONG ENUM IS PASSED!!!!!!!

public class FileReaderExperiment {
// TODO: Use SIB to initialise dynasty

    static Dynasty currentDynasty;

    public FileReaderExperiment(Dynasty currentDynasty) {
        FileReaderExperiment.currentDynasty = currentDynasty;
    }
// test removable

    public static void main(String[] args) throws IOException {

        Path pathForDataInitialization = FileSystems.getDefault().getPath("sample_input/initializeTest.txt");
//        printFile(pathForDataInitialization);
        initializeTree(pathForDataInitialization);
        // TODO in SIB

        System.out.println("\n-------------Post Init-------------\n");

        Path pathForDataQuery = FileSystems.getDefault().getPath("sample_input/queryTest.txt");

        modifyAndVerifyData(pathForDataQuery);




        // TODO checks to be added
//        System.out.println("Exists : " + Files.exists(pathForDataInitialization));
//        System.out.println("Readable : " + Files.isReadable(pathForDataInitialization));
//        System.out.println("Writeable : " + Files.isWritable(pathForDataInitialization));


    }

    private static void initializeTree(Path path) { // TODO Make sure this is ONLY readable

        boolean initializeDynastyFlag = true;

        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;

            while ((line = fileReader.readLine()) != null) {
                String[] words = line.split(" ");

                String callMethod = words[0]; // TODO don't use magic numbers but give meaningful names


                if (callMethod.equalsIgnoreCase("CREATE_DYNASTY") && initializeDynastyFlag) {
                    String queenName = toCapitalize(words[1]);
                    String kingName = toCapitalize(words[2]);
                    String dynastyName = toCapitalize(words[3]);

                    currentDynasty = new Dynasty(queenName, kingName, dynastyName);
                    System.out.println("DYNASTY_CREATION_SUCCEEDED");
                    initializeDynastyFlag = false;
                }
                else if (!callMethod.equalsIgnoreCase("CREATE_DYNASTY") && initializeDynastyFlag) {
                    System.out.println("DYNASTY_CREATION_FAILED");
                    return;
                }
                else if (callMethod.equalsIgnoreCase("MARRY")) {
                    String femaleName = toCapitalize(words[1]);
                    String maleName = toCapitalize(words[2]);

                    currentDynasty.marry(femaleName,maleName);
                    System.out.println("Married " + maleName +" to "+ femaleName);
//                    System.out.println("MARRIAGE_SUCCEEDED");

                } else if (callMethod.equalsIgnoreCase("GET_RELATIONSHIP")) {
                    String relationOf = toCapitalize(words[1]);
                    String relationship = words[2].toUpperCase();
                    relationship = relationship.replace('-', '_');

                    currentDynasty.getRelationship(relationOf,Relation.valueOf(relationship));
                    System.out.println("Relationed" + relationship);


                } else if (callMethod.equalsIgnoreCase("ADD_CHILD")) {
                    String motherName = toCapitalize(words[1]);
                    String childName = toCapitalize(words[2]);
                    String childGender = words[3].toUpperCase();

                    currentDynasty.addKid(motherName, childName, Gender.valueOf(childGender));
                    System.out.println("Childed "+ childName);

                } else System.out.println(" ");
//                } else System.out.println("Invalid Line -");


            }
            System.out.println("Dynasty Initialized Successfully!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    private static void modifyAndVerifyData(Path path) {

        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;

            while ((line = fileReader.readLine()) != null) {
                String[] words = line.split(" ");

                String callMethod = words[0]; // TODO don't use magic numbers but give meaningful names

                if (callMethod.equalsIgnoreCase("ADD_CHILD")) {
                    String motherName = toCapitalize(words[1]);
                    String childName = toCapitalize(words[2]);
                    String childGender = words[3].toUpperCase();

                    System.out.println(currentDynasty.addKid(motherName, childName, Gender.valueOf(childGender)));

//                    if (currentDynasty.addKid(motherName, childName, Gender.valueOf(childGender))) {
//                        System.out.println("CHILD_ADDITION_SUCCEEDED");
//                    } else {
//
//                        System.out.println("CHILD_ADDITION_FAILED");
//                    }

                } else if (callMethod.equalsIgnoreCase("GET_RELATIONSHIP")) {
                    String name = toCapitalize(words[1]);
                    String relation = words[2].toUpperCase();
                    relation = relation.replace('-', '_');

                    System.out.println(currentDynasty.getRelationship(name,Relation.valueOf(relation)));

                } else System.out.println(" ");
//                } else System.out.println("Invalid Line -");


            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

     public static String toCapitalize(String string) {
        string = string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();

        return string;
    }

}
