package model;

import java.util.ArrayList;
import java.util.List;

public class DialogueTester {



    String expectedResult;

    public static void main(String[] args) {
        Dialogue DIALOGUE = new Dialogue();
        List<String> VERBS = new ArrayList<>();
        List<String> NOUNS = new ArrayList<>();
        String userInput;

        String expectedResult;

        VERBS.add("go");
        NOUNS.add("Mexico Japan Nigeria");

        VERBS.add("buy");
        NOUNS.add("Tamales,Gyoza,Jollof Rice");

        DIALOGUE.setCommands(VERBS, NOUNS);

        System.out.println(DIALOGUE.findMatchingNoun("go", "I want to go to Mexico"));
    }
}