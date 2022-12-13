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
    private List<String> commands;
    private List<String> responses;
    private List<Integer> values;
    private List<NPC> npcList;

    public NPC (){

    }

    public NPC (String npcType, List<String> commands, List<String> responses, List<Integer> values){
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

    public List<String> getCommands() {
        return commands;
    }

    private void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public List<String> getResponses() {
        return responses;
    }

    private void setResponses(List<String> responses) {
        this.responses = responses;
    }

    public List<Integer> getValues() {
        return values;
    }

    private void setValues(List<Integer> values) {
        this.values = values;
    }
}