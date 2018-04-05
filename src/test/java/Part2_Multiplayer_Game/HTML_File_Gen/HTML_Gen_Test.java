package Part2_Multiplayer_Game.HTML_File_Gen;

import Part2_Multiplayer_Game.Player.TreasureFinderPlayer;
import Part2_Multiplayer_Game.Tressure_Finder_Game.GameEngine;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HTML_Gen_Test {

    private TreasureFinderPlayer player;
    private int turnNo;
    private Map map;
    private boolean [][] isVisited;
    private int mapSize;
    private TreasureFinderPlayer []players;
    private int playerNo;
    private Position playerPosition;
    private int xPos, yPos;
    private HTML_Gen hGen;
    /**
     * Creates a HTML_Gen object with the specific information listed
     */
    @Before
    public void setup() {
        turnNo = 2;
        playerNo = 0;
        mapSize = 5;
        player = new TreasureFinderPlayer(0, 0,mapSize);
        map = new Map(mapSize);

        players = new TreasureFinderPlayer[1]; //initialize the player array //added 1 instead by dylan coz you were initializing empty array
        players[0] = player; //added player by dylan coz you need to add player
        isVisited = new boolean[mapSize][mapSize];//added by dylan to initialzie visited

        hGen = null;
        try {
            hGen = new HTML_Gen(players,playerNo,turnNo,mapSize,map,isVisited);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets values to null or 0, respectively
     */
    @After
    public void tearDown(){
        player = null;
        map = null;
        hGen = null;
        turnNo = 0;
        isVisited = null;
        mapSize = 0;
        players = null;
        playerNo = 0;
        playerPosition = null;
        xPos=0;
        yPos =0;
    }

    /**
     * A test used to track if the method getTileColour correctly works, by setting up a specific known map
     * where we see that the player is on a green tile (when at position 0,0). This test will set the player position
     * on different tiles throughout, each time checking the tile colour for all 4 different colour types, including
     * grey for when it is not visited.
     */

    @Test
    public void getTileColourTest() {
        boolean[][] isVisited = {
                {true,true,true,true,true},
                {false,false,false,false,false},
                {true,false,true,false,true},
                {false,false,false,false,false},
                {true,true,true,true,true} };
        char [][] tileMap = {
                {'G','W','W','G','G'},
                {'G','W','G','G','G'},
                {'G','G','T','G','G'},
                {'G','G','G','G','G'},
                {'G','G','G','G','G'}
        };
        map.setMap(tileMap);

        String colour = hGen.getTileColour(0,0,map,isVisited);
        assertEquals("bgcolor = #00FF00",colour);
        colour = hGen.getTileColour(0,1,map,isVisited);
        assertEquals("bgcolor = #00FFFF",colour);
        colour = hGen.getTileColour(2,2,map,isVisited);
        assertEquals("bgcolor = #FFFF00",colour);
        colour = hGen.getTileColour(1,1,map,isVisited);
        assertEquals("bgcolor = #D6D6D6",colour);
    }

    /**
     * A test used to track if the method isPlayerHere correctly works. This is done by placing the player at a specific
     * position, then checking 2 different position, one containing the player and one not, to see if there would be the
     * appropriate output.
     */

    @Test
    public void isPlayerHereTest(){
        xPos = 0;
        yPos = 3;
        players[playerNo] = new TreasureFinderPlayer(xPos,yPos,mapSize);
        playerPosition = new Position(); //create new player according to his starting position
        playerPosition.setX(xPos);
        playerPosition.setY(yPos);

        String playerPresent = hGen.isPlayerHere(players,playerNo,0,3);
        assertEquals("P1",playerPresent);
        playerPresent = hGen.isPlayerHere(players,playerNo,0,4);
        assertEquals("",playerPresent);
    }
}