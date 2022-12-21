package com.treasuresconquests.engine;

import com.treasuresconquests.app.TreasuresConApp;

import java.util.List;
import java.util.Map;

import static com.treasuresconquests.app.TreasuresConApp.ANSI_RED;
import static com.treasuresconquests.app.TreasuresConApp.ANSI_RESET;

public class TextParser {

    /*
     * TextParser is a singleton class. Call TextParser.getInstance() to create original instance
     */

    private static TextParser textParser = null;
    private Map<String, NPC> npc;

    private TextParser() {};

    public String parse(String userInput, List<String> listOfVerbs, List<String> listOfNouns) {
        String resultOfStringParsing = null;

        // Quit game
        if ("quit".equals(userInput.toLowerCase())) {
            System.exit(0);
        } else if ("help".equalsIgnoreCase(userInput.toLowerCase())) {
            printCommands();
            resultOfStringParsing = "Instructions printed";
        } else if ("assist".equalsIgnoreCase(userInput.toLowerCase())) {
            Session.showRelevantCommands(npc);
        }
        else {
            if (userInputFoundInListOfVerbs(userInput, listOfVerbs)) {
                // return the closest word matching an entry in listOfNouns
                resultOfStringParsing = findClosestMatchingNoun(userInput, listOfNouns);
            }
            else {
                resultOfStringParsing = "ERROR - Invalid command. Verb was not found in the list of acceptable verbs.";
            }
        }

        return resultOfStringParsing;
    }

    public String parse(String userInput, List<String> lisfOfNouns) {
        String resultOfStringParsing = null;

        if ("quit".equalsIgnoreCase(userInput.toLowerCase())) {
            System.exit(0);
        } else if ("help".equalsIgnoreCase(userInput.toLowerCase())) {
            printCommands();
            resultOfStringParsing = "Instructions printed";
        } else {
            resultOfStringParsing = findClosestMatchingNoun(userInput, lisfOfNouns);
        }

        return resultOfStringParsing;
    }

    public static TextParser getInstance() {
        if (textParser == null) {
            textParser = new TextParser();
        }

        return textParser;
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

    private void printCommands() {
        System.out.println();
        System.out.println(ANSI_RED + "~~~~~~~~~ INSTRUCTIONS ~~~~~~~~~ " + ANSI_RESET);
        System.out.println("To provide instructions to the game you must provide an acceptable verb and noun. \n" +
                "Characters only understand a limited amount of verbs. These must be inserted into the same entry. \n" +
                "When you are specifying a noun, you do not have to spell it correctly. The game will do its best \n" +
                "to interpret what you are asking for. \n" +
                "Here is an example of asking a waiter for a burrito: \n" +
                "When you enter 'I want to eat a buriito', the system captures the acceptable verb 'eat' and \n" +
                "returns 'burrito' as it is the closest matching noun in your sentence. Notice the system understood \n" +
                "the interpreted 'buriito' as burrito. \n" +
                "\n" + "Entering 'quit' will abruptly end the game. Your progress will not be saved"
        );
        System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + ANSI_RESET);
        System.out.println();
        Session.showRelevantCommands(npc);
    }

    public void setNPC(Map<String, NPC> npc) {
        this.npc = npc;
    }
}
