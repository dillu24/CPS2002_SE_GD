package Part2_Multiplayer_Game.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This test class tests the player class , since the move function depends on the correct usage of the setters and
 * getters of both the Position class and the TreasureFinderPlayer class , only tests for the move function were
 * supplied since if the setters and getters are wrong than the move test would also fail.
 */
public class TreasureFinderPlayerTest {
    private TreasureFinderPlayer player; //stores the play object
    private boolean[][] isVisited1 = {
            {true,true,true,false,false},
            {false,false,false,false,false},
            {false,false,false,false,false},
            {false,false,false,false,false},
            {false,false,false,false,false}};

    /**
     * Creates a new player in a dummy position irrespective of the map size , since only the move function is tested
     * for the reason mentioned above
     */
    @Before
    public void setup(){
        player = new TreasureFinderPlayer(20,20,50);
    }

    /**
     *Tests whenever the user inputs a 'U' character which means the player wants to move up by 1 position . Thus
     * only the x co-ordinate changes by increasing it's value by 1 , since 2-D array indexing is considered
     */
    @Test
    public void testUppCharacter(){
        player.move('U');
        assertEquals(19,player.getPosition().getX());
        assertEquals(20,player.getPosition().getY());
    }

    /**
     *Tests whenever the user inputs a 'D' character which means the player wants to move down by 1 position . Thus
     * only the x co-ordinate changes by decreasing it's value by 1 , since 2-D array indexing is considered
     */
    @Test
    public void testDownCharacter(){
        player.move('D');
        assertEquals(21,player.getPosition().getX());
        assertEquals(20,player.getPosition().getY());
    }

    /**
     *Tests whenever the user inputs a 'L' character which means the player wants to move left by 1 position . Thus
     * only the y co-ordinate changes by decreasing it's value by 1 , since 2-D array indexing is considered.
     */
    @Test
    public void testLeftCharacter(){
        player.move('L');
        assertEquals(20,player.getPosition().getX());
        assertEquals(19,player.getPosition().getY());
    }

    /**
     *Tests whenever the user inputs a 'R' character which means the player wants to move right by 1 position . Thus
     * only the y co-ordinate changes by increasing it's value by 1, since 2-D array indexing is considered.
     */
    @Test
    public void testRightCharacter(){
        player.move('R');
        assertEquals(20,player.getPosition().getX());
        assertEquals(21,player.getPosition().getY());
    }

    /**
     *This test was done as to assure that whenever the user enters a wrong input the player position remains the same,
     * but this will not happen since verification of the input is handled in the game engine.
     */
    @Test
    public void testInvalidCharacter(){
        player.move('F');
        assertEquals(20,player.getPosition().getX());
        assertEquals(20,player.getPosition().getY());
    }

    @Test
    public void testUpdate(){ //Trying to test first we will have a player with some info, then we will update the subject
        //We will moce and update again and check if it is working by comparing the player's isVisited and the subject's isVisited
        isVisited1 = player.getMapState();
        TeamSubject tSubject = player.update();
        player.move('R');
        TeamSubject tSubject = player.update();
        assertArrayEquals(isVisited1,tSubject.getState());
    }
    /**
     * Deallocate the object's memory
     */
    @After
    public void teardown(){
        player = null;
    }
}
