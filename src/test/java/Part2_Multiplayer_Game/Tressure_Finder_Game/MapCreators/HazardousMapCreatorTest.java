package Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators;

import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HazardousMapCreatorTest {

    private int mapSize1;
    private HazardousMapCreator creator1;
    @Before
    public void setUp() {
        mapSize1 = 20;
        creator1 = new HazardousMapCreator();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateHazardousMap() {
        Map hM = creator1.createMap("Hazardous",mapSize1);
        int size =  hM.getMapSize();
        String type =  hM.getMapType();
        assertEquals(20,size);
        assertEquals("Hazardous",type);
    }
}