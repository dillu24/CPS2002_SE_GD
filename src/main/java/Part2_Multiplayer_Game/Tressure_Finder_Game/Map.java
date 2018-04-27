package Part2_Multiplayer_Game.Tressure_Finder_Game;

public abstract class Map{

    protected String type;
    protected char MatrixOfTiles[][];
    protected int size;
    public abstract void generateMap();


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

    protected void setMapSize(int size){
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

    /**
     * This method is used for tests to get the map property of size
     * @return size: the size of the border of a map
     */
    public int getMapSize(){
        return size;
    }

    /**
     * This method is used for tests to get the map property of type
     * @return size: the size of the type of a map
     */
    public String getMapType(){
        return type;
    }

}


