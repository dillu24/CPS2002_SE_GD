package Part2_Multiplayer_Game.Tressure_Finder_Game.Maps;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators.SafeMapCreator;

import java.util.Random;

/**
 * This class is used to encode the SafeMap class , it generates a map with at most 10 percent water tiles
 */

public class SafeMap extends Map {
    private static SafeMap instance = null; //Create a null instance
    float percentageOfWaterTiles; // stores the percentage of water tiles to be used for checking in unit tests

    /**
     * This method is used for tests to be able to make the instance null again, to then test
     * different scenarios, such as different map sizes, types, having specific tiles at specific
     * positions etc...
     *
     * This is NOT part of the design pattern and is only public accessible for the tests. Tests aside it
     * would not be used.
     */
    public static void setInstanceNull(){
        instance = null;
    }

    public static SafeMap getInstance(int mapSize){
        if (instance == null){//if no instance has yet been created
            instance = new SafeMap(mapSize); //create a new one
        }
        return instance;//show our only immutable instance
    }


    private SafeMap(int size){
        setMapSize(size);
        type = "Safe";
        MatrixOfTiles = new char [size][size];
    }
    public void generateMap(){
        for(int i=0;i<size;i++){ //Initialize each tile to green
            for(int j=0;j<size;j++){
                MatrixOfTiles[i][j] = 'G';
            }
        }
        Random rand = new Random(System.currentTimeMillis());
        int xTreasure;
        int yTreasure;

        do { // make sure treasure is not on the last column or first column in order to not be surrounded by water
            xTreasure = rand.nextInt(size);
        }while(xTreasure==0 || xTreasure == size-1);

        do { //make sure treasure is not on the last or fist column in order to not be surrounded by water
            yTreasure = rand.nextInt(size);
        }while(yTreasure==0 || yTreasure ==size-1);

        MatrixOfTiles[xTreasure][yTreasure] = 'T'; // Determine the position of the treasure.
        percentageOfWaterTiles = ((float) (Math.random()*10)/100); //determine random percentange
        int numberOfWaterTiles = Math.round(percentageOfWaterTiles*size*size);
        for(int i=0;i<numberOfWaterTiles;i++){ //Determine the water tiles position
            int xWater = rand.nextInt(size);
            int yWater = rand.nextInt(size);
            while (MatrixOfTiles[xWater][yWater] != 'G'){ //check that the position is not occupied by another water
                                                          //tile or the treasure.
                xWater = rand.nextInt(size);
                yWater = rand.nextInt(size);
            }
            MatrixOfTiles[xWater][yWater] = 'W';
        }
    }

}
