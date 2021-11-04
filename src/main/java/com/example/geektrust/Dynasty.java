package com.example.geektrust;

import java.util.*;
import java.util.stream.Collectors;

public class Dynasty {

    private final String dynastyName;
    private final LinkedHashMap<String, Node> familyTree;
    private final Node queenNode;
    private final Node kingNode;


    public Dynasty(String queenName, String kingName, String dynastyName) {
        this.familyTree = new LinkedHashMap<>();
        this.queenNode = new Node(queenName, Gender.FEMALE);
        this.kingNode = new Node(kingName, Gender.MALE);
        this.dynastyName = dynastyName;

        this.familyTree.put(queenNode.getName(), queenNode);
        this.familyTree.put(kingNode.getName(), kingNode);

        queenNode.assignPartnerIfSingle(kingNode.getName());
        kingNode.assignPartnerIfSingle(queenNode.getName());
    }

    public String addKid(String motherName, String kidName, Gender kidGender) {

        // Kid Input is null
        if (kidName == null || kidGender == null) return "CHILD_ADDITION_FAILED";

        // Mother not in dynasty
        if (!this.familyTree.containsKey(motherName)) return "PERSON_NOT_FOUND";

        // Child Name already in dynasty
        if (this.familyTree.containsKey(kidName)) return "CHILD_ADDITION_FAILED";

        Node mother = this.familyTree.get(motherName);

        // Mother not Female
        if (mother.getGender() != Gender.FEMALE) return "CHILD_ADDITION_FAILED";

        // Mother has no partner
        if (mother.getPartnerName() == null) return "CHILD_ADDITION_FAILED";

        Node kid = new Node(kidName, kidGender);
        this.familyTree.put(kidName, kid);

        if (kid.assignMotherIfKidHasNoMother(motherName) && mother.addKidToMother(kidName)) {
            return "CHILD_ADDITION_SUCCEEDED";
        }

        return "CHILD_ADDITION_FAILED";
    }

    public boolean marry(String femaleName, String maleName) {

        if (femaleName.isBlank() || maleName.isBlank()) return false;

        // If neither are in the dynasty
        if (!this.familyTree.containsKey(femaleName) && !this.familyTree.containsKey(maleName)) return false;

        Node female, male;

        // Identify the new individual
        if (!this.familyTree.containsKey(femaleName)) {   // Female is the outsider
            male = this.familyTree.get(maleName);
            female = new Node(femaleName, Gender.FEMALE);
        } else {                                            // Male is the outsider
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

        return male.assignPartnerIfSingle(femaleName) && female.assignPartnerIfSingle(maleName);
    }

    public String getRelationship(String relationOf, Relation relationship) {

        String noValidRelations = "NONE";
        String personNotFound = "PERSON_NOT_FOUND";

        if (!containsOne(relationOf)) return personNotFound;

        switch (relationship) {

            case MOTHER:
                String mother = getOne(relationOf).getMotherName();
                return mother == null ? noValidRelations : mother;

            case FATHER:
                mother = getRelationship(relationOf, Relation.MOTHER);
                if (mother.equals(noValidRelations)) return noValidRelations;

                String father = getOne(mother).getPartnerName();
                return father == null ? noValidRelations : father;

            case CHILDREN:
                return findAllChildren(relationOf, noValidRelations);

            case DAUGHTER:
                return findChildrenByGender(relationOf, noValidRelations, Gender.FEMALE);

            case SON:
                return findChildrenByGender(relationOf, noValidRelations, Gender.MALE);

            case SIBLINGS:
                return findAllSiblings(relationOf, noValidRelations);

            case SISTER:
                return findSiblingsByGender(relationOf, noValidRelations, Gender.FEMALE);

            case BROTHER:
                return findSiblingsByGender(relationOf, noValidRelations, Gender.MALE);

            case SISTER_IN_LAW:
                return findSisterInLaw(relationOf, noValidRelations);

            case BROTHER_IN_LAW:
                return findBrotherInLaw(relationOf, noValidRelations);

            case MATERNAL_AUNT:
                return findAuntOrUncle(relationOf, noValidRelations, Relation.MOTHER, Gender.FEMALE);

            case MATERNAL_UNCLE:
                return findAuntOrUncle(relationOf, noValidRelations, Relation.MOTHER, Gender.MALE);

            case PATERNAL_AUNT:
                return findAuntOrUncle(relationOf, noValidRelations, Relation.FATHER, Gender.FEMALE);

            case PATERNAL_UNCLE:
                return findAuntOrUncle(relationOf, noValidRelations, Relation.FATHER, Gender.MALE);

            default:
                return noValidRelations;
        }
    }

    private String findAllChildren(String relationOf, String relationsNotFound) {
        if (getOne(relationOf).getPartnerName() == null) return relationsNotFound; // unmarried hence can't have kids

        String mother;
        if (getOne(relationOf).getGender().equals(Gender.MALE)) {
            mother = getOne(relationOf).getPartnerName();
        } else mother = getOne(relationOf).getName();

        List<String> allKids = new LinkedList<>(getOne(mother).getKids());

        return allKids.isEmpty() ? relationsNotFound : beautifyOutput(allKids);
    }

    private String findChildrenByGender(String relationOf, String relationsNotFound, Gender gender) {

        String kids = findAllChildren(relationOf, relationsNotFound);
        if (kids.equals(relationsNotFound)) return relationsNotFound;

        return filterByGender(kids, relationsNotFound, gender);
    }

    private String findAllSiblings(String relationOf, String relationsNotFound) {

        String mother = getRelationship(relationOf, Relation.MOTHER);
        if (mother.equals(relationsNotFound)) return relationsNotFound;

        List<String> allKids = new LinkedList<>(getOne(mother).getKids());

        List<String> allSiblings = allKids.stream()
                .filter(kid -> !kid.equals(relationOf))
                .collect(Collectors.toList());

        return allSiblings.isEmpty() ? relationsNotFound : beautifyOutput(allSiblings);
    }

    private String findSiblingsByGender(String relationOf, String relationsNotFound, Gender gender) {

        String siblings = findAllSiblings(relationOf, relationsNotFound);
        if (siblings.equals(relationsNotFound)) return relationsNotFound;

        return filterByGender(siblings, relationsNotFound, gender);
    }

    private String findBrotherInLaw(String relationOf, String relationsNotFound) {
        String partner = getOne(relationOf).getPartnerName();
        String mother = getOne(relationOf).getMotherName();

        if (mother != null) { // belongs to bloodline
            String sisters = findSiblingsByGender(relationOf, relationsNotFound, Gender.FEMALE);
            if (sisters.equals(relationsNotFound)) return relationsNotFound;

            List<String> sisterList = filterByGenderIntoList(sisters, Gender.FEMALE);

            StringBuilder brotherInLaw = new StringBuilder();

            for (String sister : sisterList) {
                String husband = getOne(sister).getPartnerName();
                if (!husband.equals(relationsNotFound)) {
                    brotherInLaw.append(husband).append(" ");
                }
            }
            return brotherInLaw.toString();
        }
        String filteredBrotherInLaw = findSiblingsByGender(partner, relationsNotFound, Gender.MALE);

        return filteredBrotherInLaw.isEmpty() ? relationsNotFound : filteredBrotherInLaw;
    }

    private String findSisterInLaw(String relationOf, String relationsNotFound) {
        String partner = getOne(relationOf).getPartnerName();
        String mother = getOne(relationOf).getMotherName();

        if (mother != null) { // belongs to bloodline
            String brothers = findSiblingsByGender(relationOf, relationsNotFound, Gender.MALE);
            if (brothers.equals(relationsNotFound)) return relationsNotFound;

            StringBuilder sisterInLaw = new StringBuilder();

            List<String> brotherList = filterByGenderIntoList(brothers, Gender.MALE);
            for (String brother : brotherList) {
                String wife = getOne(brother).getPartnerName();
                if (wife != null) {
                    sisterInLaw.append(wife).append(" ");
                }
            }
            return sisterInLaw.toString().isEmpty() ? relationsNotFound : sisterInLaw.toString();

        }


        String filteredSisterInLaw = findSiblingsByGender(partner, relationsNotFound, Gender.FEMALE);

        return filteredSisterInLaw.isEmpty() ? relationsNotFound : filteredSisterInLaw;
    }

    private String findAuntOrUncle(String relationOf, String relationsNotFound, Relation relation, Gender gender) {
        String parent = getRelationship(relationOf, relation);
        if (parent.equals(relationsNotFound)) return relationsNotFound;
        return findSiblingsByGender(parent, relationsNotFound, gender);
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

    private String beautifyOutput(List<String> input) {
        StringBuilder builder = new StringBuilder();
        for (String string : input) {
            builder.append(string).append(" ");
        }
        return builder.toString().trim();
    }

    private String filterByGender(String inputString, String relationsNotFound, Gender gender) {
        String[] array = inputString.split(" ");

        List<String> filteredByGenderList = Arrays
                .stream(array)
                .filter(person -> getOne(person).getGender().equals(gender))
                .collect(Collectors.toList());

        return filteredByGenderList.isEmpty() ? relationsNotFound : beautifyOutput(filteredByGenderList);
    }

    private List<String> filterByGenderIntoList(String inputString, Gender gender) {
        String[] array = inputString.split(" ");

        return Arrays
                .stream(array)
                .filter(person -> getOne(person).getGender().equals(gender))
                .collect(Collectors.toList());
    }

}
