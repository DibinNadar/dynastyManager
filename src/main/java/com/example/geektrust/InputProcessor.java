package com.example.geektrust;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputProcessor {

    static Dynasty currentDynasty;

    public InputProcessor(Dynasty currentDynasty) {
        InputProcessor.currentDynasty = currentDynasty;
    }

    static void initializeTree(InputStream is) {
        boolean initializeDynastyOnceFlag = true;

        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] words = line.split(" ");
                String callMethod = words[0];

                if (callMethod.equalsIgnoreCase("CREATE_DYNASTY") && initializeDynastyOnceFlag) {
                    String queenName = toCapitalize(words[1]);
                    String kingName = toCapitalize(words[2]);
                    String dynastyName = toCapitalize(words[3]);

                    currentDynasty = new Dynasty(queenName, kingName, dynastyName);
//                    System.out.println("DYNASTY_CREATION_SUCCEEDED");
                    initializeDynastyOnceFlag = false;
                } else if (!callMethod.equalsIgnoreCase("CREATE_DYNASTY") && initializeDynastyOnceFlag) {
//                    System.out.println("DYNASTY_CREATION_FAILED");
                    return;
                } else if (callMethod.equalsIgnoreCase("MARRY")) {
                    String femaleName = toCapitalize(words[1]);
                    String maleName = toCapitalize(words[2]);

                    currentDynasty.marry(femaleName, maleName);
//                    System.out.println("Married " + maleName +" to "+ femaleName);
//                    System.out.println("MARRIAGE_SUCCEEDED");

                } else if (callMethod.equalsIgnoreCase("GET_RELATIONSHIP")) {
                    String relationOf = toCapitalize(words[1]);
                    String relationship = words[2].toUpperCase();
                    relationship = relationship.replace('-', '_');

                    currentDynasty.getRelationship(relationOf, Relation.valueOf(relationship));
//                    System.out.println("QUERIED_RELATION");
                } else if (callMethod.equalsIgnoreCase("ADD_CHILD")) {
                    String motherName = toCapitalize(words[1]);
                    String childName = toCapitalize(words[2]);
                    String childGender = words[3].toUpperCase();

                    currentDynasty.addKid(motherName, childName, Gender.valueOf(childGender));
//                    System.out.println("Added Child :"+ childName);
                } else System.out.println("INVALID_LINE");

            }
//            System.out.println("Dynasty Initialized Successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void modifyAndVerifyData(Path path) {

        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;

            while ((line = fileReader.readLine()) != null) {

                String[] words = line.split(" ");
                String callMethod = words[0];

                if (callMethod.equalsIgnoreCase("ADD_CHILD")) {
                    String motherName = toCapitalize(words[1]);
                    String childName = toCapitalize(words[2]);
                    String childGender = words[3].toUpperCase();

                    try {
                        System.out.println(currentDynasty.addKid(motherName, childName, Gender.valueOf(childGender)));
                    } catch (IllegalArgumentException ex) {
                        System.out.println("CHILD_ADDITION_FAILED"); // Invalid Gender
                    }

                } else if (callMethod.equalsIgnoreCase("GET_RELATIONSHIP")) {
                    String name = toCapitalize(words[1]);
                    String relation = words[2].toUpperCase();
                    relation = relation.replace('-', '_');

                    try {
                        System.out.println(currentDynasty.getRelationship(name, Relation.valueOf(relation)));
                    } catch (IllegalArgumentException ex) {
                        System.out.println("NONE");
                    }

                } else System.out.println("INVALID_LINE");

            }
        } catch (IOException e) {
            System.out.println("INVALID_FILE_PATH : " + e.getMessage());
        }
    }

    public static String toCapitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase().trim();
    }

}
