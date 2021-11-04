package com.example.geektrust;

public class DeleteInFinalIteration {

    public static void main(String[] args) {

        Dynasty lengaburuFamilyTree = new Dynasty("Anga", "Shan", "Lengaburu");

        System.out.print(lengaburuFamilyTree.addKid("Anga", "Chit", Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Anga", "Ish", Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Anga", "Vich", Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Anga", "Aras", Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Anga", "Satya", Gender.FEMALE));
        System.out.println();
        System.out.println();

/////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.print(lengaburuFamilyTree.marry("Amba", "Chit"));
        System.out.print(lengaburuFamilyTree.marry("Lika", "Vich"));
        System.out.print(lengaburuFamilyTree.marry("Chitra", "Aras"));
        System.out.print(lengaburuFamilyTree.marry("Satya", "Vyan"));

        System.out.print(lengaburuFamilyTree.addKid("Amba", "Dritha", Gender.FEMALE));
        System.out.print(lengaburuFamilyTree.addKid("Amba", "Tritha", Gender.FEMALE));
        System.out.print(lengaburuFamilyTree.addKid("Amba", "Vritha", Gender.MALE));

        System.out.print(lengaburuFamilyTree.addKid("Lika", "Vila", Gender.FEMALE));
        System.out.print(lengaburuFamilyTree.addKid("Lika", "Chika", Gender.FEMALE));

        System.out.print(lengaburuFamilyTree.addKid("Chitra", "Jnki", Gender.FEMALE));
        System.out.print(lengaburuFamilyTree.addKid("Chitra", "Ahit", Gender.MALE));

        System.out.print(lengaburuFamilyTree.addKid("Satya", "Asva", Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Satya", "Vyas", Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Satya", "Atya", Gender.FEMALE));
        System.out.println();
        System.out.println();
/////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.print(lengaburuFamilyTree.marry("Dritha", "Jaya"));
        System.out.print(lengaburuFamilyTree.marry("Jnki", "Arit"));
        System.out.print(lengaburuFamilyTree.marry("Satvy", "Asva"));
        System.out.print(lengaburuFamilyTree.marry("Krpi", "Vyas"));

        System.out.print(lengaburuFamilyTree.addKid("Dritha", "Yodhan", Gender.MALE));

        System.out.print(lengaburuFamilyTree.addKid("Jnki", "Laki", Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Jnki", "Lavnya", Gender.FEMALE));

        System.out.print(lengaburuFamilyTree.addKid("Satvy", "Vasa", Gender.MALE));

        System.out.print(lengaburuFamilyTree.addKid("Krpi", "Kriya", Gender.MALE));
        System.out.print(lengaburuFamilyTree.addKid("Krpi", "Krithi", Gender.FEMALE));
        System.out.println("");
        System.out.println("");
///////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("----------------Mother-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.MOTHER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Dritha", Relation.MOTHER).equals("Amba"));   // Amba
        System.out.print(lengaburuFamilyTree.getRelationship("Jaya", Relation.MOTHER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.MOTHER).equals("PERSON_NOT_FOUND"));
        System.out.print(lengaburuFamilyTree.getRelationship("Vritha", Relation.MOTHER).equals("Amba"));   // Amba
        System.out.println("");

        System.out.println("----------------Father-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.FATHER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Dritha", Relation.FATHER).equals("Chit"));   // Chit
        System.out.print(lengaburuFamilyTree.getRelationship("Jaya", Relation.FATHER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.FATHER).equals("PERSON_NOT_FOUND"));
        System.out.println("");

        System.out.println("----------------Children-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.CHILDREN).equals("Chit Ish Vich Aras Satya"));  //  chit, ish, vich, aras, satya
        System.out.print(lengaburuFamilyTree.getRelationship("Dritha", Relation.CHILDREN).equals("Yodhan"));// yodhan
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.CHILDREN).equals("Laki Lavnya"));  // laki, lavnya
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.CHILDREN).equals("Laki Lavnya"));  // laki, lavnya
        System.out.print(lengaburuFamilyTree.getRelationship("Vritha", Relation.CHILDREN).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Amba", Relation.CHILDREN).equals("Dritha Tritha Vritha"));  // dritha tritha, vritha
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.CHILDREN).equals("PERSON_NOT_FOUND"));
        System.out.println("");

        System.out.println("----------------Daughter-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.DAUGHTER).equals("Satya"));  //  satya
        System.out.print(lengaburuFamilyTree.getRelationship("Dritha", Relation.DAUGHTER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.DAUGHTER).equals("Lavnya"));  // lavnya
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.DAUGHTER).equals("Lavnya"));  // lavnya
        System.out.print(lengaburuFamilyTree.getRelationship("Vritha", Relation.DAUGHTER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Amba", Relation.DAUGHTER).equals("Dritha Tritha"));  // dritha tritha
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.DAUGHTER).equals("PERSON_NOT_FOUND"));
        System.out.println("");

        System.out.println("----------------Son-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.SON).equals("Chit Ish Vich Aras"));  //  chit, ish, vich, aras
        System.out.print(lengaburuFamilyTree.getRelationship("Vich", Relation.SON).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.SON).equals("Laki"));  // laki
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.SON).equals("Laki"));  // laki
        System.out.print(lengaburuFamilyTree.getRelationship("Vritha", Relation.SON).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.SON).equals("PERSON_NOT_FOUND"));
        System.out.println("");

        System.out.println("----------------Siblings-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.SIBLING).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Vich", Relation.SIBLING).equals("Chit Ish Aras Satya"));  // chit, ish, aras, satya
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.SIBLING).equals("Ahit"));  // ahit
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.SIBLING).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.SIBLING).equals("PERSON_NOT_FOUND"));
        System.out.println("");

        System.out.println("----------------Sister-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.SISTER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Vich", Relation.SISTER).equals("Satya"));  // satya
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.SISTER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.SISTER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.SISTER).equals("PERSON_NOT_FOUND"));
        System.out.println("");

        System.out.println("----------------Brother-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.BROTHER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Vich", Relation.BROTHER).equals("Chit Ish Aras"));  // chit, ish, aras
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.BROTHER).equals("Ahit"));  // ahit
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.BROTHER).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.BROTHER).equals("PERSON_NOT_FOUND"));
        System.out.println("");

        System.out.println("----------------Brother_In_Law-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.BROTHER_IN_LAW).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Lika", Relation.BROTHER_IN_LAW).equals("Chit Ish Aras"));  // chit, ish, aras
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.BROTHER_IN_LAW).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.BROTHER_IN_LAW).equals("Ahit"));  // ahit
        System.out.print(lengaburuFamilyTree.getRelationship("Ahit", Relation.BROTHER_IN_LAW).equals("Ahit"));  // arit
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.BROTHER_IN_LAW).equals("PERSON_NOT_FOUND"));
        System.out.print(lengaburuFamilyTree.getRelationship("Vich", Relation.BROTHER_IN_LAW).equals("Vyan"));  // vyan
        System.out.println("");

        System.out.println("----------------Sister_In_Law-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.SISTER_IN_LAW).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Lika", Relation.SISTER_IN_LAW).equals("Satya"));  // satya
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.SISTER_IN_LAW).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.SISTER_IN_LAW).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Ahit", Relation.SISTER_IN_LAW).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.SISTER_IN_LAW).equals("PERSON_NOT_FOUND"));
        System.out.print(lengaburuFamilyTree.getRelationship("Vich", Relation.SISTER_IN_LAW).equals("Amba Chitra"));  // amba chitra
        System.out.println("");

        System.out.println("----------------Maternal Aunt-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.MATERNAL_AUNT).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Lika", Relation.MATERNAL_AUNT).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.MATERNAL_AUNT).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.MATERNAL_AUNT).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Ahit", Relation.MATERNAL_AUNT).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Yodhan", Relation.MATERNAL_AUNT).equals("Tritha"));  // tritha
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.MATERNAL_AUNT).equals("PERSON_NOT_FOUND"));
        System.out.println("");

        System.out.println("----------------Maternal Uncle-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.MATERNAL_UNCLE).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Lika", Relation.MATERNAL_UNCLE).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.MATERNAL_UNCLE).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.MATERNAL_UNCLE).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Ahit", Relation.MATERNAL_UNCLE).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Yodhan", Relation.MATERNAL_UNCLE).equals("Vritha"));  // vritha
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.MATERNAL_UNCLE).equals("PERSON_NOT_FOUND"));
        System.out.println("");

        System.out.println("----------------Paternal Aunt-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.PATERNAL_AUNT).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Lika", Relation.PATERNAL_AUNT).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.PATERNAL_AUNT).equals("Satya"));
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.PATERNAL_AUNT).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Ahit", Relation.PATERNAL_AUNT).equals("Satya"));
        System.out.print(lengaburuFamilyTree.getRelationship("Yodhan", Relation.PATERNAL_AUNT).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.PATERNAL_AUNT).equals("PERSON_NOT_FOUND"));
        System.out.println("");

        System.out.println("----------------Paternal Uncle-------------------");
        System.out.print(lengaburuFamilyTree.getRelationship("Anga", Relation.PATERNAL_UNCLE).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Lika", Relation.PATERNAL_UNCLE).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Jnki", Relation.PATERNAL_UNCLE).equals("Chit Ish Vich"));
        System.out.print(lengaburuFamilyTree.getRelationship("Arit", Relation.PATERNAL_UNCLE).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Ahit", Relation.PATERNAL_UNCLE).equals("Chit Ish Vich"));
        System.out.print(lengaburuFamilyTree.getRelationship("Yodhan", Relation.PATERNAL_UNCLE).equals("NONE"));
        System.out.print(lengaburuFamilyTree.getRelationship("Drithaaa", Relation.PATERNAL_UNCLE).equals("PERSON_NOT_FOUND"));
        System.out.println("");

    }
}