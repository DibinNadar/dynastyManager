package com.example.geektrust;

import org.junit.jupiter.api.*;

class DynastyTest {

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
            Assertions.assertEquals("CHILD_ADDITION_FAILED", dynasty.addKid("Shan", "Ahit", Gender.MALE));
        }

        @Test
        void should_FailToAddKid_When_MotherHasNoPartner() {
            dynasty.addKid("Anga", "MotherWithNoPartner", Gender.FEMALE);

            Assertions.assertEquals("CHILD_ADDITION_FAILED", dynasty.addKid("MotherWithNoPartner", "KidName", Gender.MALE));
        }


        @Test
        void should_FailToAddKid_When_MotherIsPassedAsKid() {
            Assertions.assertEquals("CHILD_ADDITION_FAILED", dynasty.addKid("Anga", "Anga", Gender.FEMALE));
        }

        @Test
        void should_AddKid_When_AddedToFemaleMarriedMother() {
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
        void should_FailToMarry_When_GenderForFemaleIsIncorrect() {
            dynasty.addKid("Anga", "Laki", Gender.MALE);

            dynasty.marry("Laki", "Vasa");

            Assertions.assertFalse(dynasty.marry("Laki", "Vasa"));
        }

        @Test
        void should_FailToMarry_When_GenderForMaleIsIncorrect() {
            dynasty.addKid("Anga", "Krpi", Gender.FEMALE);

            dynasty.marry("Lavnya", "Krpi");

            Assertions.assertFalse(dynasty.marry("Lavnya", "Krpi"));
        }

        @Test
        void should_AddNodeToDynasty_When_MarriedIntoDynasty() {
            dynasty.addKid("Anga", "Kriya", Gender.FEMALE);

            dynasty.marry("Kriya", "Chit");

            Assertions.assertTrue(dynasty.containsOne("Chit"));
        }

        @Test
        void should_SetGenderOfMale_When_MarriedIntoDynasty() {
            dynasty.addKid("Anga", "Krithi", Gender.FEMALE);

            dynasty.marry("Krithi", "Ish");

            Assertions.assertEquals(dynasty.getOne("Ish").getGender(), Gender.MALE);
        }

        @Test
        void should_SetGenderOfFemale_When_MarriedIntoDynasty() {
            dynasty.addKid("Anga", "Vich", Gender.MALE);

            dynasty.marry("Satya", "Vich");

            Assertions.assertEquals(dynasty.getOne("Satya").getGender(), Gender.FEMALE);
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class getRelationship {
        Dynasty dynasty = new Dynasty("Lara", "Cregennen", "ElderBlood");
        String noValidRelations = "NONE";
        String personNotFound = "PERSON_NOT_FOUND";

        @BeforeEach
        void setUp() {

            dynasty.addKid("Lara", "Riannon", Gender.FEMALE);
            dynasty.marry("Riannon", "Goidemar");

            dynasty.addKid("Riannon", "Adela", Gender.FEMALE);
            dynasty.addKid("Riannon", "Fiona", Gender.FEMALE);
            dynasty.addKid("Riannon", "Amavet", Gender.MALE);
            dynasty.addKid("Riannon", "Cedric", Gender.MALE);
            dynasty.marry("Fiona", "Coram");
            dynasty.marry("Anna", "Amavet");
            dynasty.marry("Berengaria", "Cedric");

            dynasty.addKid("Fiona", "Corbett", Gender.MALE);
            dynasty.addKid("Anna", "Muriel", Gender.FEMALE);
            dynasty.addKid("Anna", "Crispin", Gender.MALE);
            dynasty.marry("Elen", "Corbett");
            dynasty.marry("Muriel", "Robert");
            dynasty.marry("Metinna", "Crispin");

            dynasty.addKid("Elen", "Dagorad", Gender.MALE);
            dynasty.addKid("Muriel", "Adalia", Gender.FEMALE);
            dynasty.marry("Adalia", "Dagorad");

            dynasty.addKid("Adalia", "Calanthe", Gender.FEMALE);
            dynasty.marry("Calanthe", "Roegnar");

            dynasty.addKid("Calanthe", "Pavetta", Gender.FEMALE);
            dynasty.marry("Pavetta", "Duny");

            dynasty.addKid("Pavetta", "Cirilla", Gender.FEMALE);
        }

//        @ParameterizedTest
//        @MethodSource("relationList")
//        void should_ReturnPersonNotFound_When_NodeNotInDynasty(Relation relation) {
//            Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", relation));
//        }
//
//        private Relation[] relationList() {
//            return Relation.values();
//        }
//
//        @ParameterizedTest
//        @CsvSource({"MOTHER", "FATHER", "SIBLING", "SISTER", "BROTHER", "MATERNAL_AUNT", "MATERNAL_UNCLE", "PATERNAL_AUNT", "PATERNAL_UNCLE"})
//        void should_ReturnNone_When_NodeFromDifferentBloodline(Relation relation) {
//            Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Robert", relation));
//        }


        @Nested
        @DisplayName("Testing Queries to get Mother")
        class mother {

            @Test
            void should_ReturnNone_When_NodeFromDifferentBloodline() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Robert", Relation.MOTHER));
            }

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.MOTHER));
            }

            @Test
            void should_ReturnMother_When_NodeInBloodline() {
                Assertions.assertEquals("Riannon", dynasty.getRelationship("Cedric", Relation.MOTHER));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Father")
        class father {

            @Test
            void should_ReturnNone_When_NodeFromDifferentBloodline() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Robert", Relation.FATHER));
            }

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.FATHER));
            }

            @Test
            void should_ReturnFather_When_NodeInBloodline() {
                Assertions.assertEquals("Coram", dynasty.getRelationship("Corbett", Relation.FATHER));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Children")
        class children {

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.CHILDREN));
            }

            @Test
            void should_ReturnNone_When_NodeIsUnmarried() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Adela", Relation.CHILDREN));
            }

            @Test
            void should_ReturnNone_When_NodeIsMarriedWithoutChildren() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Metinna", Relation.CHILDREN));
            }

            @Test
            void should_ReturnChildren_When_NodeHasChildren() {
                Assertions.assertEquals("Muriel Crispin", dynasty.getRelationship("Anna", Relation.CHILDREN));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Daughters")
        class daughters {

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.DAUGHTER));
            }

            @Test
            void should_ReturnNone_When_NodeIsUnmarried() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Adela", Relation.DAUGHTER));
            }

            @Test
            void should_ReturnNone_When_NodeIsMarriedWithoutDaughter() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Crispin", Relation.DAUGHTER));
            }

            @Test
            void should_ReturnDaughter_When_NodeHasDaughter() {
                Assertions.assertEquals("Pavetta", dynasty.getRelationship("Calanthe", Relation.DAUGHTER));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Sons")
        class sons {

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.SON));
            }

            @Test
            void should_ReturnNone_When_NodeIsUnmarried() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Adela", Relation.SON));
            }

            @Test
            void should_ReturnNone_When_NodeIsMarriedWithoutSon() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Berengaria", Relation.SON));
            }

            @Test
            void should_ReturnSon_When_NodeHasSon() {
                Assertions.assertEquals("Amavet Cedric", dynasty.getRelationship("Goidemar", Relation.SON));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Siblings")
        class siblings {

            @Test
            void should_ReturnNone_When_NodeFromDifferentBloodline() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Robert", Relation.SIBLING));
            }

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.SIBLING));
            }

            @Test
            void should_ReturnNone_When_NodeHasNoSiblings() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Pavetta", Relation.SIBLING));
            }

            @Test
            void should_ReturnSiblings_When_NodeHasSiblings() {
                Assertions.assertEquals("Crispin", dynasty.getRelationship("Muriel", Relation.SIBLING));
            }
        }


        @Nested
        @DisplayName("Testing Queries to get Sisters")
        class sisters {

            @Test
            void should_ReturnNone_When_NodeFromDifferentBloodline() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Robert", Relation.SISTER));
            }

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.SISTER));
            }

            @Test
            void should_ReturnNone_When_NodeHasNoSisters() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Cirilla", Relation.SISTER));
            }

            @Test
            void should_ReturnSisters_When_NodeHasSisters() {
                Assertions.assertEquals("Adela", dynasty.getRelationship("Fiona", Relation.SISTER));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Brothers")
        class brothers {

            @Test
            void should_ReturnNone_When_NodeFromDifferentBloodline() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Robert", Relation.BROTHER));
            }

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.BROTHER));
            }

            @Test
            void should_ReturnNone_When_NodeHasNoBrothers() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Dagorad", Relation.BROTHER));
            }

            @Test
            void should_ReturnBrothers_When_NodeHasBrothers() {
                Assertions.assertEquals("Cedric", dynasty.getRelationship("Amavet", Relation.BROTHER));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Sisters in law")
        class sisterInLaw {

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.SISTER_IN_LAW));
            }

            @Test
            void should_ReturnNone_When_NodeHasNoSister_in_laws() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Adalia", Relation.SISTER_IN_LAW));
            }

            @Test
            void should_ReturnSister_in_laws_When_NodeHasSister_in_laws() {
                Assertions.assertEquals("Adela", dynasty.getRelationship("Coram", Relation.SISTER_IN_LAW));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Brothers in law")
        class brotherInLaw {

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.BROTHER_IN_LAW));
            }

            @Test
            void should_ReturnNone_When_NodeHasNoBrother_in_laws() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Roegnar", Relation.BROTHER_IN_LAW));
            }

            @Test
            void should_ReturnBrother_in_laws_When_NodeHasBrother_in_laws() {
                Assertions.assertEquals("Crispin", dynasty.getRelationship("Robert", Relation.BROTHER_IN_LAW));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Maternal Aunt")
        class maternalAunt {

            @Test
            void should_ReturnNone_When_NodeFromDifferentBloodline() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Robert", Relation.MATERNAL_AUNT));
            }

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.MATERNAL_AUNT));
            }

            @Test
            void should_ReturnNone_When_NodeHasNoMaternalAunts() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Adalia", Relation.MATERNAL_AUNT));
            }

            @Test
            void should_ReturnMaternal_Aunts_When_NodeHasMaternalAunts() {
                Assertions.assertEquals("Adela", dynasty.getRelationship("Corbett", Relation.MATERNAL_AUNT));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Maternal Uncle")
        class maternalUncle {

            @Test
            void should_ReturnNone_When_NodeFromDifferentBloodline() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Robert", Relation.MATERNAL_UNCLE));
            }

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.MATERNAL_UNCLE));
            }

            @Test
            void should_ReturnNone_When_NodeHasNoMaternalUncles() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Riannon", Relation.MATERNAL_UNCLE));
            }

            @Test
            void should_ReturnMaternal_Uncles_When_NodeHasMaternalUncles() {
                Assertions.assertEquals("Crispin", dynasty.getRelationship("Adalia", Relation.MATERNAL_UNCLE));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Paternal Aunt")
        class paternalAunt {

            @Test
            void should_ReturnNone_When_NodeFromDifferentBloodline() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Robert", Relation.PATERNAL_AUNT));
            }

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.PATERNAL_AUNT));
            }

            @Test
            void should_ReturnNone_When_NodeHasNoPaternalAunts() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Lara", Relation.PATERNAL_AUNT));
            }

            @Test
            void should_ReturnPaternal_Aunts_When_NodeHasPaternalAunts() {
                Assertions.assertEquals("Adela Fiona", dynasty.getRelationship("Muriel", Relation.PATERNAL_AUNT));
            }
        }

        @Nested
        @DisplayName("Testing Queries to get Paternal Uncle")
        class paternalUncle {

            @Test
            void should_ReturnNone_When_NodeFromDifferentBloodline() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Robert", Relation.PATERNAL_UNCLE));
            }

            @Test
            void should_ReturnPersonNotFound_When_NodeNotInDynasty() {
                Assertions.assertEquals(personNotFound, dynasty.getRelationship("UnknownNode", Relation.PATERNAL_UNCLE));
            }

            @Test
            void should_ReturnNone_When_NodeHasNoPaternalUncles() {
                Assertions.assertEquals(noValidRelations, dynasty.getRelationship("Cirilla", Relation.PATERNAL_UNCLE));
            }

            @Test
            void should_ReturnPaternal_Uncles_When_NodeHasPaternalUncles() {
                Assertions.assertEquals("Cedric", dynasty.getRelationship("Crispin", Relation.PATERNAL_UNCLE));
            }
        }
    }

    @Test
    void should_ReturnQueenNode_When_Called(){
        Dynasty dynasty = new Dynasty("Anga", "Shan", "Lengaburu");

        Assertions.assertEquals("Anga",dynasty.getQueenNode().getName());
    }

    @Test
    void should_ReturnKingNode_When_Called(){
        Dynasty dynasty = new Dynasty("Anga", "Shan", "Lengaburu");

        Assertions.assertEquals("Shan",dynasty.getKingNode().getName());
    }

    @Test
    void should_ReturnDynasty_When_Called(){
        Dynasty dynasty = new Dynasty("Anga", "Shan", "Lengaburu");

        Assertions.assertEquals("Lengaburu",dynasty.getDynastyName());
    }

}