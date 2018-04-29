package Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators;

import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This class is used to test the methods found in the class HazardousMapCreator
 */
public class HazardousMapCreatorTest {

    //Both variables are being used for the tests
    private int mapSize1;
    private HazardousMapCreator creator1;
    @Before
    public void setUp() {
        mapSize1 = 5;
        creator1 = new HazardousMapCreator();
    }

    @After
    public void tearDown() {
        mapSize1 = 0;
        creator1= null;
    }

    /**
     * This test is used to check if when creating a map from this class,
     * it is actually creating a map with the type of 'Hazardous' as well checking
     * it has the inputted map size to ensure it was inserted in the right constructor
     */
    @Test
    public void testCreateHazardousMap() {
        Map hM = creator1.createMap("Hazardous",mapSize1);
        int size =  hM.getMapSize();
        String type =  hM.getMapType();
        assertEquals(5,size);
        assertEquals("Hazardous",type);
    }
}