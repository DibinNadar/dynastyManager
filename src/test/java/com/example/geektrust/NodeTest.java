package com.example.geektrust;

import org.junit.jupiter.api.*;

class NodeTest {

    @Nested
    @DisplayName("Test assigning partner to node ")
    class assignPartner {

        @Test
        void should_AddPartner_When_NodeUnmarried() {
            Node unmarriedNode = new Node("Jnki", Gender.FEMALE);

            Assertions.assertTrue(unmarriedNode.assignPartnerIfSingle("Partner Name"));
        }

        @Test
        void should_FailToAddPartner_When_NodeMarried() {
            Node marriedNode = new Node("Shan", Gender.MALE);

            marriedNode.assignPartnerIfSingle("PartnerName");

            Assertions.assertFalse(marriedNode.assignPartnerIfSingle("Different Partner"));
        }

        @Test
        @DisplayName("Test assigning the same partner name again")
        void should_AllowSettingSamePartnerNameAgain_When_NodeMarried() {
            Node marriedNode = new Node("Anga", Gender.FEMALE);

            marriedNode.assignPartnerIfSingle("SameName");
            marriedNode.assignPartnerIfSingle("SameName");

            Assertions.assertEquals("SameName", marriedNode.getPartnerName());
        }

    }

    @Nested
    @DisplayName("Test adding Kid To Mother")
    class assignKidToMother {

        @Test
        void should_addKid_When_ParentIsFemale() {
            Node femaleParent = new Node("Chitra", Gender.FEMALE);

            femaleParent.addKidToMother("Ish");

            Assertions.assertTrue(femaleParent.getKids().contains("Ish"));
        }

        @Test
        void should_FailToAddKid_When_AddingSameKidTwice() {
            Node mother = new Node("Amba", Gender.FEMALE);

            mother.addKidToMother("Vich");
            mother.addKidToMother("Vich");

            Assertions.assertEquals(1, mother.getKids().size());
        }

        @Test
        void should_FailToAddKid_When_ParentIsMale() {
            Node maleParent = new Node("Aras", Gender.MALE);

            maleParent.addKidToMother("Satya");

            Assertions.assertTrue(maleParent.getKids().isEmpty());
        }

    }

    @Nested
    @DisplayName("Test assigning Mother To Kid")
    class assignMotherIfKidHasNoMother {


        @Test
        void should_AssignMother_When_NodeHasNoMother() {
            Node kidNode = new Node("Vyan", Gender.MALE);

            kidNode.assignMotherIfKidHasNoMother("Dritha");

            Assertions.assertEquals("Dritha", kidNode.getMotherName());
        }

        @Test
        void should_FailToAssignMother_When_NodeHasMother() {
            Node kidNode = new Node("Shan", Gender.MALE);
            kidNode.assignMotherIfKidHasNoMother("Dritha");

            kidNode.assignMotherIfKidHasNoMother("Tritha");

            Assertions.assertNotEquals("Tritha", kidNode.getMotherName());
        }

        @Test
        void should_AllowSettingSameMotherNameAgain_When_NodeHasMother() {
            Node kidNode = new Node("Vila", Gender.MALE);

            kidNode.assignMotherIfKidHasNoMother("Vritha");
            kidNode.assignMotherIfKidHasNoMother("Vritha");

            Assertions.assertEquals("Vritha", kidNode.getMotherName());
        }

    }

}