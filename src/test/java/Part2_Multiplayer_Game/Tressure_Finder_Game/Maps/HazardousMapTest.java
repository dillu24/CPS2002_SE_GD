package Part2_Multiplayer_Game.Tressure_Finder_Game.Maps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dylan Galea on 27/04/2018.
 */
public class HazardousMapTest {
    private HazardousMap map1;
    @Before
    public void setUp(){
        map1 = new HazardousMap();
    }

    @After
    public void tearDown(){
        map1 = null;
    }

    @Test
    public void testIf1TreasureIsPresentInMap(){
        assertEquals(1,numberOfTreasuresInMap());
    }
    private int numberOfTreasuresInMap(){
        int numberOfTreasures = 0;
        for(int i=0;i<50;i++){
            for(int j=0;j<50;j++){
                if(map1.getTileType(i,j)=='T'){
                    numberOfTreasures++;
                }
            }
        }
        return numberOfTreasures;
    }

    @Test
    public void testIfNumberOfWaterTilesIsCorrect(){
        assertEquals(Math.round(map1.percentageOfWaterTiles*50),numberOfWaterTilesInMap());
    }
    private int numberOfWaterTilesInMap(){
        int numberOfWaterTiles = 0;
        for(int i=0;i<50;i++){
            for(int j=0;j<50;j++){
                if(map1.getTileType(i,j)=='W'){
                    numberOfWaterTiles++;
                }
            }
        }
        return numberOfWaterTiles;
    }

    @Test
    public void testIfNumberOfGreenTilesIsCorrect(){
        assertEquals(((50*50)-1-Math.round(map1.percentageOfWaterTiles*50)),numberOfGreenTilesInMap());
    }
    private int numberOfGreenTilesInMap(){
        int numberOfGreenTiles = 0;
        for(int i=0;i<50;i++){
            for(int j=0;j<50;j++){
                if(map1.getTileType(i,j)=='G'){
                    numberOfGreenTiles++;
                }
            }
        }
        return numberOfGreenTiles;
    }
}