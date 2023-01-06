package com.treasuresconquests.guiengine.other;

import com.treasuresconquests.engine.WorldMap;

public class RiddlesTreasures {
    private WorldMap.Countries.Attraction.Riddles riddle;
    private WorldMap.Countries.Attraction.Treasures treasure;

    public RiddlesTreasures(WorldMap.Countries.Attraction.Riddles riddle,
                            WorldMap.Countries.Attraction.Treasures treasure){
        this.riddle = riddle;
        this.treasure = treasure;
    }

    public WorldMap.Countries.Attraction.Riddles getRiddle() {
        return riddle;
    }

    public WorldMap.Countries.Attraction.Treasures getTreasure() {
        return treasure;
    }

}