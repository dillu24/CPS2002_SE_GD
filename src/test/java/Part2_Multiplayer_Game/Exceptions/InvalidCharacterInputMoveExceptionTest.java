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
    @Before
    public void setUp(){
        invException = new InvalidCharacterInputMoveException('c');
    }

    @Test
    public void getInvalidCharacter(){ //Tests that the correct input has been stored as invalid.
        assertEquals('c',invException.getInvalidCharacter());
    }

    @After
    public void tearDown(){
        invException = null;
    }

}