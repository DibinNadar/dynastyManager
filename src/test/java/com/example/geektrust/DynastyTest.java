package com.example.geektrust;

import org.junit.jupiter.api.*;

class DynastyTest {

    @BeforeEach
    void setUp() {
        System.out.println("Ahoy");
    }

    @Nested
    @DisplayName("Testing adding kid to Family Tree")
    class addKid {
        Dynasty dynasty = new Dynasty("Anga", "Shan", "Lengaburu");

        @Test
        void should_FailToAddKid_When_MotherNotInDynasty() {
            Assertions.assertEquals("PERSON_NOT_FOUND", dynasty.addKid("UnknownMother", "KidName", Gender.FEMALE));
        }

        @Test
        void should_FailToAddKid_When_KidAlreadyInDynasty() {
            dynasty.addKid("Anga", "Atya", Gender.FEMALE);

            Assertions.assertEquals("CHILD_ADDITION_FAILED", dynasty.addKid("Anga", "Atya", Gender.FEMALE));
        }

        @Test
        void should_FailToAddKid_When_MotherIsMale() {
            Assertions.assertEquals("CHILD_ADDITION_FAILED", dynasty.addKid("Shan","Ahit",Gender.MALE));
        }

        @Test
        void should_FailToAddKid_When_MotherHasNoPartner(){
            dynasty.addKid("Anga","MotherWithNoPartner",Gender.FEMALE);

            Assertions.assertEquals("CHILD_ADDITION_FAILED",dynasty.addKid("MotherWithNoPartner","KidName",Gender.MALE));
        }


        @Test
        void should_FailToAddKid_When_MotherIsPassedAsKid(){
            Assertions.assertEquals("CHILD_ADDITION_FAILED", dynasty.addKid("Anga", "Anga", Gender.FEMALE));
        }

        @Test
        void should_AddKid_When_AddedToFemaleMarriedMother(){
            Assertions.assertEquals("CHILD_ADDITION_SUCCEEDED", dynasty.addKid("Anga", "Ahit", Gender.MALE));
        }

    }

    @Nested
    @DisplayName("Testing marry() between Male and Female Nodes")
    class marry {

        Dynasty dynasty = new Dynasty("Anga", "Shan", "Lengaburu");


        @Test
        @DisplayName("Testing marry() for unknown Nodes")
        void should_FailToMarry_When_FemaleAndMaleNotInDynasty() {

            Assertions.assertFalse(dynasty.marry("unknownFemale", "unknownMale"));
        }

        @Test
        void should_FailToMarry_When_FemaleIsAlreadyMarried() {
            dynasty.addKid("Anga", "Jaya", Gender.FEMALE);

            dynasty.marry("Jaya", "Vyas");

            Assertions.assertFalse(dynasty.marry("Jaya", "Arit"));
        }


        @Test
        void should_FailToMarry_When_MaleIsAlreadyMarried() {
            dynasty.addKid("Anga", "Yodhan", Gender.MALE);

            dynasty.marry("Satvy", "Yodhan");

            Assertions.assertFalse(dynasty.marry("Satvy", "Yodhan"));
        }

        @Test
        void should_FailToMarry_When_GenderForFemaleIsIncorrect(){
            dynasty.addKid("Anga", "Laki",Gender.MALE);

            dynasty.marry("Laki","Vasa");

            Assertions.assertFalse(dynasty.marry("Laki","Vasa"));
        }

        @Test
        void should_FailToMarry_When_GenderForMaleIsIncorrect(){
            dynasty.addKid("Anga", "Krpi",Gender.FEMALE);

            dynasty.marry("Lavnya","Krpi");

            Assertions.assertFalse(dynasty.marry("Lavnya","Krpi"));
        }

        @Test
        void should_AddNodeToDynasty_When_MarriedIntoDynasty(){
            dynasty.addKid("Anga", "Kriya", Gender.FEMALE);

            dynasty.marry("Kriya","Chit");

            Assertions.assertTrue(dynasty.containsOne("Chit"));
        }

        @Test
        void should_SetGenderOfMale_When_MarriedIntoDynasty(){
            dynasty.addKid("Anga", "Krithi", Gender.FEMALE);

            dynasty.marry("Krithi","Ish");

            Assertions.assertEquals(dynasty.getOne("Ish").getGender(), Gender.MALE);
        }

        @Test
        void should_SetGenderOfFemale_When_MarriedIntoDynasty(){
            dynasty.addKid("Anga", "Vich", Gender.MALE);

            dynasty.marry("Satya","Vich");

            Assertions.assertEquals(dynasty.getOne("Satya").getGender(), Gender.FEMALE);
        }

    }

    @Nested  // TODO holy grail
    class getRelationship {

        // TODO Identify ideal sub tree with and without all relations- 0 1 many
        @Test
        void getRelationship() {
        }
    }



}