package controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TextParserTest {
    TextParser textParser = TextParser.getInstance();
    List<String> listOfVerbs;
    List<String> listOfNouns;
    String userInput1;
    String userInput2;
    String userInput3;
    String userInput4;
    String expected1;
    String expected2;
    String expected3;
    String expected4;


    // Positive Testing
    @Test
    public void parse_shouldReturnNoun_whenGiven_exactMatchingNounAndVerb() {
        listOfVerbs = new ArrayList<>(List.of("go", "travel"));
        listOfNouns = new ArrayList<>(List.of("Chichen Itza", "Pyramids", "Chocolate"));
        userInput1 = "I want to travel to Chichen Itza";
        userInput2 = "I want to go to Chocolate";
        userInput3 = "I want to go to Pyramids";
        expected1 = "Chichen Itza";
        expected2 = "Chocolate";
        expected3 = "Pyramids";

        assertEquals(expected1, textParser.parse(userInput1, listOfVerbs, listOfNouns));
        assertEquals(expected2, textParser.parse(userInput2, listOfVerbs, listOfNouns));
        assertEquals(expected3, textParser.parse(userInput3, listOfVerbs, listOfNouns));
    }

    @Test
    public void parse_shouldReturnNoun_whenGiven_partialMatchingNounAndExactVerb() {
        listOfVerbs = new ArrayList<>(List.of("go", "travel"));
        listOfNouns = new ArrayList<>(List.of("Chichen Itza", "Pyramids", "Chocolate"));
        userInput1 = "travel chihen Itza";
        userInput2 = "I want to go to Chxcxlate";
        userInput3 = "I want to go to Pyremiids";
        userInput4 = "I want to go to Chi";
        expected1 = "Chichen Itza";
        expected2 = "Chocolate";
        expected3 = "Pyramids";
        expected4 = "Chichen Itza";

        assertEquals(expected1, textParser.parse(userInput1, listOfVerbs, listOfNouns));
        assertEquals(expected2, textParser.parse(userInput2, listOfVerbs, listOfNouns));
        assertEquals(expected3, textParser.parse(userInput3, listOfVerbs, listOfNouns));
        assertEquals(expected4, textParser.parse(userInput4, listOfVerbs, listOfNouns));
    }

    // Negative Testing
    @Test
    public void parse_shouldReturnErrorString_whenGiven_nonMatchingVerb() {
        listOfVerbs = new ArrayList<>(List.of("go", "travel"));
        listOfNouns = new ArrayList<>(List.of("Chichen Itza", "Pyramids", "Chocolate"));
        userInput1 = "I want to to Chihen Itza";
        userInput2 = "I want to Chxcxlate";
        userInput3 = "I want to Pyremiids";
        userInput4 = "I want to to Chi";

        assertTrue(textParser.parse(userInput1, listOfVerbs, listOfNouns).toLowerCase().contains("error"));
        assertTrue(textParser.parse(userInput1, listOfVerbs, listOfNouns).toLowerCase().contains("error"));
        assertTrue(textParser.parse(userInput1, listOfVerbs, listOfNouns).toLowerCase().contains("error"));
        assertTrue(textParser.parse(userInput1, listOfVerbs, listOfNouns).toLowerCase().contains("error"));
    }
}