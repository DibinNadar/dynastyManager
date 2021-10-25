package com.example.geektrust;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dynasty {

    private final String dynastyName;
    private final HashMap<String, Node> familyTree;
    private final Node queenNode;
    private final Node kingNode;

    public Dynasty(String queenName, String kingName,String dynastyName) {
        this.familyTree = new HashMap<>();
        this.queenNode = new Node(queenName,Gender.FEMALE);
        this.kingNode = new Node(kingName,Gender.MALE);
        this.dynastyName= dynastyName;

        this.familyTree.put(queenNode.getName(),queenNode);
        this.familyTree.put(kingNode.getName(),kingNode);

        queenNode.addPartnerNameOnlyOnce(kingNode.getName());
        kingNode.addPartnerNameOnlyOnce(queenNode.getName());
    }

    public boolean addKid(String motherName, String kidName, Gender kidGender){

        // Mother not in dynasty
        if (!this.familyTree.containsKey(motherName)) return false;

        // Child Name already in dynasty
        if (this.familyTree.containsKey(kidName)) return false;

        Node mother = this.familyTree.get(motherName);

        // Mother not Female
        if (mother.getGender()!=Gender.FEMALE) return false;

        // Mother has no partner
        if (mother.getPartnerName()==null) return false;

        Node kid = new Node(kidName,kidGender);
        this.familyTree.put(kidName,kid);

        kid.addMotherOnlyOnce(motherName);
        mother.addKidToMother(kidName);

        return true;
    }

    public boolean marry(String femaleName, String maleName) {

        // If neither are in the dynasty
        if (!this.familyTree.containsKey(femaleName) && !this.familyTree.containsKey(maleName)) return false;

        Node female, male;

        // Identify the new individual

        if (!this.familyTree.containsKey(femaleName)){
            male = this.familyTree.get(maleName);
            female = new Node(femaleName,Gender.FEMALE);
        }else {
            female = this.familyTree.get(femaleName);
            male = new Node(maleName,Gender.MALE);
        }

        // Someone already married
        if (female.getPartnerName()!=null || male.getPartnerName()!=null) return false;

        // Incorrect genders passed
        if (female.getGender()!=Gender.FEMALE || male.getGender()!=Gender.MALE) return false;

        // add new individual to the dynasty
        this.familyTree.put(femaleName,female);
        this.familyTree.put(maleName,male);

        return male.addPartnerNameOnlyOnce(femaleName) && female.addPartnerNameOnlyOnce(maleName);
    }

    public String getRelationship(String relationOf, Relation relationship){ // TODO better names

        String defaultResponse = "PERSON_NOT_FOUND";
        String relation;

        if (!containsOne(relationOf)) return defaultResponse;

        switch (relationship){
// todo return multiple


            case MOTHER:
                relation = getOne(relationOf).getMotherName();
                return relation == null ? defaultResponse : relation;

            //      this.partnerName = this.partnerName == null ? partnerName : this.partnerName;

            case FATHER:
                String mother = getRelationship(relationOf, Relation.MOTHER);
                if (mother.equals(defaultResponse)) return defaultResponse;

                relation = getOne(mother).getPartnerName();
                return relation == null ? defaultResponse : relation;


            case CHILDREN:
                if (getOne(relationOf).getPartnerName() == null) return defaultResponse;

                if (getOne(relationOf).getGender().equals(Gender.MALE)){
                    mother = getOne(relationOf).getPartnerName();
                }else mother = getOne(relationOf).getName();

                Set<String> allKids = new HashSet<>(getOne(mother).getKids());
//                System.out.println(allKids.toString());
                return  allKids.isEmpty() ? defaultResponse : allKids.toString();


            case DAUGHTER:
////                if (getOne(relationOf).getPartnerName() == null) return defaultResponse;
////
////                if (getOne(relationOf).getGender().equals(Gender.MALE)){
////                    mother = getOne(relationOf).getPartnerName();
////                }else mother = getOne(relationOf).getName();
////
////                allKids = new HashSet<>(getOne(mother).getKids());
////                if (allKids.isEmpty()) return defaultResponse;
//
////                allKids = getRelationship(relationOf,Relation.CHILDREN)
//                String kidlings = getRelationship(relationOf,Relation.CHILDREN);
//                kidlings = kidlings.substring(1,kidlings.length()-1);
//                System.out.println("dsdsdssdd");
//                System.out.println(kidlings);
////                System.out.println((Arrays.toString(kidlings.split(", "))));
//                String[] ary = kidlings.split(", ");
//
//                List<String> aaa = Arrays
//                        .stream(ary)
//                        .filter(kid -> getOne(kid)
//                                .getGender()
//                                .equals(Gender.FEMALE))
//                        .collect(Collectors.toList());
////                        .collect(Collectors.toSet());
//
////                return aaa.toString();
//                            return  aaa.isEmpty() ? defaultResponse : aaa.toString();
//
////                System.out.println(ary.length);
////
////                for (String s: ary){
////                    System.out.println(s);
////                }
//
////                Set<String> theDaughters = Stream.of(kidlings.split(", "))
////                        .filter(kid -> getOne(kid)
////                                .getGender()
////                                .equals(Gender.FEMALE))
////                        .collect(Collectors.toSet());
//
////                String[] daughters = kidlings.stream()
////                        .filter(kid -> getOne(kid).getGender().equals(Gender.FEMALE))
////                        .collect(Collectors.toSet());
////
////                Set<String> daughters = allKids.stream()
////                        .filter(kid -> getOne(kid).getGender().equals(Gender.FEMALE))
////                        .collect(Collectors.toSet());
//
////                return  theDaughters.isEmpty() ? defaultResponse : theDaughters.toString();
////                return  daughters.isEmpty() ? defaultResponse : daughters.toString();



            case SON:
//                if (getOne(relationOf).getPartnerName() == null) return defaultResponse;
//
//                if (getOne(relationOf).getGender().equals(Gender.MALE)){
//                    mother = getOne(relationOf).getPartnerName();
//                }else mother = getOne(relationOf).getName();
//
//                allKids = new HashSet<>(getOne(mother).getKids());
//                if (allKids.isEmpty()) return defaultResponse;
//
//
//                Set<String> sons = allKids.stream()
//                        .filter(kid -> getOne(kid).getGender().equals(Gender.MALE))
//                        .collect(Collectors.toSet());
//
//                return  sons.isEmpty() ? defaultResponse : sons.toString();


            case SISTER:
            case BROTHER:
            case SIBLINGS:

            case SISTER_IN_LAW:
            case BROTHER_IN_LAW:

            case MATERNAL_AUNT:
            case MATERNAL_UNCLE:

            case PATERNAL_AUNT:
            case PATERNAL_UNCLE:




            default: return defaultResponse;


        }

    }

    public Node getOne(String nodeName){
        return familyTree.get(nodeName);
    }

    public boolean containsOne(String nodeName){
        return familyTree.containsKey(nodeName);
    }

    public HashMap<String, Node> getFamilyTree() {
        return familyTree;
    }

    public Node getQueenNode() {
        return queenNode;
    }

    public Node getKingNode() {
        return kingNode;
    }

    public String getDynastyName() {
        return dynastyName;
    }

}
