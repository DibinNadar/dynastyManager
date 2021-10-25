package com.example.geektrust;

import java.util.HashMap;

public class Main {

    // TODO Add some sort of logging as an alternative to custom exceptions so I know WHY the methods are failing

    // TODO eliminate Nuclear Families, probably make having mother mandatory while entering dynasty
    //  to do so, give the king and queen unique no parent rules, or initialise their names when the dynasty HashMap is created?
    //  Each dynasty can have only one queen


    // Dynasty Manager()
    public boolean marry(String femaleName, String maleName, HashMap<String, Node> dynasty) {

        // Not in the dynasty
        if (!dynasty.containsKey(femaleName) || !dynasty.containsKey(maleName)) return false;

        // Creating references
        Node female = dynasty.get(femaleName);
        Node male = dynasty.get(maleName);

        // Someone already married
        if (female.getPartnerName()!=null || male.getPartnerName()!=null) return false;

        // Incorrect genders passed
        if (female.getGender()!=Gender.FEMALE || male.getGender()!=Gender.MALE) return false;

        return male.addPartnerNameOnlyOnce(femaleName) && female.addPartnerNameOnlyOnce(maleName);
    }

    // Dynasty Manager()
    public boolean addKid(String motherName, String kidName, HashMap<String, Node> dynasty){

//        TODO Simplify mom-child loop understanding

        // Not in dynasty
        if (!dynasty.containsKey(motherName) || !dynasty.containsKey(kidName)) return false;

        Node mother = dynasty.get(motherName);
        Node kid = dynasty.get(kidName);

        // Individual not Female
        if (mother.getGender()!=Gender.FEMALE) return false;

        // Mother has no partner
        if (mother.getPartnerName()==null) return false;

        // Kid already has mother
        if (kid.getMotherName() != null && !kid.getMotherName().equals(motherName)) return false; // IMP allows set the same relation again

        // Trying to assign same node as mother and child
        if (mother.equals(kid)) return false;

        // Trying to add child(motherName) as mother(kidName)
        if (mother.getMotherName() != null && mother.getMotherName().equals(kidName)) return false;

        // Trying to add mother(kidName) as child(motherName)
        if (!kid.getKids().isEmpty() && kid.getKids().contains(motherName)) return false;


        kid.addMotherOnlyOnce(motherName);

        mother.addKidToMother(kidName);

        return true;
    }

    // Dynasty Manager()
    public boolean createAndAddKid (String motherName, String kidName, Gender kidGender, HashMap<String, Node> dynasty){
        // TODO to be implemented
        return false;

    }

    public static void main(String[] args) {

        Main main = new Main();
        HashMap<String, Node> lengaburuFamilyTree = new HashMap<>();


/**                Creation                          **/
        Node anga = new Node("Anga", Gender.FEMALE);
        Node shan = new Node("Shan", Gender.MALE);
///////////////////////////////////////////////////////////////////////////////////////////////

        Node chit = new Node("Chit", Gender.MALE);
        Node ish = new Node("Ish", Gender.MALE);
        Node vich = new Node("Vich", Gender.MALE);
        Node aras = new Node("Aras", Gender.MALE);
        Node satya = new Node("Satya", Gender.FEMALE);

        Node amba = new Node("Amba", Gender.FEMALE);
        Node lika = new Node("Lika", Gender.FEMALE);
        Node chitra = new Node("Chitra", Gender.FEMALE);
        Node vyan = new Node("Vyan", Gender.MALE);
///////////////////////////////////////////////////////////////////////////////////////////////

        Node dritha = new Node("Dritha", Gender.FEMALE);
        Node tritha = new Node("Tritha", Gender.FEMALE);
        Node vritha = new Node("Vritha", Gender.MALE);
        Node vila = new Node("Vila", Gender.FEMALE);
        Node chika = new Node("Chika", Gender.FEMALE);
        Node jnki = new Node("Jnki", Gender.FEMALE);
        Node ahit = new Node("Ahit", Gender.MALE);
        Node asva = new Node("Asva", Gender.MALE);
        Node vyas = new Node("Vyas", Gender.MALE);
        Node atya = new Node("Atya", Gender.FEMALE);

        Node jaya = new Node("Jaya", Gender.MALE);
        Node arit = new Node("Arit", Gender.MALE);
        Node satvy = new Node("Satvy", Gender.FEMALE);
        Node krpi = new Node("Krpi", Gender.FEMALE);
///////////////////////////////////////////////////////////////////////////////////////////////

        Node yodhan = new Node("Yodhan", Gender.MALE);
        Node laki = new Node("Laki", Gender.MALE);
        Node lavnya = new Node("Lavnya", Gender.FEMALE);
        Node vasa = new Node("Vasa", Gender.MALE);
        Node kriya = new Node("Kriya", Gender.MALE);
        Node krithi = new Node("Krithi", Gender.FEMALE);



/**                 Insertion                          **/

        lengaburuFamilyTree.put("Anga", anga);
        lengaburuFamilyTree.put("Shan", shan);
/////////////////////////////////////////////////////////////////////////////////////////////////

        lengaburuFamilyTree.put("Chit", chit);
        lengaburuFamilyTree.put("Ish", ish);
        lengaburuFamilyTree.put("Vich", vich);
        lengaburuFamilyTree.put("Aras", aras);
        lengaburuFamilyTree.put("Satya", satya);

        lengaburuFamilyTree.put("Amba", amba);
        lengaburuFamilyTree.put("Lika", lika);
        lengaburuFamilyTree.put("Chitra", chitra);
        lengaburuFamilyTree.put("Vyan", vyan);
///////////////////////////////////////////////////////////////////////////////////////////////

        lengaburuFamilyTree.put("Dritha", dritha);
        lengaburuFamilyTree.put("Tritha", tritha);
        lengaburuFamilyTree.put("Vritha", vritha);
        lengaburuFamilyTree.put("Vila", vila);
        lengaburuFamilyTree.put("Chika", chika);
        lengaburuFamilyTree.put("Jnki", jnki);
        lengaburuFamilyTree.put("Ahit", ahit);
        lengaburuFamilyTree.put("Asva", asva);
        lengaburuFamilyTree.put("Vyas", vyas);
        lengaburuFamilyTree.put("Atya", atya);

        lengaburuFamilyTree.put("Jaya", jaya);
        lengaburuFamilyTree.put("Arit", arit);
        lengaburuFamilyTree.put("Satvy", satvy);
        lengaburuFamilyTree.put("Krpi", krpi);
///////////////////////////////////////////////////////////////////////////////////////////////

        lengaburuFamilyTree.put("Yodhan", yodhan);
        lengaburuFamilyTree.put("Laki", laki);
        lengaburuFamilyTree.put("Lavnya", lavnya);
        lengaburuFamilyTree.put("Vasa", vasa);
        lengaburuFamilyTree.put("Kriya", kriya);
        lengaburuFamilyTree.put("Krithi", krithi);





        /**                Wedding                          **/

        main.marry("Anga", "Shan", lengaburuFamilyTree);
/////////////////////////////////////////////////////////////////////////////////////////////////

        main.marry("Amba", "Chit", lengaburuFamilyTree);
        main.marry("Lika", "Vich", lengaburuFamilyTree);
        main.marry("Chitra", "Aras", lengaburuFamilyTree);
        main.marry("Satya", "Vyan", lengaburuFamilyTree);
/////////////////////////////////////////////////////////////////////////////////////////////////

        main.marry("Dritha", "Jaya", lengaburuFamilyTree);
        main.marry("Jnki", "Arit", lengaburuFamilyTree);
        main.marry("Satvy", "Asva", lengaburuFamilyTree);
        main.marry("Krpi", "Vyas", lengaburuFamilyTree);
/////////////////////////////////////////////////////////////////////////////////////////////////



        /**                Add Children                          **/

//        main.addKid("Anga","Chit",lengaburuFamilyTree);
//        main.addKid("Anga","Ish",lengaburuFamilyTree);
//        main.addKid("Anga","Vich",lengaburuFamilyTree);
//        main.addKid("Anga","Aras",lengaburuFamilyTree);
//        main.addKid("Anga","Satya",lengaburuFamilyTree);
/////////////////////////////////////////////////////////////////////////////////////////////////
//
//        main.addKid("Amba","Dritha",lengaburuFamilyTree);
//        main.addKid("Amba","Tritha",lengaburuFamilyTree);
//        main.addKid("Amba","Vritha",lengaburuFamilyTree);
//
//        main.addKid("Lika","Vila",lengaburuFamilyTree);
//        main.addKid("Lika","Chika",lengaburuFamilyTree);
//
//        main.addKid("Chitra","Jnki",lengaburuFamilyTree);
//        main.addKid("Chitra","Ahit",lengaburuFamilyTree);
//
//        main.addKid("Satya","Asva",lengaburuFamilyTree);
//        main.addKid("Satya","Vyas",lengaburuFamilyTree);
//        main.addKid("Satya","Atya",lengaburuFamilyTree);
///////////////////////////////////////////////////////////////////////////////////////////////////
//
//        main.addKid("Dritha","Yodhan",lengaburuFamilyTree);
//
//        main.addKid("Jnki","Laki",lengaburuFamilyTree);
//        main.addKid("Jnki","Lavnya",lengaburuFamilyTree);
//
//        main.addKid("Satvy","Vasa",lengaburuFamilyTree);
//
//        main.addKid("Krpi","Kriya",lengaburuFamilyTree);
//        main.addKid("Krpi","Krithi",lengaburuFamilyTree);
///////////////////////////////////////////////////////////////////////////////////////////////////



Node testNode = new Node("Test", Gender.FEMALE);
Node testMale = new Node("TestMale", Gender.MALE);
Node testKid = new Node("TestKid", Gender.FEMALE);

lengaburuFamilyTree.put("Test", testNode);
lengaburuFamilyTree.put("TestMale", testMale);
lengaburuFamilyTree.put("TestKid", testKid);

main.marry("Test", "TestMale", lengaburuFamilyTree);
System.out.println("Is this a nuclear family? "+main.addKid("Test","TestKid",lengaburuFamilyTree));


    }
}

/**
 * RULES
 * Input format to add a child:
 * ADD_CHILD ”Mother’s-Name" "Child's-Name" "Gender"
 * <p>
 * Input format to find the people belonging to a relationship:
 * GET_RELATIONSHIP ”Name” “Relationship”
 * <p>
 * Output format on finding the relationship:
 * ”Name 1” “Name 2”… “Name N”
 * ------------------------------------------------------------------------------------------------------------------
 * ADD_CHILD Pjali Srutak Male
 * GET_RELATIONSHIP Pjali Son
 * <p>
 * PERSON_NOT_FOUND
 * PERSON_NOT_FOUND
 * Pjali does not exist in the family tree
 * ------------------------------------------------------------------------------------------------------------------
 * ADD_CHILD Asva Vani Female
 * GET_RELATIONSHIP Vasa Siblings
 * <p>
 * CHILD_ADDITION_FAILED
 * NONE
 * Asva is male, hence child addition failed
 * ------------------------------------------------------------------------------------------------------------------
 * GET_RELATIONSHIP Atya Sister-In-Law
 * Satvy Krpi
 * ------------------------------------------------------------------------------------------------------------------
 * <p>
 * Remember
 * Edit the build file to set your ‘{your.qualified.name.of.main.class}’ and add your dependencies if any. Ensure the
 * generated executable is named ‘geektrust.jar’.
 * <p>
 * For a solution without build system, we want you to name your Main class as Geektrust.java. This is the file that
 * will contain your main method. We build and run the solution by using the following commands.
 * <p>
 * Follow Check list - submitting code
 **/

/**
 Remember
 Edit the build file to set your ‘{your.qualified.name.of.main.class}’ and add your dependencies if any. Ensure the
 generated executable is named ‘geektrust.jar’.

 For a solution without build system, we want you to name your Main class as Geektrust.java. This is the file that
 will contain your main method. We build and run the solution by using the following commands.

 Follow Check list - submitting code
 **/