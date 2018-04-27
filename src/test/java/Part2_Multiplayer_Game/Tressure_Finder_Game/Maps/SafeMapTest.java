package Part2_Multiplayer_Game.Tressure_Finder_Game.Maps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SafeMapTest {
    private SafeMap map1;
    @Before
    public void setUp(){
        SafeMap.setInstanceNull();
        map1 = SafeMap.getInstance(50);
        map1.generateMap();
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
        assertEquals(Math.round(map1.percentageOfWaterTiles*50*50),numberOfWaterTilesInMap());
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
        assertEquals(((50*50)-1-Math.round(map1.percentageOfWaterTiles*50*50)),numberOfGreenTilesInMap());
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

    @Test
    public void testSameMapInstance(){
        assertEquals(SafeMap.getInstance(50),map1);
    }

}