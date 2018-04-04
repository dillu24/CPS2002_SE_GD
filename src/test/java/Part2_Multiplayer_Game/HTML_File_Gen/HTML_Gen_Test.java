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
    private int i, j;
    private Map map;
    private boolean [][] isVisited;
    private int mapSize;
    private TreasureFinderPlayer []players;
    private int playerNo;
    private Position playerPosition;
    private int xPos, yPos;
    private HTML_Gen hGen;
    /**
     * Creates a new player in a dummy position irrespective of the map size , since only the move function is tested
     * for the reason mentioned above
     */
    @Before
    public void setup() {
        playerNo = 0;
        mapSize = 5;
        player = new TreasureFinderPlayer(0, 0,mapSize);
        map = new Map(mapSize);

        players = new TreasureFinderPlayer[1]; //initialize the player array //added 1 instead by dylan coz you were initializing empty array
        players[0] = player; //added player by dylan coz you need to add player
        isVisited = new boolean[mapSize][mapSize];//added by dylan to initialzie visited

        hGen = null;
        try {
            hGen = new HTML_Gen(players,playerNo,mapSize,map,isVisited);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        player = null;
        map = null;
    }

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

    @Test
    public void isPlayerHereTest(){
        xPos = 0;
        yPos = 3;
        players[playerNo] = new TreasureFinderPlayer(xPos,yPos,mapSize);
        playerPosition = new Position(); //create new player according to his starting position
        playerPosition.setX(xPos);
        playerPosition.setY(yPos);

        String playerPresent = hGen.isPlayerHere(players,playerNo,0,3);
        assertEquals("P1",playerPresent); //you wanted it p1?
        playerPresent = hGen.isPlayerHere(players,playerNo,0,4);
        assertEquals("",playerPresent);
    }
}