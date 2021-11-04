//package com.example.geektrust;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class TestTest {
//
//    ContactManager contactManager;
//
//    @BeforeAll
//    public static void setupAll(){ System.out.println("Once before any tests run"); }
//
//    @BeforeEach // One for each test
//    public void setup(){ contactManager = new ContactManager();  }
//
//    @Nested  // To help with logical organisation!!
//    class nestedTestClassDemo{
//
//        @Test
//        void shouldAddContact() {
//            contactManager.addContact("Tim", "Buchaka","0123456789");
//            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
//            Assertions.assertEquals(1,contactManager.getAllContacts().size());
//            Assertions.assertTrue(contactManager.getAllContacts().stream()
//                    .anyMatch(contact -> contact.getFirstName().equals("Tim") &&
//                            contact.getLastName().equals("Buchaka") &&
//                            contact.getPhoneNumber().equals("0123456789")));
//        }
//
//        @Test
//        @DisplayName("Should not create name when First Name is null")
//        public void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
//            Assertions.assertThrows(RuntimeException.class,()-> contactManager.addContact(null,"Carriel","123"));
//        }
//
//    }
//
//
//    @ParameterizedTest
//    @MethodSource("phoneNumberList")
//    @DisplayName("Repeat Contact Creation Test For Different Phone Numbers using METHOD SOURCE")
//    public void shouldTestContactCreationUsingMethodSourceForPhoneNumber(String phoneNumber){
//        contactManager.addContact("Tom","Jerrious",phoneNumber);
//        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
//        Assertions.assertEquals(1, contactManager.getAllContacts().size());
//    }
//    private List<String> phoneNumberList(){
//        return Arrays.asList("asdqwdaasf", "1236547890","0147852369","0256");
//    }
//
//
//    @Nested  // To help with logical organisation!!
//    class repeatedParameterizedTests{
//
//
//        @ParameterizedTest
//        @CsvSource( {"0147852369", "1236547890","asdqwdaasf","0256"} )
//        @DisplayName("Repeat Contact Creation Test For Different Phone Numbers using CSV SOURCE")
//        public void shouldTestContactCreationUsingCSV_SourceForPhoneNumber(String phoneNumber){
//            contactManager.addContact("Tom","Jerrious",phoneNumber);
//            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
//            Assertions.assertEquals(1, contactManager.getAllContacts().size());
//        }
//
//    }
//
//
//    @AfterEach
//    public void tearDown(){
//        System.out.println("Executed after each method ");
//    }
//
//    @AfterAll
//    public static void tearDownAll(){
//        System.out.println("Executed Once after all methods are executed");
//    }
//
//
//}
