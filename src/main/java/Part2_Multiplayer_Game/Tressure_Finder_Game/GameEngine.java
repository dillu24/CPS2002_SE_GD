package Part2_Multiplayer_Game.Tressure_Finder_Game;

import Part2_Multiplayer_Game.Exceptions.InvalidMapSizeException;
import Part2_Multiplayer_Game.Exceptions.InvalidNumberOfPlayersException;
import Part2_Multiplayer_Game.Player.TreasureFinderPlayer;
import java.util.Random;


public class GameEngine {
    TreasureFinderPlayer []players; //Stores the list of players
    Map map; //Stores the game map
    Position startingPosition[]; //An array of all the starting position of each player , so that whenever a player
                                 //Enters a water tile , he can restart again from that same position.
    int mapSize; //Stores the size of the map
    int numberOfPlayers; //stores the number of players playing the game

    public GameEngine(){} //default constructor used for testing the GameEngine class

    /**
     * This constructor is used to initialize the game fields according to the assignment specification.Thus the
     * game engine class is initialized correctly only when the passed mapSize and numberOfPlayers values are
     * in the specified ranges. Other wise the game instance is not created.
     * @param mapSize
     * Stores the map size
     * @param numberOfPlayers
     * stores the number of players playing the game
     * @throws InvalidNumberOfPlayersException
     * Whenever the number of players passed as parameters is not as specified
     * @throws InvalidMapSizeException
     * Whenever the number of players passed as parameters is not as specified
     */

    public GameEngine(int mapSize , int numberOfPlayers) throws InvalidNumberOfPlayersException,InvalidMapSizeException{
        validNumberOfPlayers(numberOfPlayers); //validate number of players
        this.numberOfPlayers = numberOfPlayers; //store it if correct
        validMapSize(numberOfPlayers,mapSize); //validate map size
        this.mapSize = mapSize; //store it if correct
    }

    /**
     * This method is used to validate that the number of players to play the game as per user input is according
     * to the specification given in the assignment sheet
     * @param input
     * Stores the input to be validated
     * @return
     * True if the input is between 2 and 8
     * @throws InvalidNumberOfPlayersException
     * If the input is not between 2 and 8 then the user entered an invalid number has an InvalidNumberOfPlayersException
     * is generated.
     */
    boolean validNumberOfPlayers(int input) throws InvalidNumberOfPlayersException{
        if(input>=2 && input<=8){
            return true;
        }else{
            throw new InvalidNumberOfPlayersException(input);
        }
    }

    /**
     * This method is used to validate that the user input for the map size is correct , it also checks that for
     * different number of players the map size is in the required range
     * @param numberOfPlayers
     * Stores the number of players playing the game
     * @param mapSize
     * Stores the map size that will be validated
     * @return
     * True if the map size is within the range
     * @throws InvalidMapSizeException
     * If the map size is not within the range.
     */

    boolean validMapSize(int numberOfPlayers, int mapSize)throws InvalidMapSizeException{
        if(numberOfPlayers<=4){
            if(mapSize>=5 && mapSize <=50){
                return true;
            }else{
                throw new InvalidMapSizeException(mapSize);
            }
        }else{
            if(mapSize>=8 && mapSize <=50){
                return true;
            }else{
                throw new InvalidMapSizeException(mapSize);
            }
        }
    }

    /**
     * This method is used to verify that the starting position is a valid one
     * @param x
     * Stores the x co-ordinate of the Position to be validated
     * @param y
     * Stores the y co-ordinate of the Position to be validated
     * @return
     * True if valid position(on Grass tile)
     * False otherwise
     */

    boolean validStartingPosition(int x, int y){
        return map.getTileType(x, y) == 'G';
    }

    /**
     * This method initializes the players array depending on the number of players and map size the user entered ,
     * this must be done this way since when initializing a new player , his starting position must be given ,
     * thus this must be generated according to the map size , in order to not start from an invalid position
     */

    private void initializeGamePlayers(){
        Random rand = new Random(System.currentTimeMillis());
        players = new TreasureFinderPlayer[numberOfPlayers]; //initialize the player array
        startingPosition = new Position[numberOfPlayers]; // initialize the starting position array
        for(int i=0;i<numberOfPlayers;i++){
            int xStartPos = rand.nextInt(mapSize);
            int yStartPos = rand.nextInt(mapSize);
            while(!validStartingPosition(xStartPos,yStartPos)){ //generate  random numbers until the position is a valid
                                                                // starting position
                xStartPos = rand.nextInt(mapSize);
                yStartPos = rand.nextInt(mapSize);
            }
            players[i] = new TreasureFinderPlayer(xStartPos,yStartPos);
            startingPosition[i] = new Position(); //create new player according to his starting position
            startingPosition[i].setX(xStartPos);
            startingPosition[i].setY(yStartPos);
        }
    }

    /**
     * This method initializes all the game elements which are the map and the players array
     */

    private void initializeGame(){
        map = new Map(mapSize);
        initializeGamePlayers();
    }

    /**
     * This method combines all the game logic of the Treasure game
     */

    public void StartGame(){
        initializeGame();
    }
}
