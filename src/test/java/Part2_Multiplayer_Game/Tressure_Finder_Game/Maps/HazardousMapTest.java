package Part2_Multiplayer_Game.Tressure_Finder_Game.Maps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class is used to test the methods found in the class HazardousMap
 */
public class HazardousMapTest {
    private HazardousMap map1;
    @Before
    public void setUp(){
        map1 = HazardousMap.getInstance(5);
        map1.generateMap();
    }

    @After
    public void tearDown(){
        map1 = null;
    }

    @Test
    /**
     * This test is used to ensure that a generated map contains only 1 treasure
     */
    public void testIf1TreasureIsPresentInMap(){
        assertEquals(1,numberOfTreasuresInMap());
    }
    private int numberOfTreasuresInMap(){
        int numberOfTreasures = 0;
        for(int i=0;i<5;i++){ //for loop going through all the tiles in map
            for(int j=0;j<5;j++){
                if(map1.getTileType(i,j)=='T'){ //checking their type to see if they are a treasure
                    numberOfTreasures++; //if they are, count it
                }
            }
        }
        return numberOfTreasures; //give back the total count
    }

    /**
     * This test is used to check if there are a correct amount of water tiles, according to the generated percentage
     */
    @Test
    public void testIfNumberOfWaterTilesIsCorrect(){
        assertEquals(Math.round(map1.percentageOfWaterTiles*5*5),numberOfWaterTilesInMap());
    }
    private int numberOfWaterTilesInMap(){
        int numberOfWaterTiles = 0;
        for(int i=0;i<5;i++){ //loops through all the tiles in the map
            for(int j=0;j<5;j++){
                if(map1.getTileType(i,j)=='W'){ //checks which are water tile
                    numberOfWaterTiles++; //counts the water
                }
            }
        }
        return numberOfWaterTiles; //returns the count
    }

    /**
     * This test is used to check if there are a correct amount of grass tiles, according to the generated percentage
     * This is very similar to the above test.
     */
    @Test
    public void testIfNumberOfGreenTilesIsCorrect(){
        assertEquals(((5*5)-1-Math.round(map1.percentageOfWaterTiles*5*5)),numberOfGreenTilesInMap());
        //Total number of tiles minus 1 treasure tile minus the water tiles
    }
    private int numberOfGreenTilesInMap(){
        int numberOfGreenTiles = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(map1.getTileType(i,j)=='G'){//checks on grass tiles
                    numberOfGreenTiles++;
                }
            }
        }
        return numberOfGreenTiles;
    }

    @Test
    public void testSameMapInstance(){
        assertEquals(HazardousMap.getInstance(5),map1);
    }
}