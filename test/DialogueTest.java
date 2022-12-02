import static org.junit.Assert.*;
import junit.framework.TestCase;
import model.Dialogue;
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
        String go = "go";
        String buy = "buy";

        VERBS.add(go);
        NOUNS.add("mexico,japan,nigeria,canada");

        VERBS.add(buy);
        NOUNS.add("tamales,gyoza,jollofrice,timhortons");

        DIALOGUE.setCommands(VERBS, NOUNS);

        assertEquals("mexico", DIALOGUE.findMatchingNoun(go, "I want to go to Mexico"));
        assertEquals("japan", DIALOGUE.findMatchingNoun(go, "GO to JAPAN"));
        assertEquals("nigeria", DIALOGUE.findMatchingNoun(go, "Ay bro I want to gO to NiGeRiA"));
        assertEquals("canada", DIALOGUE.findMatchingNoun(go, "Canada Go"));

        assertEquals("tamales", DIALOGUE.findMatchingNoun(buy, "i want to buy a lot of tamales"));
        assertEquals("gyoza", DIALOGUE.findMatchingNoun(buy, "bUY gyoza"));
        assertEquals("jollofrice", DIALOGUE.findMatchingNoun(buy, "jollofrice is good i want to BuY it"));
        assertEquals("timhortons", DIALOGUE.findMatchingNoun(buy, "timhortons has good coffee. i want to BUY some"));
    };

    @Test
    public void findMatchingNoun_ShouldReturn_NounMatchNotFound_WhenInvalidVerb() {
        String go = "go";
        String buy = "buy";
        String expectedResult = "noun match not found";

        VERBS.add(go);
        NOUNS.add("mexico,japan,nigeria,canada");

        VERBS.add(buy);
        NOUNS.add("tamales,gyoza,jollofrice,timhortons");

        DIALOGUE.setCommands(VERBS, NOUNS);

        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(go, "I want to go to Puebla"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(go, "GO to JA PAN"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(go, "Ay bro I want to gO to Africa"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(go, "adanac go"));

        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(buy, "i want to bUy a lot of tamles"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(buy, "buy gyozaaa"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(buy, "jollo frice is good i want to BuY it"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(buy, "timhorto ns has good coffee. i want to BUY some"));
    };

    @Test
    public void findMatchingNoun_ShouldReturn_VerbMatchNotFound_WhenInvalidVerb() {
        String go = "go";
        String buy = "buy";
        String expectedResult = "verb match not found";

        VERBS.add(go);
        NOUNS.add("mexico,japan,nigeria,canada");

        VERBS.add(buy);
        NOUNS.add("tamales,gyoza,jollofrice,timhortons");

        DIALOGUE.setCommands(VERBS, NOUNS);

        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(go, "I want to oo to Mexico"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(go, "GOo to JAPAN"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(go, "Ay bro I want to goO to NiGeRiA"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(go, "i want to buy canada"));

        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(buy, "i want to go a lot of tamales"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(buy, "get gyoza"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(buy, "jollofrice is good i want to BuuY it"));
        assertEquals(expectedResult, DIALOGUE.findMatchingNoun(buy, "timhortons has good coffee. i want to bBUY some"));
    };
};