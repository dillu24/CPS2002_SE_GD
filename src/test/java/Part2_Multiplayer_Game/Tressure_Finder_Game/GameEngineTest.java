package Part2_Multiplayer_Game.Tressure_Finder_Game;

import Part2_Multiplayer_Game.Exceptions.InvalidMapSizeException;
import Part2_Multiplayer_Game.Exceptions.InvalidNumberOfPlayersException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class GameEngineTest {
    private GameEngine treasureGame ;

    @Rule
    public ExpectedException exceptionExcepted = ExpectedException.none();

    @Before
    public void setUp(){
        treasureGame = new GameEngine();
    }

    @Test
    public void testValidNumberOfPlayersInput() throws InvalidNumberOfPlayersException{
        assertEquals(true,treasureGame.validNumberOfPlayers(2));
        assertEquals(true,treasureGame.validNumberOfPlayers(8));
        assertEquals(true,treasureGame.validNumberOfPlayers(5));
    }

    @Test
    public void testInvalidNumberOfPlayersInput() throws InvalidNumberOfPlayersException{
        exceptionExcepted.expect(InvalidNumberOfPlayersException.class);
        treasureGame.validNumberOfPlayers(9);
    }

    @Test
    public void testValidMapSizeInput()throws InvalidMapSizeException{
        assertEquals(true,treasureGame.validMapSize(4,5));
        assertEquals(true,treasureGame.validMapSize(4,50));
        assertEquals(true,treasureGame.validMapSize(4,25));
        assertEquals(true,treasureGame.validMapSize(5,8));
        assertEquals(true,treasureGame.validMapSize(5,50));
        assertEquals(true,treasureGame.validMapSize(5,25));
    }

    @Test
    public void testInvalidMapSizeInput()throws InvalidMapSizeException{
        exceptionExcepted.expect(InvalidMapSizeException.class);
        assertEquals(true,treasureGame.validMapSize(4,4));
    }

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

    @Test
    public void testPlayerArrayInitializedCorrectly(){
        treasureGame.map = new Map(5);
        treasureGame.initializeGamePlayers(2,5);
        assertNotNull(treasureGame.players);
        assertEquals(2,treasureGame.players.length);
    }

    @Test
    public void testStartingPositionArrayInitializedCorrectly(){
        treasureGame.map = new Map(5);
        treasureGame.initializeGamePlayers(2,5);
        assertNotNull(treasureGame.startingPosition);
        assertEquals(2,treasureGame.startingPosition.length);
    }



    @After
    public void tearDown(){
        treasureGame = null;
    }

}