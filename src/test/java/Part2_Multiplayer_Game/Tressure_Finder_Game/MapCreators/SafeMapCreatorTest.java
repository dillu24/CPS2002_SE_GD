package Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators;

import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SafeMapCreatorTest {

    private int mapSize1;
    private SafeMapCreator creator1;

    @Before
    public void setUp() {
        mapSize1 = 10;
        creator1 = new SafeMapCreator();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateSafeMap() {
        Map sM = creator1.createMap("Safe",mapSize1);
        int size =  sM.getMapSize();
        String type =  sM.getMapType();
        assertEquals(10,size);
        assertEquals("Safe",type);
    }
}