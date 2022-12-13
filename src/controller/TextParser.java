package controller;

import java.util.ArrayList;
import java.util.List;

public class TextParser {

    /*
     * TextParser is a singleton class. Call TextParser.getInstance() to create original instance
     */

    private static TextParser textParser = null;

    private TextParser() {};

    public String parse(String userInput, List<String> listOfVerbs, List<String> listOfNouns) {
        // Quit game
        if ("quit".equals(userInput.toLowerCase())) {
            System.exit(0);
        }

        String resultOfStringParsing;

        if (userInputFoundInListOfVerbs(userInput, listOfVerbs)) {
            // return the closest word matching an entry in listOfNouns
            resultOfStringParsing = findClosestMatchingNoun(userInput, listOfNouns);
        }
        else {
            resultOfStringParsing = "ERROR - Invalid command. Verb was not found in the list of acceptable verbs.";
        }

        return resultOfStringParsing;
    }

    public String parse(String userInput, List<String> lisfOfNouns) {
        if ("quit".equals(userInput.toLowerCase())) {
            System.exit(0);
        }

        return findClosestMatchingNoun(userInput, lisfOfNouns);
    }
    
    private boolean userInputFoundInListOfVerbs(String userInput, List<String> listOfVerbs) {
        boolean inputFound = false;
        String[] userInputList = userInput.split("\\s");

        for (String word : userInputList) {
            if (listOfVerbs.contains(word.toLowerCase())) {
                inputFound = true;
                break;
            }
        }

        return inputFound;
    }

    private String findClosestMatchingNoun(String userInput, List<String> listOfNouns) {
        int longestSubstringLen = 0;
        String closestMatchingNoun = null;
        boolean exactMatchFound = false;
        String concatenatedUserInput = userInput.toLowerCase().replaceAll("\\s", "");

        for (String noun : listOfNouns) {
            String concatenatedNoun = noun.toLowerCase().replaceAll("\\s", "");

            for (int i = 0; i < concatenatedUserInput.length(); i++) {
                Character nounChar = concatenatedNoun.toLowerCase().charAt(0);
                Character userInputChar = concatenatedUserInput.charAt(i);

                if (nounChar.equals(userInputChar)) {
                    int substringLength = findSubstring(concatenatedNoun, concatenatedUserInput, i);

                    if (substringLength == noun.length()) {
                        closestMatchingNoun = noun;
                        exactMatchFound = true;
                        break;
                    }

                    if (substringLength > longestSubstringLen) {
                        longestSubstringLen = substringLength;
                        closestMatchingNoun = noun;
                    }
                }
            }
            if (exactMatchFound) {
                break;
            }
        }

        closestMatchingNoun = (null == closestMatchingNoun) ? "ERROR: No matching noun found" : closestMatchingNoun;

        return closestMatchingNoun;
    }

    private int findSubstring(String noun, String userInput, int userInputIndex) {
        int numOfErrors = 0;
        int matchingSubstringLen = 0;
        int nounIndex = 0;
        final int maxNumOfErrors = 2;

        for (int j = userInputIndex; j < userInput.length(); j++) {
            if (nounIndex >= noun.length()) {
                break;
            }

            Character nounChar = noun.charAt(nounIndex++);
            Character userInputChar = userInput.charAt(j);

            if (nounChar.equals(userInputChar)) {
                matchingSubstringLen++;
            }
            else {
                numOfErrors++;

                if (numOfErrors > maxNumOfErrors) {
                    break;
                }
            }
        }

        return matchingSubstringLen;
    }

    public static TextParser getInstance() {
        if (textParser == null) {
            textParser = new TextParser();
        }

        return textParser;
    }
}
