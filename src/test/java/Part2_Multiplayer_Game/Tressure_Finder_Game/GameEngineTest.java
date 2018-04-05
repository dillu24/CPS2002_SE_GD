package Part2_Multiplayer_Game.Tressure_Finder_Game;

import Part2_Multiplayer_Game.Exceptions.InvalidCharacterInputMoveException;
import Part2_Multiplayer_Game.Exceptions.InvalidMapSizeException;
import Part2_Multiplayer_Game.Exceptions.InvalidNumberOfPlayersException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * This class is used to test the game Engine class , note that only methods that could be tested by junit without
 * user live input could be tested , that's why the code coverage may be lower than previous tests
 */

public class GameEngineTest {
    private GameEngine treasureGame ; //stores the treasure game to be tested
    private GameEngine treasureGame2; //stores one of the treasure games to be tested
    private GameEngine treasureGame3; //stores one of the treasure games to be tested

    /**
     * A rule done in order to test for exceptions
     */
    @Rule
    public ExpectedException exceptionExcepted = ExpectedException.none();

    /**
     * This method is used to check incorrect initialization
     */
    @Before
    public void setUp() throws InvalidMapSizeException,InvalidNumberOfPlayersException{
        treasureGame = new GameEngine();
        treasureGame.mapSize =50;
        treasureGame2 = new GameEngine(50,3);
        treasureGame3 = new GameEngine(5, 2);
    }

    /**
     * This method tests whether the validation of the player input is per assignment specification
     * @throws InvalidNumberOfPlayersException
     * When the test fails this throws an InvalidNumberOfPlayersException since the inputted number would be out
     * of range
     */
    @Test
    public void testValidNumberOfPlayersInput() throws InvalidNumberOfPlayersException{
        assertEquals(true,treasureGame.validNumberOfPlayers(2));
        assertEquals(true,treasureGame.validNumberOfPlayers(8));
        assertEquals(true,treasureGame.validNumberOfPlayers(5));
    }

    /**
     * This method tests whether an exception is generated whenever a bad input for the number of players is inserted
     * @throws InvalidNumberOfPlayersException
     * Whenever a bad input is inserted , in this test this gives us a good test result.
     */
    @Test
    public void testInvalidNumberOfPlayersInput() throws InvalidNumberOfPlayersException{
        exceptionExcepted.expect(InvalidNumberOfPlayersException.class);
        treasureGame.validNumberOfPlayers(9);
    }

    /**
     * This method tests that the validation for the map size is correct
     * @throws InvalidMapSizeException
     * Whenever the map size is not within range and thus the test fails due to an InvalidMapSizeException that was
     * not expected
     */
    @Test
    public void testValidMapSizeInput()throws InvalidMapSizeException{
        assertEquals(true,treasureGame.validMapSize(4,5));
        assertEquals(true,treasureGame.validMapSize(4,50));
        assertEquals(true,treasureGame.validMapSize(4,25));
        assertEquals(true,treasureGame.validMapSize(5,8));
        assertEquals(true,treasureGame.validMapSize(5,50));
        assertEquals(true,treasureGame.validMapSize(5,25));
    }

    /**
     * This method tests whether an InvalidMapSizeException is generated when a bad input is outside the range , this
     * tests when the number of players is less than or equal to 4
     * @throws InvalidMapSizeException
     * Whenever the map size is out of range , in this test case this yields a good test result
     */
    @Test
    public void testInvalidMapSizeInput1()throws InvalidMapSizeException{
        exceptionExcepted.expect(InvalidMapSizeException.class);
        treasureGame.validMapSize(4,4);
    }

    /**
     * This method tests whether an InvalidMapSizeException is generated when a bad input is outside the range , this
     * tests when the number of players is greater than 4
     * @throws InvalidMapSizeException
     * Whenever the map size is out of range , in this test case this yields a good test result
     */
    @Test
    public void testInvalidMapSizeInput2()throws InvalidMapSizeException{
        exceptionExcepted.expect(InvalidMapSizeException.class);
        treasureGame.validMapSize(5,4);
    }

    /**
     * This test is used in order to check that the validation for the starting position is correct , i.e that
     * each starting position is on a grass tile.
     */
    @Test
    public void testStartingPositionCorrectness(){
        treasureGame.map = new Map(5);
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(treasureGame.map.getTileType(i,j)=='W'){
                    assertEquals(false, treasureGame.validStartingPosition(i,j));
                }else if(treasureGame.map.getTileType(i,j)=='T'){
                    assertEquals(false, treasureGame.validStartingPosition(i,j));
                }else {
                    assertEquals(true, treasureGame.validStartingPosition(i,j));
                }
            }
        }
    }

    /**
     * This test checks that whenever from the game launcher the constructor is supplied with bad input for map an
     * exception is generated
     */
    @Test
    public void testInvalidMapSizeNonDefaultConstructorM() throws InvalidMapSizeException,InvalidNumberOfPlayersException{
        exceptionExcepted.expect(InvalidMapSizeException.class);
        new GameEngine(1,5);
    }

    /**
     * This test checks that whenever from the game launcher the constructor is supplied with bad input for map an
     * exception is generated
     */
    @Test
    public void testInvalidNumberOfPlayersNonDefaultConstructorM() throws InvalidMapSizeException,InvalidNumberOfPlayersException{
        exceptionExcepted.expect(InvalidNumberOfPlayersException.class);
        new GameEngine(5,1);
    }

    /**
     * This test checks if the validate method returns true for valid move inputs
     * @throws InvalidCharacterInputMoveException
     * If this is thrown there is an error in the validate method
     */
    @Test
    public void testValidMoveByUser() throws InvalidCharacterInputMoveException{
        treasureGame2.initializeGame();
        treasureGame2.players[0].setPosition(1,1);
        treasureGame2.validateMove('U',0);
        treasureGame2.validateMove('D',0);
        treasureGame2.validateMove('L',0);
        treasureGame2.validateMove('R',0);
    }

    /**
     * This test checks whether the validate method returns an exception when the user tries to generate an up move
     * if he is in the top row
     * @throws InvalidCharacterInputMoveException
     * If this is thrown test result is successful
     */
    @Test
    public void testInvalidMoveUpByUser() throws InvalidCharacterInputMoveException{
        exceptionExcepted.expect(InvalidCharacterInputMoveException.class);
        treasureGame2.initializeGame();
        treasureGame2.players[0].setPosition(0,5);
        treasureGame2.validateMove('U',0);
    }

    /**
     * This test checks whether the validate method returns an exception when the user tries to generate a down move
     * if he is in the bottom row
     * @throws InvalidCharacterInputMoveException
     * If this is thrown test result is successful
     */
    @Test
    public void testInvalidMoveDownByUser() throws InvalidCharacterInputMoveException{
        exceptionExcepted.expect(InvalidCharacterInputMoveException.class);
        treasureGame2.initializeGame();
        treasureGame2.players[0].setPosition(treasureGame2.mapSize-1,5);
        treasureGame2.validateMove('D',0);
    }

    /**
     * This test checks whether the validate method returns an exception when the user tries to generate a left move
     * if he is in the first column
     * @throws InvalidCharacterInputMoveException
     * If this is thrown test result is successful
     */
    @Test
    public void testInvalidMoveLeftByUser() throws InvalidCharacterInputMoveException{
        exceptionExcepted.expect(InvalidCharacterInputMoveException.class);
        treasureGame2.initializeGame();
        treasureGame2.players[0].setPosition(5,0);
        treasureGame2.validateMove('L',0);
    }

    /**
     * This test checks whether the validate method returns an exception when the user tries to generate a right move
     * if he is in the last column
     * @throws InvalidCharacterInputMoveException
     * If this is thrown test result is successful
     */
    @Test
    public void testInvalidMoveRightByUser() throws InvalidCharacterInputMoveException{
        exceptionExcepted.expect(InvalidCharacterInputMoveException.class);
        treasureGame2.initializeGame();
        treasureGame2.players[0].setPosition(5,treasureGame2.mapSize-1);
        treasureGame2.validateMove('R',0);
    }

    /**
     * This test checks whether the validate method returns an exception when the user tries to enter a non move character
     * @throws InvalidCharacterInputMoveException
     * If this is thrown test result is successful
     */
    @Test
    public void testInvalidMoveCharacterByUser() throws InvalidCharacterInputMoveException{
        exceptionExcepted.expect(InvalidCharacterInputMoveException.class);
        treasureGame2.initializeGame();
        treasureGame2.players[0].setPosition(treasureGame2.mapSize-1,5);
        treasureGame2.validateMove('G',0);
    }

    /**
     * This test checks if the player when accesses a water tile dies
     */
    @Test
    public void testPlayerDiesByWater() {
        treasureGame3.initializeGame();
        treasureGame3.players[0].setPosition(0,0);

        char [][] tileMap = {
                {'G','W','G','W','W'},
                {'T','G','G','G','G'},
                {'G','G','G','G','G'},
                {'G','G','G','G','G'},
                {'G','G','G','G','G'}};
        treasureGame3.map.setMap(tileMap);
        treasureGame3.players[0].move('R');
        treasureGame3.playersEvents(0);
        assertEquals(false,treasureGame3.playerLivingStatus[0]);
    }
    @Test
    public void testPlayerFindsTreasure() {
        treasureGame3.initializeGame();
        treasureGame3.players[0].setPosition(0,0);

        char [][] tileMap = {
                {'G','W','G','W','W'},
                {'T','G','G','G','G'},
                {'G','G','G','G','G'},
                {'G','G','G','G','G'},
                {'G','G','G','G','G'}};
        treasureGame3.map.setMap(tileMap);
        treasureGame3.players[0].move('D');
        treasureGame3.playersEvents(0);
        assertEquals(true,treasureGame3.treasureFound);
    }

    /**
     * This method makes removes the allocated memory to the object.
     */
    @After
    public void tearDown(){
        treasureGame = null;
        treasureGame2 = null;
        treasureGame3 = null;
    }

}