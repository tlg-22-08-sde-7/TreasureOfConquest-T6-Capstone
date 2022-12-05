//package model;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CommandsTest {
//
//    private static final Commands COMMANDS = new Commands();
//    private static final List<String> VERBS = new ArrayList<>();
//    private static final List<String> NOUNS = new ArrayList<>();
//    private static String category;
//
//    @Test
//    public void findMatchingNoun_ShouldReturnMatchingNoun_WhenVerbAndNounFound() {
//        category = "flights";
//
//        assertEquals("mexico", COMMANDS.findMatchingNoun(category, "I want to go to Mexico"));
//        assertEquals("japan", COMMANDS.findMatchingNoun(category, "GO to JAPAN"));
//        assertEquals("nigeria", COMMANDS.findMatchingNoun(category, "Ay bro I want to gO to NiGeRiA"));
//        assertEquals("canada", COMMANDS.findMatchingNoun(category, "Canada Go"));
//
//        category = "foods";
//
//        assertEquals("tamales", COMMANDS.findMatchingNoun(category, "i want to buy a lot of tamales"));
//        assertEquals("gyoza", COMMANDS.findMatchingNoun(category, "bUY gyoza"));
//        assertEquals("jollofrice", COMMANDS.findMatchingNoun(category, "jollofrice is good i want to BuY it"));
//        assertEquals("timhortons", COMMANDS.findMatchingNoun(category, "timhortons has good coffee. i want to BUY some"));
//    };
//
//    @Test
//    public void findMatchingNoun_ShouldReturn_NounMatchNotFound_WhenInvalidVerb() {
//        String expectedResult = "noun match not found";
//
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "I want to go to Puebla"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "GO to JA PAN"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "Ay bro I want to gO to Africa"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "adanac go"));
//
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "i want to bUy a lot of tamles"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "buy gyozaaa"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "jollo frice is good i want to BuY it"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "timhorto ns has good coffee. i want to BUY some"));
//    };
//
//    @Test
//    public void findMatchingNoun_ShouldReturn_VerbMatchNotFound_WhenInvalidVerb() {
//        String expectedResult = "verb match not found";
//
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "I want to oo to Mexico"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "GOo to JAPAN"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "Ay bro I want to goO to NiGeRiA"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "i want to buy canada"));
//
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "i want to go a lot of tamales"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "get gyoza"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "jollofrice is good i want to BuuY it"));
//        assertEquals(expectedResult, COMMANDS.findMatchingNoun(category, "timhortons has good coffee. i want to bBUY some"));
//    };
//};