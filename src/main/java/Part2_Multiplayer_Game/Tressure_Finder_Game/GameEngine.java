package Part2_Multiplayer_Game.Tressure_Finder_Game;

import Part2_Multiplayer_Game.Exceptions.InvalidCharacterInputMoveException;
import Part2_Multiplayer_Game.Exceptions.InvalidMapSizeException;
import Part2_Multiplayer_Game.Exceptions.InvalidMapTypeException;
import Part2_Multiplayer_Game.Exceptions.InvalidNumberOfPlayersException;
import Part2_Multiplayer_Game.Exceptions.InvalidNumberOfTeamsException;
import Part2_Multiplayer_Game.Tressure_Finder_Game.ObSubjPattern.TeamSubject;
import Part2_Multiplayer_Game.Tressure_Finder_Game.ObSubjPattern.TreasureFinderPlayer;
import Part2_Multiplayer_Game.HTML_File_Gen.HTML_Gen;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This is the Game Engine class and it encodes the game's logic , valid moves and rules of the game as specified by
 * the assignment specification sheet.
 */


public class GameEngine {


    TreasureFinderPlayer[] players; //Stores the list of players
    Map map; //Stores the game map
    private Position startingPosition[]; //An array of all the starting position of each player , so that whenever a player
    //Enters a water tile , he can restart again from that same position.
    int mapSize; //Stores the size of the map
    int numberOfPlayers; //stores the number of players playing the game
    private int turnNo; //stores the turn number
    private int teamNo; //stores the team number
    boolean treasureFound = false; //The game will end when the treasure is found.
    boolean playerLivingStatus[]; //Will be used to track if players are alive or dead.
    private HTML_Gen htmlGenerator; //An object used to generate the HTML Files
    private String mapType; //stores whether the map is safe or hazardous
    private ArrayList<TeamSubject> teamList;
    private int numberOfTeams;
    private boolean teamsChosen[];
    private int playerTeam[];
    private String gameMode;

    GameEngine() {
    } //default constructor used for testing the GameEngine class

    /**
     * This constructor is used to initialize the game fields according to the assignment specification.Thus the
     * game engine class is initialized correctly only when the passed mapSize and numberOfPlayers values are
     * in the specified ranges. Other wise the game instance is not created.
     *
     * @param mapSize         Stores the map size
     * @param numberOfPlayers stores the number of players playing the game
     * @param numberOfTeams   Stores the number of teams to play against each other
     * @throws InvalidNumberOfPlayersException Whenever the number of players passed as parameters is not as specified
     * @throws InvalidMapSizeException         Whenever the number of players passed as parameters is not as specified
     * @throws InvalidNumberOfTeamsException   Whenever the number of teams is not valid
     */

    public GameEngine(int mapSize, int numberOfPlayers, String mapType, int numberOfTeams) throws
            InvalidNumberOfPlayersException, InvalidMapSizeException, InvalidMapTypeException, InvalidNumberOfTeamsException {
        validNumberOfPlayers(numberOfPlayers); //validate number of players
        this.numberOfPlayers = numberOfPlayers; //store it if correct
        validMapSize(numberOfPlayers, mapSize); //validate map size
        this.mapSize = mapSize; //store it if correct
        validMapType(mapType);
        this.mapType = mapType;
        validNumberOfTeams(numberOfTeams);
        this.numberOfTeams = numberOfTeams;
        playerLivingStatus = new boolean[mapSize]; //initialize li playerLiving Status
        teamsChosen = new boolean[numberOfTeams];
        htmlGenerator = new HTML_Gen(); //initializes the html object
        teamList = new ArrayList<TeamSubject>();
        playerTeam = new int[numberOfPlayers];
        gameMode = "Collaborative";
    }

    /**
     * This constructor is used to initialize the game fields according to the assignment specification.Thus the
     * game engine class is initialized correctly only when the passed mapSize and numberOfPlayers values are
     * in the specified ranges. Other wise the game instance is not created.
     *
     * @param mapSize         Stores the map size
     * @param numberOfPlayers stores the number of players playing the game
     * @throws InvalidNumberOfPlayersException Whenever the number of players passed as parameters is not as specified
     * @throws InvalidMapSizeException         Whenever the number of players passed as parameters is not as specified
     */

    public GameEngine(int mapSize, int numberOfPlayers, String mapType) throws
            InvalidNumberOfPlayersException, InvalidMapSizeException, InvalidMapTypeException {
        validNumberOfPlayers(numberOfPlayers); //validate number of players
        this.numberOfPlayers = numberOfPlayers; //store it if correct
        validMapSize(numberOfPlayers, mapSize); //validate map size
        this.mapSize = mapSize; //store it if correct
        validMapType(mapType);
        this.mapType = mapType;
        playerLivingStatus = new boolean[mapSize]; //initialize li playerLiving Status
        htmlGenerator = new HTML_Gen(); //initializes the html object
        teamList = new ArrayList<TeamSubject>();
        gameMode = "Single";
    }

    /**
     * This method is used to check that the number of teams is valid
     *
     * @param numberOfTeams Stores the value to be checked
     * @return true if correct , exception if incorrect
     */
    boolean validNumberOfTeams(int numberOfTeams) throws InvalidNumberOfTeamsException {
        if (numberOfTeams >= 2 && numberOfTeams < numberOfPlayers) {
            return true;
        } else {
            throw new InvalidNumberOfTeamsException(numberOfTeams);
        }
    }

    /**
     * This method is used to check that the map types entered by the user are correct
     *
     * @param mapType Stores the map type name the user entered
     * @return True if the map type is Hazardous or Safe
     * @throws InvalidMapTypeException If the map Type is not Hazardous or Safe
     */
    boolean validMapType(String mapType) throws InvalidMapTypeException {
        if (mapType.equals("Hazardous") || mapType.equals("Safe")) {
            return true;
        } else {
            throw new InvalidMapTypeException(mapType);
        }
    }

    /**
     * This method is used to validate that the number of players to play the game as per user input is according
     * to the specification given in the assignment sheet
     *
     * @param input Stores the input to be validated
     * @return True if the input is between 2 and 8
     * @throws InvalidNumberOfPlayersException If the input is not between 2 and 8 then the user entered an invalid number has an InvalidNumberOfPlayersException
     *                                         is generated.
     */
    boolean validNumberOfPlayers(int input) throws InvalidNumberOfPlayersException {
        if (input >= 2 && input <= 8) {
            return true;
        } else {
            throw new InvalidNumberOfPlayersException(input);
        }
    }

    /**
     * This method is used to validate that the user input for the map size is correct , it also checks that for
     * different number of players the map size is in the required range
     *
     * @param numberOfPlayers Stores the number of players playing the game
     * @param mapSize         Stores the map size that will be validated
     * @return True if the map size is within the range
     * @throws InvalidMapSizeException If the map size is not within the range.
     */

    boolean validMapSize(int numberOfPlayers, int mapSize) throws InvalidMapSizeException {
        if (numberOfPlayers <= 4) {
            if (mapSize >= 5 && mapSize <= 50) {
                return true;
            } else {
                throw new InvalidMapSizeException(mapSize);
            }
        } else {
            if (mapSize >= 8 && mapSize <= 50) {
                return true;
            } else {
                throw new InvalidMapSizeException(mapSize);
            }
        }
    }

    /**
     * This method is used to verify that the starting position is a valid one
     *
     * @param x Stores the x co-ordinate of the Position to be validated
     * @param y Stores the y co-ordinate of the Position to be validated
     * @return True if valid position(on Grass tile)
     * False otherwise
     */

    boolean validStartingPosition(int x, int y) {
        return map.getTileType(x, y) == 'G';
    }

    /**
     * This method initializes the players array depending on the number of players and map size the user entered ,
     * this must be done this way since when initializing a new player , his starting position must be given ,
     * thus this must be generated according to the map size , in order to not start from an invalid position
     */

    private void initializeGamePlayers() {
        Random rand = new Random(System.currentTimeMillis());
        players = new TreasureFinderPlayer[numberOfPlayers]; //initialize the player array
        startingPosition = new Position[numberOfPlayers]; // initialize the starting position array
        for (int i = 0; i < numberOfPlayers; i++) {
            playerLivingStatus[i] = true; //declare the player as alive and playing
            int xStartPos = rand.nextInt(mapSize);
            int yStartPos = rand.nextInt(mapSize);
            while (!validStartingPosition(xStartPos, yStartPos)) { //generate  random numbers until the position is a valid
                // starting position
                xStartPos = rand.nextInt(mapSize);
                yStartPos = rand.nextInt(mapSize);
            }
            if (numberOfTeams > 0) {
                boolean teamAssigned = false;
                for (int j = 0; j < numberOfTeams; j++) {
                    if (!teamsChosen[j]) {
                        teamNo = j;
                        teamsChosen[j] = true;
                        teamAssigned = true;
                        break;
                    }
                }
                if (!teamAssigned) {
                    teamNo = rand.nextInt(numberOfTeams);
                }
                playerTeam[i] = teamNo;

                players[i] = new TreasureFinderPlayer(teamList.get(teamNo), xStartPos, yStartPos, mapSize);
            } else {
                players[i] = new TreasureFinderPlayer(xStartPos, yStartPos, mapSize);
            }
            startingPosition[i] = new Position(); //create new player according to his starting position
            startingPosition[i].setX(xStartPos);
            startingPosition[i].setY(yStartPos);
        }
    }


    /**
     * This method initializes all the game elements which are the map and the players array
     */

    void initializeGame() {
        for (int i = 0; i < numberOfTeams; i++) {
            teamList.add(new TeamSubject(mapSize));
        }
        turnNo = 0;
        MapCreator creator = new MapCreator();
        map = creator.createMap(mapType, mapSize);
        initializeGamePlayers();
    }

    /**
     * This method checks that the input supplied by the user for moving is valid
     *
     * @param input    Stores the input character by the user
     * @param playerID Stores the player index in the players list of the player that is trying to move
     * @return True if move character is valid
     * @throws InvalidCharacterInputMoveException If move character is not a character or the player is trying to move outside the map
     */

    boolean validateMove(char input, int playerID) throws InvalidCharacterInputMoveException {
        switch (input) {
            case 'U': //if up check if the player position is in top row
                if (players[playerID].getPosition().getX() != 0) {
                    return true;
                } else {
                    throw new InvalidCharacterInputMoveException(input);
                }
            case 'D': //if down check if player position is in bottom row
                if (players[playerID].getPosition().getX() != mapSize - 1) {
                    return true;
                } else {
                    throw new InvalidCharacterInputMoveException(input);
                }
            case 'L': // if left check if player position is in first column
                if (players[playerID].getPosition().getY() != 0) {
                    return true;
                } else {
                    throw new InvalidCharacterInputMoveException(input);
                }
            case 'R': // if right check if player position is in last column
                if (players[playerID].getPosition().getY() != mapSize - 1) {
                    return true;
                } else {
                    throw new InvalidCharacterInputMoveException(input);
                }
            default:
                throw new InvalidCharacterInputMoveException(input);
        }
    }

    /**
     * This method gets the user input , this method could not be tested because user input cannot be simulated. It also
     * checks that the input by the user is a character and that the user does not go outside the map
     *
     * @param playerID The index of the player that is trying to move in the players list
     * @return The character entered by the user if valid.
     */

    private char getMoveFromUser(int playerID) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Player " + (playerID + 1) +
                " Please enter 'U' to move up , 'D' to move down , 'L' to move left or 'R' to move right.");
        boolean validInput = false; //used to check if the player input is valid
        char moveInput = 'F'; //stores the player input character
        while (!validInput) { //until input is valid
            try {
                moveInput = sc.next().charAt(0); // get first character in the user input
                validateMove(moveInput, playerID); // check if valid
                validInput = true; // if valid break loop
            } catch (InputMismatchException e) { //if input not an integer
                System.out.println("Please enter a character!");
                sc.next();
            } catch (InvalidCharacterInputMoveException e) {
                if (e.getInvalidCharacter() != 'U' && e.getInvalidCharacter() != 'D' && e.getInvalidCharacter() != 'L'
                        && e.getInvalidCharacter() != 'R') {
                    System.out.println("You entered an invalid move character"); // if user enters an invalid move character
                } else { //if user tries to access outside the map
                    System.out.println("You are moving outside your map !");
                }
            }
        }
        return moveInput;
    }

    /**
     * This method is used to check the different events a player may experience. These are when a player dies because
     * they entered a water tile OR when a player finds the treasure to alert the game the treasure is found to then
     * stop the game after and announce the winner/s after this round of turns is completed
     *
     * @param playerNo used to store the player number
     */

    void playersEvents(int playerNo) {
        if (map.getTileType(players[playerNo].getPosition().getX(), players[playerNo].getPosition().getY()) == 'W') {
            playerLivingStatus[playerNo] = false; //player dies
        } else if (map.getTileType(players[playerNo].getPosition().getX(), players[playerNo].getPosition().getY()) == 'T') {
            treasureFound = true; //treasure is found
        }
    }


    /**
     * This method is used to create and write the HTML map files of each player. This will be done by calling
     * the generatePlayerFile method save the file string to an array, then wait 1 second and display the file
     * via a browser, and repeat till all files are displayed. The 1 second delay is done due to the browser having
     * to open the files some errors may occur when one has a slow computer
     */
    private void generateFiles() {
        File playerFiles[] = new File[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            File playerFile = null;
            try {
                playerFile = htmlGenerator.generatePlayerFile(players, playerTeam[i], i, turnNo, mapSize, map, players[i].isVisited);
                //call the generatePlayerFile method
            } catch (IOException e) {
                e.printStackTrace();
            }
            playerFiles[i] = playerFile;
            try {
                Thread.sleep(1000); //wait 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            try {
                htmlGenerator.displayFile(playerFiles[i]);//display the file via browser
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method combines all the game logic of the Treasure game.
     * Whilst the treasure has not been found, the turn counter is incremented and the players will
     * have their  map files displayed via browser. Then their turn starts, if they are dead they will
     * respawn to their starting positions. Then they will move 1 tile in the direction they want to move
     * and finally we will check if any special event occurs, eg if they die or fidn the treasure.
     * When the treasure is found the map files will be created, written and displayed one last time
     * and whoever is in the treasure tile is deemed as a winner.
     */

    public void StartGame() {
        initializeGame();
        while (!treasureFound) {
            turnNo++;
            generateFiles();
            for (int i = 0; i < numberOfPlayers; i++) {
                if (!playerLivingStatus[i]) {
                    players[i].setPosition(startingPosition[i].getX(), startingPosition[i].getY());
                    playerLivingStatus[i] = true;
                }
                players[i].move(getMoveFromUser(i)); //move every user by one position.
                playersEvents(i);
            }
        }
        generateFiles();
        for (int i = 0; i < numberOfPlayers; i++) {
            if (map.getTileType(players[i].getPosition().getX(), players[i].getPosition().getY()) == 'T') {
                if (gameMode.equals("Collaborative")) {
                    System.out.println("Team #" + (playerTeam[i] + 1) + " has won the game.");
                    System.out.println("Team #" + (playerTeam[i] + 1) + " consisted of the following players:");
                    for (int j = 0; j < numberOfPlayers; j++) {
                        if (playerTeam[j] == playerTeam[i])
                            System.out.println("\tPlayer #" + (j + 1));
                    }
                } else if (gameMode.equals("Single")) {
                    System.out.println("Player #" + (i + 1) + " has won the game.");
                }
            }
        }
    }
}
