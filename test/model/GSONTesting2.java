package model;

import com.google.gson.Gson;

import java.io.*;

public class GSONTesting2 {

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        WorldMap worldMap = gson.fromJson(new FileReader("assets/commands/worldMap.json"), WorldMap.class);

        // Country instantiation
        WorldMap.Mexico mexico = worldMap.getMexico();
        WorldMap.Japan japan = worldMap.getJapan();

        // Mexico Testing
        // meta functionality
        WorldMap.Mexico.Meta meta = mexico.getMeta();
        WorldMap.Mexico.Meta.FunFacts funFacts = meta.getFunFacts();
        WorldMap.Mexico.Meta.FunFacts.FunFact1 funFact1 = funFacts.getFunFact1();

        System.out.println(funFact1.getText());
        System.out.println(funFact1.getAnswer());

        // attraction1
        WorldMap.Mexico.Attraction1 attraction1 = mexico.getAttraction1();
        WorldMap.Mexico.Attraction1.Challenges challenges = attraction1.getChallenges();
        WorldMap.Mexico.Attraction1.Challenges.Challenge1 challenge1 = challenges.getChallenge1();

        for (String option : challenge1.getOptions()) {
            System.out.println(option.equals(challenge1.getAnswer()));
        }

        // Japan Testing
        // meta functionality
        WorldMap.Japan.Meta japanMeta = japan.getMeta();
        WorldMap.Japan.Meta.FunFacts japanFunFacts = japanMeta.getFunFacts();
        WorldMap.Japan.Meta.FunFacts.FunFact1 japanFunFact1 = japanFunFacts.getFunFact1();

        System.out.println(japanFunFact1.getText());
        System.out.println(japanFunFact1.getAnswer());

        // attraction1
        WorldMap.Japan.Attraction1 japanAttraction1 = japan.getAttraction1();
        String japanAttraction1Name = japanAttraction1.getName();

        System.out.println(japanAttraction1Name);

        // npcWordBank
        NPCWordBank npcWordBank = gson.fromJson(new FileReader("assets/commands/npcWordBank.json"), NPCWordBank.class);
        String[] wordBank = npcWordBank.getWaiter();

        for (String word : wordBank) {
            System.out.println(word);
        }
    }
}