package com.treasuresconquests.app;

import com.google.gson.Gson;
import com.treasuresconquests.engine.*;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIController {
    private WorldMap worldMap;

    private Player player;
    private final Map<String, WorldMap.Countries> countries = new HashMap<>();
    private final Map<String, NPC> npc = new HashMap<>();

    public GUIController() {
        player = new Player();
        player.playerSetupGUI();
        player.setCurrentAttraction("airport");
        try {
            createWorld();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void createWorld() throws FileNotFoundException {
        try {
            fillCountryMap();
            fillNPCMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Countries being initialized here
    private void fillCountryMap() throws FileNotFoundException {
        // deserialize json
        Gson gson = new Gson();
        worldMap = gson.fromJson(new InputStreamReader(TestGSON.getFileFromResourceAsStream("assets/json-files/worldMap.json")), WorldMap.class);

        for (WorldMap.Countries country : worldMap.getCountries()) {
            countries.put(country.getName().toLowerCase(), country);
        }
    }

    private void fillNPCMap() {
        List<NPC> npcList = new NPC().getNpcList();

        for (NPC item : npcList) {
            npc.put(item.getNpcType(), item);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<WorldMap.Countries.Restaurant> getRestaurantsForCountry(
            String countryName){
        WorldMap.Countries country = countries.get(countryName);

        if(country != null){
            return (List<WorldMap.Countries.Restaurant>) country.getRestaurants();
        }
        return null;
    }

}