package Part2_Multiplayer_Game.Tressure_Finder_Game;

import Part2_Multiplayer_Game.Exceptions.InvalidCharacterInputMoveException;
import Part2_Multiplayer_Game.Exceptions.InvalidMapSizeException;
import Part2_Multiplayer_Game.Exceptions.InvalidNumberOfPlayersException;
import Part2_Multiplayer_Game.Player.TreasureFinderPlayer;
import Part2_Multiplayer_Game.HTML_File_Gen.HTML_Gen;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class GameEngine {


    TreasureFinderPlayer []players; //Stores the list of players
    Map map; //Stores the game map
    Position startingPosition[]; //An array of all the starting position of each player , so that whenever a player
    //Enters a water tile , he can restart again from that same position.
    int mapSize; //Stores the size of the map
    int numberOfPlayers; //stores the number of players playing the game
    int turnNo; //stores the turn number
    boolean treasureFound = false; //The game will end when the treasure is found.
    boolean playerLivingStatus[]; //Will be used to track if players are alive or dead.
    HTML_Gen htmlGenerator;


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
        playerLivingStatus = new boolean[mapSize]; //initialize li playerLiving Status
        htmlGenerator = new HTML_Gen();
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

    protected boolean validMapSize(int numberOfPlayers, int mapSize)throws InvalidMapSizeException{
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

    public boolean validStartingPosition(int x, int y){
        return map.getTileType(x, y) == 'G';
    }

    /**
     * This method initializes the players array depending on the number of players and map size the user entered ,
     * this must be done this way since when initializing a new player , his starting position must be given ,
     * thus this must be generated according to the map size , in order to not start from an invalid position
     */

    private void initializeGamePlayers()  {
        Random rand = new Random(System.currentTimeMillis());
        players = new TreasureFinderPlayer[numberOfPlayers]; //initialize the player array
        startingPosition = new Position[numberOfPlayers]; // initialize the starting position array
        for(int i=0;i<numberOfPlayers;i++){
            playerLivingStatus[i] = true;
            int xStartPos = rand.nextInt(mapSize);
            int yStartPos = rand.nextInt(mapSize);
            while(!validStartingPosition(xStartPos,yStartPos)){ //generate  random numbers until the position is a valid
                // starting position
                xStartPos = rand.nextInt(mapSize);
                yStartPos = rand.nextInt(mapSize);
            }
            players[i] = new TreasureFinderPlayer(xStartPos,yStartPos,mapSize);
            startingPosition[i] = new Position(); //create new player according to his starting position
            startingPosition[i].setX(xStartPos);
            startingPosition[i].setY(yStartPos);
        }
    }

    /**
     * This method initializes all the game elements which are the map and the players array
     */

    void initializeGame(){
        turnNo = 0;
        map = new Map(mapSize);
        initializeGamePlayers();
    }

    /**
     * This method checks that the input supplied by the user for moving is valid
     * @param input
     * Stores the input character by the user
     * @param playerID
     * Stores the player index in the players list of the player that is trying to move
     * @return
     * True if move character is valid
     * @throws InvalidCharacterInputMoveException
     * If move character is not a character or the player is trying to move outside the map
     */

    boolean validateMove(char input,int playerID) throws InvalidCharacterInputMoveException{
        switch(input){
            case 'U': //if up check if the player position is in top row
                if(players[playerID].getPosition().getX() != 0){
                    return true;
                }else{
                    throw new InvalidCharacterInputMoveException(input);
                }
            case 'D': //if down check if player position is in bottom row
                if(players[playerID].getPosition().getX() != mapSize-1){
                    return true;
                }else{
                    throw new InvalidCharacterInputMoveException(input);
                }
            case 'L': // if left check if player position is in first column
                if(players[playerID].getPosition().getY() != 0){
                    return true;
                }else{
                    throw new InvalidCharacterInputMoveException(input);
                }
            case 'R': // if right check if player position is in last column
                if(players[playerID].getPosition().getY() != mapSize-1){
                    return true;
                }else{
                    throw new InvalidCharacterInputMoveException(input);
                }
            default :
                throw new InvalidCharacterInputMoveException(input);
        }
    }

    /**
     * This method gets the user input , this method could not be tested because user input cannot be simulated. It also
     * checks that the input by the user is a character and that the user does not go outside the map
     * @param playerID
     * The index of the player that is trying to move in the players list
     * @return
     * The character entered by the user if valid.
     */

    private char getMoveFromUser(int playerID){
        Scanner sc = new Scanner(System.in);
        System.out.println("Player "+(playerID+1)+
                " Please enter 'U' to move up , 'D' to move down , 'L' to move left or 'R' to move right.");
        boolean validInput = false; //used to check if the player input is valid
        char moveInput = 'F'; //stores the player input character
        while (!validInput) { //until input is valid
            try {
                moveInput = sc.next().charAt(0); // get first character in the user input
                validateMove(moveInput,playerID); // check if valid
                validInput = true; // if valid break loop
            } catch (InputMismatchException e) { //if input not an integer
                System.out.println("Please enter a character!");
                sc.next();
            } catch (InvalidCharacterInputMoveException e){
                if(e.getInvalidCharacter() != 'U' && e.getInvalidCharacter() != 'D' && e.getInvalidCharacter() != 'L'
                        && e.getInvalidCharacter() !='R'){
                    System.out.println("You entered an invalid move character"); // if user enters an invalid move character
                }else{ //if user tries to access outside the map
                    System.out.println("You are moving outside your map !");
                }
            }
        }
        return moveInput;
    }

    void playersEvents(int playerNo){
        if (map.getTileType(players[playerNo].getPosition().getX(), players[playerNo].getPosition().getY()) == 'W') {
            playerLivingStatus[playerNo] = false;
        } else if (map.getTileType(players[playerNo].getPosition().getX(), players[playerNo].getPosition().getY()) == 'T') {
            treasureFound = true;
        }
    }

    private void generateFiles(){
        File playerFiles[] = new File[numberOfPlayers];
        for(int i=0;i<numberOfPlayers;i++){
            File playerFile = null;
            try {
                playerFile = htmlGenerator.generatePlayerFile(players,i,turnNo,mapSize,map,players[i].isVisited);
            } catch (IOException e) {
                e.printStackTrace();
            }
            playerFiles[i] = playerFile;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            try {
                htmlGenerator.displayFile(playerFiles[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method combines all the game logic of the Treasure game
     */

    public void StartGame(){
        initializeGame();
        while(!treasureFound){
            turnNo++;
            generateFiles();
            for(int i=0;i<numberOfPlayers;i++){
                if(!playerLivingStatus[i]) {
                    players[i].setPosition(startingPosition[i].getX(),startingPosition[i].getY());
                    playerLivingStatus[i]=true;
                }
                players[i].move(getMoveFromUser(i)); //move every user by one position.
                playersEvents(i);
            }
        }
        generateFiles();
        for(int i=0;i<numberOfPlayers;i++){
            if (map.getTileType(players[i].getPosition().getX(), players[i].getPosition().getY()) == 'T') {
                System.out.println("Player #" + (i + 1) + " has won the game.");
            }
        }
    }
}
