package com.example.geektrust;

import java.util.*;
import java.util.stream.Collectors;

public class Dynasty {

    private final String dynastyName;
    private final HashMap<String, Node> familyTree;
    private final Node queenNode;
    private final Node kingNode;

    public Dynasty(String queenName, String kingName, String dynastyName) {
        this.familyTree = new HashMap<>();
        this.queenNode = new Node(queenName, Gender.FEMALE);
        this.kingNode = new Node(kingName, Gender.MALE);
        this.dynastyName = dynastyName;

        this.familyTree.put(queenNode.getName(), queenNode);
        this.familyTree.put(kingNode.getName(), kingNode);

        queenNode.addPartnerNameOnlyOnce(kingNode.getName());
        kingNode.addPartnerNameOnlyOnce(queenNode.getName());
    }

    public boolean addKid(String motherName, String kidName, Gender kidGender) {

        // Mother not in dynasty
        if (!this.familyTree.containsKey(motherName)) return false;

        // Child Name already in dynasty
        if (this.familyTree.containsKey(kidName)) return false;

        Node mother = this.familyTree.get(motherName);

        // Mother not Female
        if (mother.getGender() != Gender.FEMALE) return false;

        // Mother has no partner
        if (mother.getPartnerName() == null) return false;

        Node kid = new Node(kidName, kidGender);
        this.familyTree.put(kidName, kid);

        kid.addMotherOnlyOnce(motherName);
        mother.addKidToMother(kidName);

        return true;
    }

    public boolean marry(String femaleName, String maleName) {

        // If neither are in the dynasty
        if (!this.familyTree.containsKey(femaleName) && !this.familyTree.containsKey(maleName)) return false;

        Node female, male;

        // Identify the new individual

        if (!this.familyTree.containsKey(femaleName)) {
            male = this.familyTree.get(maleName);
            female = new Node(femaleName, Gender.FEMALE);
        } else {
            female = this.familyTree.get(femaleName);
            male = new Node(maleName, Gender.MALE);
        }

        // Someone already married
        if (female.getPartnerName() != null || male.getPartnerName() != null) return false;

        // Incorrect genders passed
        if (female.getGender() != Gender.FEMALE || male.getGender() != Gender.MALE) return false;

        // add new individual to the dynasty
        this.familyTree.put(femaleName, female);
        this.familyTree.put(maleName, male);

        return male.addPartnerNameOnlyOnce(femaleName) && female.addPartnerNameOnlyOnce(maleName);
    }

    public String getRelationship(String relationOf, Relation relationship) { // TODO better names

        String defaultResponse = "PERSON_NOT_FOUND";
        String relation;

        if (!containsOne(relationOf)) return defaultResponse;

        switch (relationship) {

            case MOTHER:
                relation = getOne(relationOf).getMotherName();
                return relation == null ? defaultResponse : relation;

            case FATHER:
                String mother = getRelationship(relationOf, Relation.MOTHER);
                if (mother.equals(defaultResponse)) return defaultResponse;

                relation = getOne(mother).getPartnerName();
                return relation == null ? defaultResponse : relation;

            case CHILDREN:
                return findAllChildren(relationOf, defaultResponse);

            case DAUGHTER:
                return findChildrenByGender(relationOf, defaultResponse, Gender.FEMALE);

            case SON:
                return findChildrenByGender(relationOf, defaultResponse, Gender.MALE);

            case SIBLINGS:
                return findAllSiblings(relationOf, defaultResponse);

            case SISTER:
                return findSiblingsByGender(relationOf, defaultResponse, Gender.FEMALE);

            case BROTHER:
                return findSiblingsByGender(relationOf, defaultResponse, Gender.MALE);


            case SISTER_IN_LAW:
                return findSisterInLaw(relationOf,defaultResponse);

            case BROTHER_IN_LAW:
                return findBrotherInLaw(relationOf,defaultResponse);

            case MATERNAL_AUNT:
            case MATERNAL_UNCLE:

            case PATERNAL_AUNT:
            case PATERNAL_UNCLE:


            default:
                return defaultResponse;
        }
    }

    // TODO Unify all methods structure, return same dataTypes, basically be more elegant
    //  code consistency, stringBuilder everywhere?


    private String findAllChildren(String relationOf, String defaultResponse) {
        if (getOne(relationOf).getPartnerName() == null) return defaultResponse;

        String mother;
        if (getOne(relationOf).getGender().equals(Gender.MALE)) {
            mother = getOne(relationOf).getPartnerName();
        } else mother = getOne(relationOf).getName();

        Set<String> allKids = new HashSet<>(getOne(mother).getKids());
        List<String> all = new LinkedList<>(getOne(mother).getKids());
        return all.isEmpty() ? defaultResponse : all.toString();
//        return allKids.isEmpty() ? defaultResponse : allKids.toString();
    }

    private String findChildrenByGender(String relationOf, String defaultResponse, Gender gender) {

        String kids = findAllChildren(relationOf, defaultResponse);
        if (kids.equals(defaultResponse)) return defaultResponse;

        kids = kids.substring(1, kids.length() - 1); // removing []
        String[] kidsArray = kids.split(", ");

        List<String> filteredKidList = Arrays
                .stream(kidsArray)
                .filter(kid -> getOne(kid)
                        .getGender()
                        .equals(gender))
                .collect(Collectors.toList());

        return filteredKidList.isEmpty() ? defaultResponse : filteredKidList.toString();

    }

    // TODO: Check if too much code hopping
    private String findAllSiblings(String relationOf, String defaultResponse) {

        String mother = getRelationship(relationOf, Relation.MOTHER);
        if (mother.equals(defaultResponse)) return defaultResponse;

        Set<String> allKids = new HashSet<>(getOne(mother).getKids());

        List<String> allSiblings = allKids.stream().filter(kid -> !kid.equals(relationOf)).collect(Collectors.toList());

        return allSiblings.isEmpty() ? defaultResponse : allSiblings.toString();
    }

    private String findSiblingsByGender(String relationOf, String defaultResponse, Gender gender){

        String siblings = findAllSiblings(relationOf, defaultResponse);
        if (siblings.equals(defaultResponse)) return defaultResponse;

        siblings = siblings.substring(1, siblings.length() - 1);
        String[] kidsArray = siblings.split(", ");

        List<String> filteredKidList = Arrays
                .stream(kidsArray)
                .filter(kid -> getOne(kid)
                        .getGender()
                        .equals(gender))
                .collect(Collectors.toList());

        return filteredKidList.isEmpty() ? defaultResponse : filteredKidList.toString();

    }

    // TODO needs major attention
    private String findBrotherInLaw(String relationOf, String defaultResponse){
        String partner = getOne(relationOf).getPartnerName();
        String mother = getOne(relationOf).getMotherName();

        if (mother != null) { // belongs to bloodline
            String sisters = findSiblingsByGender(relationOf,defaultResponse,Gender.FEMALE);
            if (sisters.equals(defaultResponse)) return defaultResponse;

            StringBuilder brotherInLaw = new StringBuilder();

            sisters = sisters.substring(1, sisters.length() - 1);
            String[] sistersArray = sisters.split(", ");

            List<String> sisterList = Arrays
                    .stream(sistersArray)
                    .filter(sister -> getOne(sister)
                            .getGender()
                            .equals(Gender.FEMALE))
                    .collect(Collectors.toList());

            for (String sister:sisterList){
                String husband = getOne(sister).getPartnerName();
                if (!husband.equals(defaultResponse)){
                    brotherInLaw.append(husband).append(" ");  // TODO check formaot in sisterInLaw for Atya
                }
            }

            return brotherInLaw.toString();
        }


        String filteredBrotherInLaw = findSiblingsByGender(partner,defaultResponse,Gender.MALE);

        return filteredBrotherInLaw.isEmpty() ? defaultResponse : filteredBrotherInLaw;
    }

    // TODO needs major attention
    private String findSisterInLaw(String relationOf, String defaultResponse){
        String partner = getOne(relationOf).getPartnerName();
        String mother = getOne(relationOf).getMotherName();

        if (mother != null) { // belongs to bloodline
            String brothers = findSiblingsByGender(relationOf,defaultResponse,Gender.MALE);
            if (brothers.equals(defaultResponse)) return defaultResponse;

            StringBuilder sisterInLaw = new StringBuilder();

            brothers = brothers.substring(1, brothers.length() - 1);
            String[] brothersArray = brothers.split(", ");

            List<String> brotherList = Arrays
                    .stream(brothersArray)
                    .filter(brother -> getOne(brother)
                            .getGender()
                            .equals(Gender.MALE))
                    .collect(Collectors.toList());

            for (String brother:brotherList){
                String wife = getOne(brother).getPartnerName();
                if (wife != null){
                    sisterInLaw.append(wife).append(" "); // TODO check formaot in sisterInLaw for Atya
                }
            }
            return sisterInLaw.toString().isEmpty() ? defaultResponse : sisterInLaw.toString();

        }


        String filteredSisterInLaw = findSiblingsByGender(partner,defaultResponse,Gender.FEMALE);

        return filteredSisterInLaw.isEmpty() ? defaultResponse : filteredSisterInLaw;
    }

    public Node getOne(String nodeName) {
        return familyTree.get(nodeName);
    }

    public boolean containsOne(String nodeName) {
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
