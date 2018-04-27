package Part2_Multiplayer_Game.Exceptions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class is used to test that whenever an exception object is created and given an invalid character , that input
 * is correctly stored in the invalidCharacter variable , thus when outputting to the user the correct identification of
 * the error would be reported.
 */

public class InvalidCharacterInputMoveExceptionTest {
    private InvalidCharacterInputMoveException invException; //The user defined exception object

    /**
     * Creates an object of type InvalidCharacterInputMoveException with some character 'c' as to test if the
     * correct input is stored , so that correct output will be outputted
     */
    @Before
    public void setUp(){
        invException = new InvalidCharacterInputMoveException('c');
    }

    /**
     * Tests that the correct input has been stored.
     */

    @Test
    public void testGetInvalidCharacter(){
        assertEquals('c',invException.getInvalidCharacter());
    }

    /**
     * Deallocates the objects memory
     */
    @After
    public void tearDown(){
        invException = null;
    }

}