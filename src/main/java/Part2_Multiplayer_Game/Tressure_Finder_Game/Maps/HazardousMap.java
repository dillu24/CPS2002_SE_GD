package Part2_Multiplayer_Game.Tressure_Finder_Game.Maps;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import java.util.Random;

/**
 * This class is used to encode a Hazardous map , i.e having between 25-30 percent of water tiles
 */

public class HazardousMap extends Map {
    float percentageOfWaterTiles; //stores the percetnage of water tiles to be used in tests
    HazardousMap(){
        size = 50;
        type = "Hazardous";
        MatrixOfTiles = new char[50][50];
        generateMap();
    }
    public HazardousMap(int size){
        setMapSize(size);
        type = "Hazardous";
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
        percentageOfWaterTiles = (((float) (Math.random()*10)+25))/100;
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
