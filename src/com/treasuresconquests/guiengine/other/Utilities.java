package com.treasuresconquests.guiengine.other;

import com.treasuresconquests.engine.WorldMap;

import java.util.List;
import java.util.Vector;

public class Utilities {

    public static Vector<String> convertTreasuresToVector(
            List<WorldMap.Countries.Attraction.Treasures> treasures) {
        Vector<String> data = new Vector<>();
        for (WorldMap.Countries.Attraction.Treasures treasure :
             treasures) {
            data.add(treasure.getName());
        }
        return data;
    }
}