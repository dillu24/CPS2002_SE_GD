package Part2_Multiplayer_Game.Exceptions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dylan Galea on 27/04/2018.
 */
public class InvalidMapTypeExceptionTest {
    private InvalidMapTypeException exception; //Stores the object of type InvalidMapTypeException
    /**
     * This method is used to initialize the InvalidMapTypeException object with some dummy invalid value of "In-Safe
     */
    @Before
    public void setUp(){
        exception = new InvalidMapTypeException("In-safe");
    }

    /**
     * This test is then used in order to check that the actual value passed in the @Before method is the same
     * as the one stored in the invalidMapType local variable.
     */

    @Test
    public void testInvalidNumber(){
        assertEquals("In-safe",exception.getInvalidMapType());
    }

    /**
     * This method is used to remove the object memory after termination of the test.
     */

    @After
    public void tearDown(){
        exception = null;
    }
}
