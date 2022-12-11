package controller;

import java.util.ArrayList;
import java.util.List;

public class TextParser {

    /*
     * TextParser is a singleton class. Call TextParser.getInstance() to create original instance
     */

    /*
     * TODO - Implement KMP (https://www.geeksforgeeks.org/java-program-for-kmp-algorithm-for-pattern-searching-2/) algorithm for searching the closest match of keys within the country
     *  EG: If they're in Mexico and said "I want to go to Chichen Itza" you should join that string
     *  (using String.join() [https://stackoverflow.com/questions/1978933/a-quick-and-easy-way-to-join-array-elements-with-a-separator-the-opposite-of-sp]
     *  so that it reads "iwanttogotochicentiza". Then loop through attraction1, restaurant1, restaurant2, weaponStore1, etc
     *  to find the best match.
     */

    private static TextParser textParser = null;

    private TextParser() {};

    public String parse(String userInput, List<String> listOfVerbs, List<String> listOfNouns) {
        String resultOfStringParsing;

        if (userInputFoundInListOfVerbs(userInput, listOfVerbs)) {
            // check list of nouns
            resultOfStringParsing = findClosestMatchingNoun(userInput, listOfNouns);
        }
        else {
            resultOfStringParsing = "ERROR - Invalid command. Verb was not found in the list of acceptable verbs.";
        }

        return resultOfStringParsing;
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
        userInput = userInput.toLowerCase().replaceAll("\\s", "");

        for (String noun : listOfNouns) {
            int userInputLength = userInput.length();
            String nounCopy = noun.toLowerCase().replaceAll("\\s", "");

            for (int i = 0; i < userInputLength; i++) {
                Character nounChar = nounCopy.charAt(0);
                Character userInputChar = userInput.charAt(i);

                if (nounChar.equals(userInputChar)) {
                    int substringLength = findSubstring(nounCopy, userInput, i);

                    if (substringLength > longestSubstringLen) {
                        longestSubstringLen = substringLength;
                        closestMatchingNoun = noun;
                    }
                }
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

                if (numOfErrors >= maxNumOfErrors) {
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
