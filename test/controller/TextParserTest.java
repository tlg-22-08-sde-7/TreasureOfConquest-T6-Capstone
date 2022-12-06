package controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextParserTest {
    TextParser textParser = TextParser.getInstance();
    String[] listOfVerbs;
    String[] listOfNouns;
    String userInput;
    String expected;

    @Test
    public void parse() {
        listOfVerbs = new String[] {"go", "travel"};
        listOfNouns = new String[] {"Chichen Itza", "Pyramids", "Chocolate"};
        userInput = "I want to travel to Chichen Itza";
        expected = "Chichen Itza";

        assertEquals(expected, textParser.parse(userInput, listOfVerbs, listOfNouns));
    }
}