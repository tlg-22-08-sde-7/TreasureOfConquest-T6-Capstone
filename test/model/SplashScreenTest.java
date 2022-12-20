package model;

import com.treasuresconquests.engine.SplashScreen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SplashScreenTest {
    SplashScreen splashScreen;

    @Before
    public void setUp()  {
        splashScreen = new SplashScreen();
    }

    @Test
    public void testGetSplashScreenArt_shouldReturnSplashScreenStringFromTxtFile() {
        String actualResults = splashScreen.getSplashScreenArt();
        String expectedResults = "Treasure of Conquest";
        Assert.assertEquals(expectedResults, actualResults);
    }
}