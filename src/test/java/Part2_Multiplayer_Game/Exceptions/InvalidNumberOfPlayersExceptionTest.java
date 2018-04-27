package Part2_Multiplayer_Game.Exceptions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class is used to test the InvalidNumberOfPlayersException class , i.e it tests that the invalid data is stored correctly
 * when an object of type InvalidNumberOfPlayersException is created.
 */
public class InvalidNumberOfPlayersExceptionTest {
    private InvalidNumberOfPlayersException exception; //Stores the object of type InvalidNumberOfPlayersException
    /**
     * This method is used to initialize the InvalidNumberOfPlayersException object with some dummy invalid value of 60
     */
    @Before
    public void setUp(){
        exception = new InvalidNumberOfPlayersException(60);
    }

    /**
     * This test is then used in order to check that the actual value passed in the @Before method is the same
     * as the one stored in the invalidNumber local variable.
     */

    @Test
    public void testInvalidNumber(){
        assertEquals(60,exception.getInvalidNumber());
    }

    /**
     * This method is used to remove the object memory after termination of the test.
     */

    @After
    public void tearDown() throws Exception {
        exception = null;
    }


}