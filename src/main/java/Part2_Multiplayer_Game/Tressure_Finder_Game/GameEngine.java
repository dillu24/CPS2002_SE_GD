package Part2_Multiplayer_Game.Tressure_Finder_Game;

import Part2_Multiplayer_Game.Exceptions.InvalidMapSizeException;
import Part2_Multiplayer_Game.Exceptions.InvalidNumberOfPlayersException;
import Part2_Multiplayer_Game.Player.TreasureFinderPlayer;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class GameEngine {
    TreasureFinderPlayer []players; //Stores the list of players
    Map map; //Stores the game map
    Position startingPosition[]; //An array of all the starting position of each player , so that whenever a player
                                 //Enters a water tile , he can restart again from that same position.

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
     * This method is used to get the number of players as user input in the range between 2 and 8. This method checks
     * that the input is both in the range and an integer and returns the accepted size.
     * @return
     * The number of players entered by the user
     */

    private int getNumberOfPlayers() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of players you wish to play the game , please enter a number between" +
                "2 and 8");
        boolean validInput = false;
        int numberOfPlayers=0;
        while (!validInput) { //until input is valid
            try {
                numberOfPlayers = sc.nextInt();
                validNumberOfPlayers(numberOfPlayers); //check input is valid
                validInput = true; // if valid break loop
            } catch (InputMismatchException e) { //if input not an integer
                System.out.println("Please enter an integer!");
            } catch (InvalidNumberOfPlayersException e){ //if input not in range
                System.out.println("Please enter an integer between 2 and 8 !");
            }
        }
        return numberOfPlayers;
    }

    /**
     * This method is used to get the map size from the user, where the input is both checked if it is an integer
     * and is valid between 5 and 50 as per assignment specification. Note that the number of players had to be passed
     * as parameter as there is different minimum values of map sizes for each number of players.
     * @param numberOfPlayers
     * Stores the number of players playing the game
     * @return
     *the map size.
     */

    private int getMapSize(int numberOfPlayers){
        Scanner sc = new Scanner(System.in);

        if(numberOfPlayers<=4){ //Each player size has different minimum size of maps
            System.out.println("Please enter the map size , the minimum size is 5 , the maximum number is 50");
        }else{
            System.out.println("Please enter the map size , the minimum size is 8 , the maximum number is 50");
        }

        boolean validInput = false;
        int mapSize=0;
        while (!validInput) { // iterate until user enteres a valid number
            try {
                mapSize = sc.nextInt();
                validMapSize(numberOfPlayers,mapSize); //check if map size is valid
                validInput = true;
            } catch (InputMismatchException e) { //If input is not an integer
                System.out.println("Please enter an integer!");
            } catch (InvalidMapSizeException e){ //If input is not in the specified range
                System.out.println(e.getInvalidNumber()+" is not a valid number");
            }
        }
        return mapSize;
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
     * @param numberOfPlayers
     * Stores the number of players playing the game
     * @param mapSize
     * Stores the size of the map
     */

    void initializeGamePlayers(int numberOfPlayers, int mapSize){
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
            startingPosition[i] = new Position(); //create new player accoridng to his starting position
            startingPosition[i].setX(xStartPos);
            startingPosition[i].setY(yStartPos);
        }
    }

    /**
     * This method initializes all the game elements which are the map and the players array
     */

    private void initializeGame(){
        int numberOfPlayers = getNumberOfPlayers();
        int mapSize = getMapSize(numberOfPlayers);
        map = new Map(mapSize);
        initializeGamePlayers(numberOfPlayers,mapSize);
    }

    /**
     * This method combines all the game logic of the Treasure game
     */

    public void StartGame(){
        initializeGame();
    }
}
