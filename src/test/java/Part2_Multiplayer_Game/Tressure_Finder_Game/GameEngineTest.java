package Part2_Multiplayer_Game.Tressure_Finder_Game;

import Part2_Multiplayer_Game.Exceptions.InvalidMapSizeException;
import Part2_Multiplayer_Game.Exceptions.InvalidNumberOfPlayersException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * This class is used to test the game Engine class , note that only methods that could be tested by junit without
 * user live input could be tested , that's why the code coverage may be lower than previous tests
 */

public class GameEngineTest {
    private GameEngine treasureGame ; //stores the treasure game to be tested

    /**
     * A rule done in order to test for exceptions
     */
    @Rule
    public ExpectedException exceptionExcepted = ExpectedException.none();

    /**
     * This method is used to initialize the treasure game
     */
    @Before
    public void setUp(){
        treasureGame = new GameEngine();
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
     * This test is used to check that all arrays are initialized properly when starting the game with some good
     * parameters for map size and numbers of players
     */
    @Test
    public void testInitializedProperly(){
        treasureGame.mapSize =5;
        treasureGame.numberOfPlayers =2;
        treasureGame.StartGame();
        assertNotNull(treasureGame.players);
        assertNotNull(treasureGame.startingPosition);
        assertEquals(2,treasureGame.players.length);
        assertEquals(2,treasureGame.startingPosition.length);
    }

    /**
     * This test checks that whenever from the game launcher the constructor is supplied with bad input for map an
     * exception is generated
     */
    @Test
    public void testInvalidMapSizeNonDefaultConstructorM() throws InvalidMapSizeException,InvalidNumberOfPlayersException{
        exceptionExcepted.expect(InvalidMapSizeException.class);
        GameEngine game = new GameEngine(1,5);
    }

    /**
     * This test checks that whenever from the game launcher the constructor is supplied with bad input for map an
     * exception is generated
     */
    @Test
    public void testInvalidNumberOfPlayersNonDefaultConstructorM() throws InvalidMapSizeException,InvalidNumberOfPlayersException{
        exceptionExcepted.expect(InvalidNumberOfPlayersException.class);
        GameEngine game = new GameEngine(5,1);
    }

    /**
     * This method makes removes the allocated memory to the object.
     */
    @After
    public void tearDown(){
        treasureGame = null;
    }

}