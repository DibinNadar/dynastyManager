package com.example.geektrust;

import java.util.*;
import java.util.stream.Collectors;

public class Dynasty {

    private final String dynastyName;
    private final LinkedHashMap<String,Node> familyTree;
    private final Node queenNode;
    private final Node kingNode;

    public Dynasty(String queenName, String kingName, String dynastyName) {
        this.familyTree = new LinkedHashMap<>();
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
                return findMaternalPaternalAuntUncle(relationOf,defaultResponse,Relation.MOTHER, Gender.FEMALE);

            case MATERNAL_UNCLE:
                return findMaternalPaternalAuntUncle(relationOf,defaultResponse,Relation.MOTHER,Gender.MALE);

            case PATERNAL_AUNT:
                return findMaternalPaternalAuntUncle(relationOf,defaultResponse,Relation.FATHER,Gender.FEMALE);

            case PATERNAL_UNCLE:
                return findMaternalPaternalAuntUncle(relationOf,defaultResponse,Relation.FATHER,Gender.MALE);


            default:
                return defaultResponse;
        }
    }

    private String findAllChildren(String relationOf, String defaultResponse) {
        if (getOne(relationOf).getPartnerName() == null) return defaultResponse;

        String mother;
        if (getOne(relationOf).getGender().equals(Gender.MALE)) {
            mother = getOne(relationOf).getPartnerName();
        } else mother = getOne(relationOf).getName();

        List<String> allKids = new LinkedList<>(getOne(mother).getKids());

        return allKids.isEmpty() ? defaultResponse : beautifyOutput(allKids);
    }

    private String findChildrenByGender(String relationOf, String defaultResponse, Gender gender) {

        String kids = findAllChildren(relationOf, defaultResponse);
        if (kids.equals(defaultResponse)) return defaultResponse;

        return genderFilter(kids,defaultResponse,gender);
    }

    private String findAllSiblings(String relationOf, String defaultResponse) {

        String mother = getRelationship(relationOf, Relation.MOTHER);
        if (mother.equals(defaultResponse)) return defaultResponse;

        List<String> allKids = new LinkedList<>(getOne(mother).getKids());

        List<String> allSiblings = allKids.stream()
                .filter(kid -> !kid.equals(relationOf))
                .collect(Collectors.toList());

        return allSiblings.isEmpty() ? defaultResponse : beautifyOutput(allSiblings);
    }

    private String findSiblingsByGender(String relationOf, String defaultResponse, Gender gender){

        String siblings = findAllSiblings(relationOf, defaultResponse);
        if (siblings.equals(defaultResponse)) return defaultResponse;

        return genderFilter(siblings,defaultResponse,gender);
    }

    private String findBrotherInLaw(String relationOf, String defaultResponse){
        String partner = getOne(relationOf).getPartnerName();
        String mother = getOne(relationOf).getMotherName();

        if (mother != null) { // belongs to bloodline
            String sisters = findSiblingsByGender(relationOf,defaultResponse,Gender.FEMALE);
            if (sisters.equals(defaultResponse)) return defaultResponse;

            List<String> sisterList = genderFilterList(sisters, Gender.FEMALE);

            StringBuilder brotherInLaw = new StringBuilder();

            for (String sister:sisterList){
                String husband = getOne(sister).getPartnerName();
                if (!husband.equals(defaultResponse)){
                    brotherInLaw.append(husband).append(" ");
                }
            }
            return brotherInLaw.toString();
        }
        String filteredBrotherInLaw = findSiblingsByGender(partner,defaultResponse,Gender.MALE);

        return filteredBrotherInLaw.isEmpty() ? defaultResponse : filteredBrotherInLaw;
    }

    private String findSisterInLaw(String relationOf, String defaultResponse){
        String partner = getOne(relationOf).getPartnerName();
        String mother = getOne(relationOf).getMotherName();

        if (mother != null) { // belongs to bloodline
            String brothers = findSiblingsByGender(relationOf,defaultResponse,Gender.MALE);
            if (brothers.equals(defaultResponse)) return defaultResponse;

            StringBuilder sisterInLaw = new StringBuilder();

            List<String> brotherList = genderFilterList(brothers,Gender.MALE);
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

    private String findMaternalPaternalAuntUncle(String relationOf, String defaultResponse, Relation relation, Gender gender){
        String parent = getRelationship(relationOf, relation);
        return findSiblingsByGender(parent,defaultResponse,gender);
    }


    public Node getOne(String nodeName) {
        return familyTree.get(nodeName);
    }

    public boolean containsOne(String nodeName) {
        return familyTree.containsKey(nodeName);
    }

    public LinkedHashMap<String, Node> getFamilyTree() {
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

    private String beautifyOutput(List<String> input){
        StringBuilder builder = new StringBuilder();

        for (String string: input){
            builder.append(string).append(" ");
        }
        return builder.toString();
    }

    private String genderFilter(String inputString, String defaultResponse, Gender gender){
        String[] array = inputString.split(" ");

        List<String> filteredByGenderList = Arrays
                .stream(array)
                .filter(person -> getOne(person).getGender().equals(gender))
                .collect(Collectors.toList());

        return filteredByGenderList.isEmpty() ? defaultResponse : beautifyOutput(filteredByGenderList);
    }

    private List<String> genderFilterList(String inputString, Gender gender){
        String[] array = inputString.split(" ");

        return Arrays
                .stream(array)
                .filter(person -> getOne(person).getGender().equals(gender))
                .collect(Collectors.toList());
    }


}
