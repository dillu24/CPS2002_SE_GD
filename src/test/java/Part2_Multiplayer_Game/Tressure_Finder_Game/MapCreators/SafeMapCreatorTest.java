package Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators;

import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Maps.SafeMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class is used to test the methods found in the class SafeMapCreator
 */
public class SafeMapCreatorTest {

    //Both variables are being used for the tests
    private int mapSize1;
    private SafeMapCreator creator1;

    @Before
    public void setUp() {
        mapSize1 = 5;
        creator1 = new SafeMapCreator();
    }

    @After
    public void tearDown() {
        mapSize1 = 0;
        creator1= null;
    }

    /**
     * This test is used to check if when creating a map from this class,
     * it is actually creating a map with the type of 'Safe' as well checking
     * it has the inputted map size to ensure it was inserted in the right constructor
     */
    @Test
    public void testCreateSafeMap() {
        Map sM = creator1.createMap("Safe",mapSize1);
        int size =  sM.getMapSize();
        String type =  sM.getMapType();
        assertEquals(5,size);
        assertEquals("Safe",type);
    }
}