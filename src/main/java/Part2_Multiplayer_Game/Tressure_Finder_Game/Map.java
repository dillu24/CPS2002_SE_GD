package Part2_Multiplayer_Game.Tressure_Finder_Game;


import java.util.Random;

/**
 * The Map class encodes the map for the Treasure Finder Game , where each map contains a size. Note that the integer
 * value given to the size variable should be interpreted as having a map of size : size.size .This class also contains
 * a matrix of tiles where 'G' means that we have a grass tile , 'W' means that we have a water tile and 'T' means we
 * have a treasure tile . In this class also whenever a new Map is created , it is given a size and it is instantly
 * randomly generated. Note that when giving a size it is assumed that the game engine has verified that correct sizes
 * are inputted.
 */
public class Map {
    private int size; //Stores the size of the Map
    private char MatrixOfTiles[][]; // Stores a character that represents a tile .. 'G' = Grass , 'W' = water , 'T' = Treasure

    /**
     * This constructor is used whenever a default map is to be initialized with default size . The default value was
     * set to be 50 as to take the maximum worst case as per specification.After the size has been set then the matrix
     * of tiles is declared and the map is generated accordingly.
     */
    public Map(){
        size = 50;
        MatrixOfTiles = new char[50][50];
        generateMap();
    }

    /**
     * This constructor is used to create a map given the size the player has entered in the console. Note that no
     * input validation is done here as this job is left to the game engine. Again after the size has been set the
     * matrix of tiles is declared and the map is randomly generated.
     * @param size
     * Stores the size value of the newly created map to be given to this.map
     */
    public Map(int size){
        setMapSize(size);
        MatrixOfTiles = new char[size][size];
        generateMap();
    }

    /**
     * This method randomly generates the map of the treasure , i.e randomly generates the tiles . First this method
     * initializes all tiles to grass so that when adding water tiles it could be identified that a water tile is not
     * replacing a treasure tile. Then a treasure tile is randomly generated in the map . After wards the number of
     * water tiles are determined according to the size of the map in order not to offload the map with a lot of water
     * tiles since the game would never stop. Then water tiles are randomly generated and replace grass tiles only
     * so that the treasure tile is not modified. Note that the number of water tiles were chosen this way in order
     * to make sure that the treasure is never unaccessable due to the surrounding of water tiles . The same can be said
     * to the position of the player. Also in order to avoid having a lot of restarts in the game engine the number of
     * water tiles was chosen to be small.
     */

    private void generateMap(){
        for(int i=0;i<size;i++){ //Initialize each tile to green
            for(int j=0;j<size;j++){
                MatrixOfTiles[i][j] = 'G';
            }
        }
        Random rand = new Random(System.currentTimeMillis());
        int xTreasure = rand.nextInt(size);
        int yTreasure = rand.nextInt(size);
        MatrixOfTiles[xTreasure][yTreasure] = 'T'; // Determine the position of the treasure.

        int numberOfWaterTiles;
        if(size <= 10){ // Set the numberOfWater tiles according to size
            numberOfWaterTiles = 3;
        }else if(size>10 && size <=20){
            numberOfWaterTiles = 5;
        }else{
            numberOfWaterTiles = 7;
        }
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

    /**
     * This getter is used to get the tile type in the co-ordinate position x,y
     * @param x
     * Stores the x co-ordinate of the tile type to be outputted
     * @param y
     * Stores the y co-ordinate of the tile type to be outputted
     * @return
     * The tile type at the position (x,y) which can be 'G','W','T'
     */

    public char getTileType(int x , int y){
        return MatrixOfTiles[x][y];
    }

    /**
     * This method is used to set the size of the map to a value , note that this method does not check that the input
     * given by the user is correct since this is done in the game-engine.
     * @param size
     * Stores the size value to be stored in this.size.
     */

    private void setMapSize(int size){
        this.size = size;
    }

    /**
     * This method is used to set the map's tile values to the different possible existing tile types. This will mainly
     * be used for tests.
     * @param tileMap
     * Stores a pre set 2d char array of the tiles to be set for the map.
     */
    public void setMap(char tileMap[][]){
        if(tileMap.length == MatrixOfTiles.length && tileMap[0].length == MatrixOfTiles[0].length) {
            MatrixOfTiles = tileMap;
        }
    }
}
