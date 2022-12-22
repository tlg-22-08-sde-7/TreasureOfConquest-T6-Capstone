package com.treasuresconquests.engine;

import com.treasuresconquests.app.TreasuresConApp;

import java.util.Map;

public class Session {
    private static String currentNPC = "tourGuide";

    public static void showRelevantCommands(Map<String, NPC> npc) {

        // Added below code to show revelant commands and where you are.
        System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~ LIST OF ACCEPTABLE COMMANDS ~~~~~~" + TreasuresConApp.ANSI_RESET);
        for (int i = 0; i < npc.get(currentNPC).getCommands().size(); i++) {
            System.out.print(npc.get(currentNPC).getCommands().get(i));
            if (i < npc.get(currentNPC).getCommands().size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + TreasuresConApp.ANSI_RESET);
        System.out.println();
    }

    public static String getCurrentNPC() {
        return currentNPC;
    }

    public static void setCurrentNPC(String currentNPC) {
        Session.currentNPC = currentNPC;
    }
}