package Part2_Multiplayer_Game.Tressure_Finder_Game.ObSubjPattern;

import Part2_Multiplayer_Game.Player.TreasureFinderPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class is used to test the Team Subject
 */
public class TeamSubjectTest {
    private TeamSubject teamSubject; //stores a team subject
    private TreasureFinderPlayer player; // stores one of the player instances
    private boolean playerMapState[][] =  //stores the map state for the team
            {{true,false,true,false,true},
            {true,false,true,false,true},
            {true,false,true,false,true},
            {true,false,true,false,true},
            {true,false,true,false,true}};
    private boolean teamMapState[][] =  //stores the map state for some player
            {{false,true,false,true,true},
            {true,false,true,false,true},
            {true,false,true,false,true},
            {true,false,true,false,true},
            {true,false,true,false,true}};

    /**
     * Used to set up test
     */
    @Before
    public void setUp(){
        teamSubject = new TeamSubject(5);
        player = new TreasureFinderPlayer();
        teamSubject.attach(player);
        player.isVisited = playerMapState;
    }

    /**
     * Deallocate memory
     */
    @After
    public void tearDown(){
        teamSubject = null;
        player = null;
    }

    /**
     * This test is used to check that when setting a new map state , the player's map state is updated aswell.
     */
    @Test
    public void testCorrectNotificationAndUpdateOfMapState(){
        teamSubject.setMapState(teamMapState);
        assertEquals(teamSubject.getMapState(),player.isVisited);
    }
}