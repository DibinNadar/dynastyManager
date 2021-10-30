package com.example.geektrust;

public class MainTester {

    // TODO Add some sort of logging as an alternative to custom exceptions so I know WHY the methods are failing


    public static void main(String[] args) {

        Dynasty lengaburuFamilyTree = new Dynasty("Anga","Shan", "Lengaburu");

        System.out.print(lengaburuFamilyTree.addKid("Anga","Chit",Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Anga","Ish",Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Anga","Vich",Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Anga","Aras",Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Anga","Satya",Gender.FEMALE));
        System.out.println();
        System.out.println();

/////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.print(lengaburuFamilyTree.marry("Amba", "Chit"));
        System.out.print(lengaburuFamilyTree.marry("Lika", "Vich"));
        System.out.print(lengaburuFamilyTree.marry("Chitra", "Aras"));
        System.out.print(lengaburuFamilyTree.marry("Satya", "Vyan"));

        System.out.print(lengaburuFamilyTree.addKid("Amba","Dritha",Gender.FEMALE));
        System.out.print(lengaburuFamilyTree.addKid("Amba","Tritha",Gender.FEMALE));
        System.out.print(lengaburuFamilyTree.addKid("Amba","Vritha",Gender.MALE));

        System.out.print(lengaburuFamilyTree.addKid("Lika","Vila",Gender.FEMALE));
        System.out.print(lengaburuFamilyTree.addKid("Lika","Chika",Gender.FEMALE));

        System.out.print(lengaburuFamilyTree.addKid("Chitra","Jnki",Gender.FEMALE));
        System.out.print(lengaburuFamilyTree.addKid("Chitra","Ahit",Gender.MALE));

        System.out.print(lengaburuFamilyTree.addKid("Satya","Asva",Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Satya","Vyas",Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Satya","Atya",Gender.FEMALE));
        System.out.println();
        System.out.println();
/////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.print(lengaburuFamilyTree.marry("Dritha", "Jaya"));
        System.out.print(lengaburuFamilyTree.marry("Jnki", "Arit"));
        System.out.print(lengaburuFamilyTree.marry("Satvy", "Asva"));
        System.out.print(lengaburuFamilyTree.marry("Krpi", "Vyas"));

        System.out.print(lengaburuFamilyTree.addKid("Dritha","Yodhan",Gender.MALE));

        System.out.print(lengaburuFamilyTree.addKid("Jnki","Laki",Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Jnki","Lavnya",Gender.FEMALE));

        System.out.print(lengaburuFamilyTree.addKid("Satvy","Vasa",Gender.MALE));

        System.out.print(lengaburuFamilyTree.addKid("Krpi","Kriya",Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Krpi","Krithi",Gender.FEMALE));
        System.out.println("");
        System.out.println("");
///////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("----------------Mother-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.MOTHER));
        System.out.println(lengaburuFamilyTree.getRelationship("Dritha",Relation.MOTHER));   // Amba
        System.out.println(lengaburuFamilyTree.getRelationship("Jaya",Relation.MOTHER));
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.MOTHER));
        System.out.println(lengaburuFamilyTree.getRelationship("Vritha",Relation.MOTHER));   // Amba
        System.out.println("");

        System.out.println("----------------Father-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.FATHER));
        System.out.println(lengaburuFamilyTree.getRelationship("Dritha",Relation.FATHER));   // Chit
        System.out.println(lengaburuFamilyTree.getRelationship("Jaya",Relation.FATHER));
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.FATHER));
        System.out.println("");

        System.out.println("----------------Children-------------------"); //TODO SEQUENCE needs to be fixed
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.CHILDREN));  //  chit, ish, vich, aras, satya
        System.out.println(lengaburuFamilyTree.getRelationship("Dritha",Relation.CHILDREN));// yodhan
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.CHILDREN));  // laki, lavnya
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.CHILDREN));  // laki, lavnya
        System.out.println(lengaburuFamilyTree.getRelationship("Vritha",Relation.CHILDREN));
        System.out.println(lengaburuFamilyTree.getRelationship("Amba",Relation.CHILDREN));  // dritha tritha, vritha
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.CHILDREN));
        System.out.println("");

        System.out.println("----------------Daughter-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.DAUGHTER));  //  satya
        System.out.println(lengaburuFamilyTree.getRelationship("Dritha",Relation.DAUGHTER));
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.DAUGHTER));  // lavnya
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.DAUGHTER));  // lavnya
        System.out.println(lengaburuFamilyTree.getRelationship("Vritha",Relation.DAUGHTER));
        System.out.println(lengaburuFamilyTree.getRelationship("Amba",Relation.DAUGHTER));  // dritha tritha
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.DAUGHTER));
        System.out.println("");

        System.out.println("----------------Son-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.SON));  //  chit, ish, vich, aras
        System.out.println(lengaburuFamilyTree.getRelationship("Vich",Relation.SON));
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.SON));  // laki
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.SON));  // laki
        System.out.println(lengaburuFamilyTree.getRelationship("Vritha",Relation.SON));
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.SON));
        System.out.println("");

        System.out.println("----------------Siblings-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.SIBLINGS));
        System.out.println(lengaburuFamilyTree.getRelationship("Vich",Relation.SIBLINGS));  // chit, ish, aras, satya
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.SIBLINGS));  // ahit
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.SIBLINGS));  
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.SIBLINGS));
        System.out.println("");
        
        System.out.println("----------------Sister-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.SISTER));
        System.out.println(lengaburuFamilyTree.getRelationship("Vich",Relation.SISTER));  // satya
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.SISTER));
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.SISTER));  
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.SISTER));
        System.out.println("");

        System.out.println("----------------Brother-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.BROTHER));
        System.out.println(lengaburuFamilyTree.getRelationship("Vich",Relation.BROTHER));  // chit, ish, aras
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.BROTHER));  // ahit
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.BROTHER));
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.BROTHER));
        System.out.println("");

        System.out.println("----------------Brother_In_Law-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.BROTHER_IN_LAW));
        System.out.println(lengaburuFamilyTree.getRelationship("Lika",Relation.BROTHER_IN_LAW));  // chit, ish, aras
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.BROTHER_IN_LAW));
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.BROTHER_IN_LAW));  // ahit
        System.out.println(lengaburuFamilyTree.getRelationship("Ahit",Relation.BROTHER_IN_LAW));  // arit
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.BROTHER_IN_LAW));
        System.out.println(lengaburuFamilyTree.getRelationship("Vich",Relation.BROTHER_IN_LAW));  // vyan
        System.out.println("");

        System.out.println("----------------Sister_In_Law-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.SISTER_IN_LAW));
        System.out.println(lengaburuFamilyTree.getRelationship("Lika",Relation.SISTER_IN_LAW));  // satya
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.SISTER_IN_LAW));
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.SISTER_IN_LAW));
        System.out.println(lengaburuFamilyTree.getRelationship("Ahit",Relation.SISTER_IN_LAW));
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.SISTER_IN_LAW));
        System.out.println(lengaburuFamilyTree.getRelationship("Vich",Relation.SISTER_IN_LAW));  // amba chitra
        System.out.println("");
        
        System.out.println("----------------Maternal Aunt-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.MATERNAL_AUNT));
        System.out.println(lengaburuFamilyTree.getRelationship("Lika",Relation.MATERNAL_AUNT));
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.MATERNAL_AUNT));
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.MATERNAL_AUNT));
        System.out.println(lengaburuFamilyTree.getRelationship("Ahit",Relation.MATERNAL_AUNT));
        System.out.println(lengaburuFamilyTree.getRelationship("Yodhan",Relation.MATERNAL_AUNT));  // tritha
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.MATERNAL_AUNT));
        System.out.println("");
        
        System.out.println("----------------Maternal Uncle-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.MATERNAL_UNCLE));
        System.out.println(lengaburuFamilyTree.getRelationship("Lika",Relation.MATERNAL_UNCLE));
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.MATERNAL_UNCLE));
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.MATERNAL_UNCLE));
        System.out.println(lengaburuFamilyTree.getRelationship("Ahit",Relation.MATERNAL_UNCLE));
        System.out.println(lengaburuFamilyTree.getRelationship("Yodhan",Relation.MATERNAL_UNCLE));  // vritha
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.MATERNAL_UNCLE));
        System.out.println("");
        
        System.out.println("----------------Paternal Aunt-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.PATERNAL_AUNT));
        System.out.println(lengaburuFamilyTree.getRelationship("Lika",Relation.PATERNAL_AUNT));
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.PATERNAL_AUNT));   // satya
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.PATERNAL_AUNT));
        System.out.println(lengaburuFamilyTree.getRelationship("Ahit",Relation.PATERNAL_AUNT));   // satya
        System.out.println(lengaburuFamilyTree.getRelationship("Yodhan",Relation.PATERNAL_AUNT));
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.PATERNAL_AUNT));
        System.out.println("");

        System.out.println("----------------Paternal Uncle-------------------");
        System.out.println(lengaburuFamilyTree.getRelationship("Anga",Relation.PATERNAL_UNCLE));
        System.out.println(lengaburuFamilyTree.getRelationship("Lika",Relation.PATERNAL_UNCLE));
        System.out.println(lengaburuFamilyTree.getRelationship("Jnki",Relation.PATERNAL_UNCLE));  // chit, ish, vich
        System.out.println(lengaburuFamilyTree.getRelationship("Arit",Relation.PATERNAL_UNCLE));
        System.out.println(lengaburuFamilyTree.getRelationship("Ahit",Relation.PATERNAL_UNCLE));  // chit, ish, vich
        System.out.println(lengaburuFamilyTree.getRelationship("Yodhan",Relation.PATERNAL_UNCLE));
        System.out.println(lengaburuFamilyTree.getRelationship("Drithaaa",Relation.PATERNAL_UNCLE));
        System.out.println("");

    }
}

/**
 * RULES
 * d output the people corresponding to the relationship in the order in  which they were added to the family tree :for some reason it isn't
 * The test file will contain only commands to modify or verify the family tree
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
 * For a solution without build system, we want you to name your MainTester class as Geektrust.java. This is the file that
 * will contain your main method. We build and run the solution by using the following commands.
 * <p>
 * Follow Check list - submitting code
 **/

/**
 Remember
 Edit the build file to set your ‘{your.qualified.name.of.main.class}’ and add your dependencies if any. Ensure the
 generated executable is named ‘geektrust.jar’.

 For a solution without build system, we want you to name your MainTester class as Geektrust.java. This is the file that
 will contain your main method. We build and run the solution by using the following commands.

 Follow Check list - submitting code
 **/