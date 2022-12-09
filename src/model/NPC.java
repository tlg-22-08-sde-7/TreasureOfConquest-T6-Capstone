package model;

import com.google.gson.Gson;
import test.TestGSON;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class NPC {
    //TODO: Setup structure for allies and villains in npcWordBank

    private String npcType;
    private String[] commands;
    private String[] responses;
    private int[] values;
    private List<NPC> npcList;

    public NPC (){

    }

    public NPC (String npcType, String[] commands, String[] responses, int[] values){
        this.npcType = npcType;
        this.commands = commands;
        this.responses = responses;
        this.values = values;
    }

    private void populateNPCList(){
        Gson gson = new Gson();
        // worldMap = gson.fromJson(new InputStreamReader(TestGSON.getFileFromResourceAsStream("assets/json-files/worldMap.json")), WorldMap.class);
        //Reader reader = Files.newBufferedReader(("assets/json-files/npcWordBank.json"));
        npcList = Arrays.asList(gson.fromJson(new InputStreamReader(TestGSON.getFileFromResourceAsStream("assets/json-files/npcWordBank.json")), NPC[].class));
        //reader.close();
    }

    public List<NPC> getNpcList() {
        populateNPCList();
        return npcList;
    }

    public String getNpcType() {
        return npcType;
    }

    private void setNpcType(String npcType) {
        this.npcType = npcType;
    }

    public String[] getCommands() {
        return commands;
    }

    private void setCommands(String[] commands) {
        this.commands = commands;
    }

    public String[] getResponses() {
        return responses;
    }

    private void setResponses(String[] responses) {
        this.responses = responses;
    }

    public int[] getValues() {
        return values;
    }

    private void setValues(int[] values) {
        this.values = values;
    }
}