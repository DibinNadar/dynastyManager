package com.example.geektrust;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class InputProcessorTest {


    @Nested
    class initializeTree {

        @AfterEach
        void tearDown() {
            InputProcessor.currentDynasty = null;
        }

        @Nested
        class createDynasty {

            @Test
            void should_FailToCreateDynasty_When_InvalidNumberOfArgumentsPassed() {
                String inputString = "CREATE_DYNASTY Anga Shan\n";
                InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

                Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> InputProcessor.initializeTree(stream));
            }

            @Test
            void should_ThrowNullPointerException_When_TryingToAddNodesWithoutInitializingDynasty() {
                String inputString = "ADD_CHILD Anga Chit MALE\n";
                InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

                InputProcessor.initializeTree(stream);

                Assertions.assertThrows(NullPointerException.class, () -> InputProcessor.currentDynasty.containsOne("Anga"));
            }

            @Test
            void should_ReturnInvalidLine_When_TryingToCreateASecondDynasty() {
                String inputString = "CREATE_DYNASTY Anga Shan Lengaburu\nCREATE_DYNASTY Rhaella Aerys Targaryen\n";
                InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

                InputProcessor.initializeTree(stream);

                Assertions.assertNotEquals("Targaryen", InputProcessor.currentDynasty.getDynastyName());
            }

            @Test
            void should_CreateDynasty_When_ValidValuesPassed() {
                String inputString = "CREATE_DYNASTY Anga Shan Lengaburu\n";
                InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

                InputProcessor.initializeTree(stream);

                Assertions.assertEquals("Lengaburu", InputProcessor.currentDynasty.getDynastyName());
            }
        }


        @Test
        void should_FailToAddKid_When_InvalidNumberOfArgumentsPassed() {
            String inputString = "CREATE_DYNASTY Anga Shan Lengaburu\nADD_CHILD Anga Chit\n";
            InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

            Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> InputProcessor.initializeTree(stream));
        }

        @Test
        void should_AddKid_When_ValidValuesPassed() {
            String inputString = "CREATE_DYNASTY Anga Shan Lengaburu\nADD_CHILD Anga Chit MALE\n";
            InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

            InputProcessor.initializeTree(stream);

            Assertions.assertEquals("[Chit]", InputProcessor.currentDynasty.getOne("Anga").getKids().toString());
        }

        @Test
        void should_FailToMarry_When_InvalidNumberOfArgumentsPassed() {
            String inputString = "CREATE_DYNASTY Anga Shan Lengaburu\nADD_CHILD Anga Chit MALE\nMARRY Chit\n";
            InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

            Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> InputProcessor.initializeTree(stream));
        }

        @Test
        void should_Marry_When_ValidValuesPassed() {
            String inputString = "CREATE_DYNASTY Anga Shan Lengaburu\nADD_CHILD Anga Chit MALE\nMARRY Amba Chit\n";
            InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

            InputProcessor.initializeTree(stream);

            Assertions.assertEquals("Amba", InputProcessor.currentDynasty.getOne("Chit").getPartnerName());
        }


        @Test
        void should_FailToGetRelation_When_InvalidNumberOfArgumentsPassed() {
            String inputString = "CREATE_DYNASTY Anga Shan Lengaburu\nADD_CHILD Anga Chit MALE\nGET_RELATIONSHIP Chit\n";
            InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

            Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> InputProcessor.initializeTree(stream));
        }

        @Test
        void should_GetRelation_When_ValidValuesPassed() {
            String inputString = "CREATE_DYNASTY Anga Shan Lengaburu\nADD_CHILD Anga Chit MALE\nGET_RELATIONSHIP Chit Mother\n";
            InputStream stream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

            InputProcessor.initializeTree(stream);
        }
    }


    @Test
    void should_CapitaliseWord_When_StringPassed() {
        Assertions.assertEquals("Capital", InputProcessor.toCapitalize("caPiTal  "));
    }

}