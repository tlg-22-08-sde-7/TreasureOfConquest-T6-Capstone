//package model;
//
//import com.google.gson.Gson;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.*;
//
//public class Commands {
//
//    private static Map<String, Object> commandsMap = new HashMap<>();
//
//    public Commands() {
//        try {
//            setCommands();
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    };
//
//    public String findMatchingNoun(String inputCategory, String userInput) {
//        /*
//         * findMatchingNoun parses userInput for the expected command and a noun thats paired
//         * to inputCategory in the commandsMap Hashmap.
//         * If both are found then it returns the noun, else it identifies the input error in userInput
//         * and returns it.
//         * CURRENTLY, DOES NOT WORK FOR NOUNS THAT CONSIST OF >1 WORDS
//         */
//        Object object = commandsMap.get(inputCategory);
//
//        inputCategory = inputCategory.toLowerCase();
//
//        String result = null;
//        Set<String> verbs = new HashSet<>(object.get(inputCategory));
//        Set<String> nouns = new HashSet<>(commandsMap.get(inputCategory));
//        List<String> userTextSplit = List.of(userInput.split("\\s")); // splits based on whitespace
//
//        String matchingVerb = null;
//        String matchingNoun = null;
//
//        for (String word : userTextSplit) {
//            word = word.toLowerCase();
//
//            if (verbs.contains(word)) {
//                matchingVerb = word;
//            } else if (nouns.contains(word)) {
//                matchingNoun = word;
//            };
//        };
//
//        if (matchingVerb == null) {
//            result = "ERROR: verb match not found.";
//        }
//        else if (matchingNoun == null) {
//            result = "ERROR: noun match not found.";
//        }
//        else {
//            result = matchingNoun;
//        };
//
//        return result;
//    };
//
//    private Map<String, Set<String>> getCommands() {
//        return commandsMap;
//    };
//
//    public void setCommands() throws FileNotFoundException {
//        Gson gson = new Gson();
//
//        commandsMap = gson.fromJson(new FileReader("assets/commands/npcWordBank.json"), HashMap.class);
//    };
//};
