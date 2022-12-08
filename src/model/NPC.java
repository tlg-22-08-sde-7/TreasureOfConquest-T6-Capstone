package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPC {
    private String npcType;
    private String [] commands;
    private String [] responds;
    private List<NPC> npcList;

    public NPC (){

    }

    public NPC (String npcType,String [] commands, String [] responds){
        this.npcType = npcType;
        this.commands = commands;
        this.responds = responds;
    }

    public void populateNPCList(){
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Path.of("assets/json-files/npcWordBank.json"));
            npcList = Arrays.asList(gson.fromJson(reader,NPC[].class));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<NPC> getNpcList() {
        return npcList;
    }
}