package model;

import com.google.gson.Gson;
import org.junit.BeforeClass;
import java.io.FileReader;

import static org.junit.Assert.*;

public class WorldMapTest  {

    private static final Gson GSON = new Gson();
    private static WorldMap worldMap;
    private static WorldMap.Mexico mexico;
    private static WorldMap.Japan japan;

    @BeforeClass
    public static void setUp() throws Exception {
         worldMap = GSON.fromJson(new FileReader("assets/commands/worldMap.json"), WorldMap.class);
         mexico = worldMap.getMexico();
         japan = worldMap.getJapan();
    }

    // TODO: COMPLETE JUNIT TESTING.
        // CURRENTLY, NON-JUNIT TESTING CAN BE FOUND IN GSONTesting2
}