package com.treasuresconquests.guiengine.other;

import com.treasuresconquests.engine.WorldMap;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiddlesTreasures that = (RiddlesTreasures) o;
        return riddle.equals(that.riddle) && treasure.equals(that.treasure);
    }


}