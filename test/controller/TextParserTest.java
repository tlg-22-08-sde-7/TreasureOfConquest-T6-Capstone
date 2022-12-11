package controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TextParserTest {
    TextParser textParser = TextParser.getInstance();
    List<String> listOfVerbs;
    List<String> listOfNouns;
    String userInput;
    String expected;

    @Test
    public void parse() {
        listOfVerbs = new ArrayList<>(List.of("go", "travel"));
        listOfNouns = new ArrayList<String>(List.of("Chichen Itza", "Pyramids", "Chocolate"));
        userInput = "I want to travel to Chichen Itza";
        expected = "Chichen Itza";

        assertEquals(expected, textParser.parse(userInput, listOfVerbs, listOfNouns));
    }
}