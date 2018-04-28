package Part2_Multiplayer_Game.Tressure_Finder_Game.ObSubjPattern;

import Part2_Multiplayer_Game.Player.TreasureFinderPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Observer;

/**
 * Created by Dylan Galea on 28/04/2018.
 */

/**
 * This test class is used to check that the basic methods for the observers are correct
 */
public class SubjectTest {
    private Subject subject; //stores the subject instance

    /**
     * Give memory to the object
     */
    @Before
    public void setUp(){
        subject = new Subject();
    }

    /**
     * Deallocate memory
     */
    @After
    public void tearDown(){
        subject = null;
    }

    /**
     * This method is used to test that after attaching a new player to the empty observer list , it is correctly stored,
     * thus it's size is 1.
     */
    @Test
    public void testValidAttach(){
        Observer player = new TreasureFinderPlayer();
        subject.attach(player);
        assertEquals(1,subject.observerList.size());
    }

    /**
     * This test is used to check that after detaching the observer list is updated accordingly , thus it's size
     * is decreased by 1.
     */
    @Test
    public void testValidDetach(){
        Observer player = new TreasureFinderPlayer();
        subject.detach(player);
        assertEquals(0,subject.observerList.size());
    }
}
