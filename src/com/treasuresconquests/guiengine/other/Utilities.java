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

    public static Vector<String> convertListToVector(
            List<String> items) {
        Vector<String> data = new Vector<>();
        for (String str :
                items) {
            data.add(str);
        }
        return data;
    }
}