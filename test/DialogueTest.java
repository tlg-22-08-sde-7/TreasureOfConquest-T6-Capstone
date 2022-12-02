package model;

import static org.junit.Assert.*;


import junit.framework.TestCase;
import model.Dialogue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DialogueTest {

    private static final Dialogue DIALOGUE = new Dialogue();
    private static final List<String> VERBS = new ArrayList<>();
    private static final List<String> NOUNS = new ArrayList<>();
    private static String userInput;

    @Test
    public void findMatchingNoun_ShouldReturnMatchingNoun_WhenVerbAndNounFound() {
        String expectedResult;

        VERBS.add("go");
        NOUNS.add("mexico,japan,nigeria,canada");

        VERBS.add("buy");
        NOUNS.add("tamales,gyoza,jollofrice,timhortons");

        DIALOGUE.setCommands(VERBS, NOUNS);

        assertEquals("mexico", DIALOGUE.findMatchingNoun("go", "I want to go to Mexico"));
        assertEquals("japan", DIALOGUE.findMatchingNoun("GO", "go to JAPAN"));
        assertEquals("nigeria", DIALOGUE.findMatchingNoun("gO", "Ay bro I want to go to NiGeRiA"));
        assertEquals("canada", DIALOGUE.findMatchingNoun("Go", "Canada go"));

        assertEquals("tamales", DIALOGUE.findMatchingNoun("buy", "i want to buy a lot of tamales"));
        assertEquals("gyoza", DIALOGUE.findMatchingNoun("bUY", "buy gyoza"));
        assertEquals("jollofrice", DIALOGUE.findMatchingNoun("BuY", "jollofrice is good i want to buy it"));
        assertEquals("timhortons", DIALOGUE.findMatchingNoun("BUY", "timhortons has good coffee. i want to buy some"));
    };
}