package com.treasuresconquests.app;

import com.google.gson.Gson;
import com.treasuresconquests.engine.*;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

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

    public Vector<String> loadFoodItems(
            String restaurantName, String country
    ){
        List<WorldMap.Countries.Restaurant> restaurants
                = getRestaurantsForCountry(country);
        // 2. get the restaurant object for the restaurant name given
        WorldMap.Countries.Restaurant restaurant = null;
        Vector<String> itemsList = new Vector<>();
        for (WorldMap.Countries.Restaurant restaurantObj: restaurants
        ) {
            if(restaurantObj.getName().
                    equalsIgnoreCase(restaurantName)){
                restaurant = restaurantObj;
                break;
            }
        }
        if(restaurant != null){
            for (WorldMap.Countries.Restaurant.Items item: restaurant.getItems()
                 ) {
                itemsList.add(item.getName());
            }
        }
        return itemsList;
    }

    public static void main(String[] args) {
        GUIController controller = new GUIController();
        List<WorldMap.Countries.Restaurant> rests = controller
                .getRestaurantsForCountry("japan");
        System.out.println(rests);
    }

    public List<WorldMap.Countries.Attraction> getAttractionsForCountry(String countryName) {

        WorldMap.Countries country = countries.get(countryName);

        if(country != null){
            return (List<WorldMap.Countries.Attraction>) country.getAttractions();
        }
        return null;

    }
}