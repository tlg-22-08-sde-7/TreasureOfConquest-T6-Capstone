package model;

import java.util.*;

public class Dialogue {

    private static final Map<String, Set<String>> COMMANDS_MAP = new HashMap<>();

    public Dialogue() {};

    public Dialogue(List<String> verbs, List<String> nouns) {
        setCommands(verbs, nouns);
    };

    public String findMatchingNoun(String expectedCommand, String userInput) {
        /*
         * findMatchingNoun parses userInput for the expected command and a noun thats paired
         * to expectedCommand in the COMMANDS_MAP Hashmap.
         * If both are found then it returns the noun, else it identifies the input error in userInput
         * and returns it.
         * CURRENTLY, DOES NOT WORK FOR NOUNS THAT CONSIST OF >1 WORDS
         */
        expectedCommand = expectedCommand.toLowerCase();
        String result = null;
        Set<String> commandKeys = COMMANDS_MAP.keySet();
        Set<String> availableCountries = COMMANDS_MAP.get(expectedCommand);
        List<String> userTextSplit = List.of(userInput.split("\\s")); // splits based on whitespace

        String matchingVerb = null;
        String matchingNoun = null;

        for (String word : userTextSplit) {
            word = word.toLowerCase();

            if (word.equals(expectedCommand)) {
                matchingVerb = word;
            } else if (availableCountries.contains(word)) {
                matchingNoun = word;
            };
        };

        if (matchingVerb == null) {
            result = "verb match not found";
        }
        else if (matchingNoun == null) {
            result = "noun match not found";
        }
        else {
            result = matchingNoun;
        };

        return result;
    };

    private Map<String, Set<String>> getCommands() {
        return COMMANDS_MAP;
    };

    public void setCommands(List<String> verbs, List<String> nouns) {
        List<String> listOfNouns;

        for (int i = 0; i < verbs.size(); i++) {
            listOfNouns = List.of(nouns.get(i).split(","));
            HashSet<String> nounsSet = new HashSet<String>(listOfNouns);

            COMMANDS_MAP.put(verbs.get(i), nounsSet);
        };
    };
};
