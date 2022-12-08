package model;

import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GSONTesting2 {

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        WorldMap worldMap = gson.fromJson(new FileReader("assets/json-files/worldMap.json"), WorldMap.class);

        // Country instantiation
        WorldMap.Mexico mexico = worldMap.getMexico();
        WorldMap.Japan japan = worldMap.getJapan();

        // Mexico Testing
        // meta functionality
        WorldMap.Mexico.Meta meta = mexico.getMeta();
        System.out.println(meta.getName());
        WorldMap.Mexico.Meta.FunFacts funFacts = meta.getFunFacts();
        WorldMap.Mexico.Meta.FunFacts.FunFact1 funFact1 = funFacts.getFunFact1();

        System.out.println(funFact1.getText());
        System.out.println(funFact1.getAnswer());

        // attraction1
        WorldMap.Mexico.Attraction1 attraction1 = mexico.getAttraction1();
        WorldMap.Mexico.Attraction1.Riddles riddles = attraction1.getRiddles();
        WorldMap.Mexico.Attraction1.Riddles.Riddle1 riddle1 = riddles.getRiddle1();

        for (String option : riddle1.getOptions()) {
            System.out.println(option.equals(riddle1.getAnswer()));
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
//        NPCWordBank npcWordBank = gson.fromJson(new FileReader("assets/json-files/worldMap.json"), NPCWordBank.class);
//        String[] wordBank = npcWordBank.getWaiter();

        Map<String, Object> worldMapping = new HashMap<>();

        worldMapping.put("mexico", mexico);
        worldMapping.put("japan", japan);

        WorldMap.CountriesStructure[] list = new WorldMap.CountriesStructure[]{mexico, japan};

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i].getMeta().getName());
        }

    }
}