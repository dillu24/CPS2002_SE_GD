package Part2_Multiplayer_Game.Tressure_Finder_Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class is used to test the Map class , this class was tested by testing the generation of the Map method
 * since this joins all the methods used in this class together . Thus for different map sizes that would result
 * to different number of water tiles , the Map generation was tested . This was done by checking the number of
 * different tiles created by each map .
 */

public class MapTest {
    private Map map1 ; //Stores map 1 of size default i.e 50
    private Map map2 ; //Stores map 2 of size 10
    private Map map3 ; //Stores map 2 of size 15

    /**
     * This method is created in order to create maps of different sizes , since the Map class will be created such that
     * the number of water tiles varies according to the map size. 3 Maps were only created since only 3 variations of
     * number of water tiles will be created.
     */

    @Before
    public void setUp(){
        map1 = new Map();
        map2 = new Map(10);
        map3 = new Map(15);
    }

    /**
     * This test checks that Map 1 is generated with all different tile numbers , since the size of map 1 is set to
     * default 50 , then the number of water tiles will be set to be 7 , thus the array of tiles will be traversed
     * and each tile type will be recorded.
     */
    @Test
    public void testMap1ContainsAllTiles(){
        int numberOfGrassTiles=0;
        int numberOfWaterTiles=0;
        int numberOfTreasureTiles=0;
        for(int i=0;i<50;i++){
            for(int j=0;j<50;j++){
                if(map1.getTileType(i,j)=='G'){
                    numberOfGrassTiles++;
                }else if(map1.getTileType(i,j)=='W'){
                    numberOfWaterTiles++;
                }else{
                    numberOfTreasureTiles++;
                }
            }
        }
        assertEquals(2492,numberOfGrassTiles);
        assertEquals(7,numberOfWaterTiles);
        assertEquals(1,numberOfTreasureTiles);
    }

    /**
     * This test checks that Map 2 is generated with all different tile numbers , since the size of map 2 is set to
     * 10 , then the number of water tiles will be set to be 3 , thus the array of tiles will be traversed
     * and each tile type will be recorded.
     */

    @Test
    public void testMap2ContainsAllTiles(){
        int numberOfGrassTiles=0;
        int numberOfWaterTiles=0;
        int numberOfTreasureTiles=0;
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(map2.getTileType(i,j)=='G'){
                    numberOfGrassTiles++;
                }else if(map2.getTileType(i,j)=='W'){
                    numberOfWaterTiles++;
                }else{
                    numberOfTreasureTiles++;
                }
            }
        }
        assertEquals(96,numberOfGrassTiles);
        assertEquals(3,numberOfWaterTiles);
        assertEquals(1,numberOfTreasureTiles);
    }

    /**
     * This test checks that Map 3 is generated with all different tile numbers , since the size of map 3 is set to
     * 15 , then the number of water tiles will be set to be 5 , thus the array of tiles will be traversed
     * and each tile type will be recorded.
     */

    @Test
    public void testMap3ContainsAllTiles(){
        int numberOfGrassTiles=0;
        int numberOfWaterTiles=0;
        int numberOfTreasureTiles=0;
        for(int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                if(map3.getTileType(i,j)=='G'){
                    numberOfGrassTiles++;
                }else if(map3.getTileType(i,j)=='W'){
                    numberOfWaterTiles++;
                }else{
                    numberOfTreasureTiles++;
                }
            }
        }
        assertEquals(219,numberOfGrassTiles);
        assertEquals(5,numberOfWaterTiles);
        assertEquals(1,numberOfTreasureTiles);
    }

    /**
     * Remove the maps when ready
     */
    @After
    public void tearDown(){
        map1 = null;
        map2 = null;
        map3 = null;
    }

}