package Part2_Multiplayer_Game.Exceptions;

/**
 * This exception class is used as to create an exception whenever a user entered an invalid move character.
 */
public class InvalidCharacterInputMoveException extends Exception {
    private char invalidCharacter; //Stores the invalid character that the user entered

    /**
     * The Constructor used to give the invalidCharacter variable the character that caused the exception
     * @param invalidCharacter
     * Contains the value of the character that caused the exception , that will be assigned to the local variable
     * 'invalidCharacter
     */
    public InvalidCharacterInputMoveException(char invalidCharacter){
        this.invalidCharacter = invalidCharacter;
    }

    /**
     * A method used to get the value of the invalidCharacter that caused the exception
     * @return
     * The value of the invalid character that caused the exception.
     */

    public char getInvalidCharacter(){
        return invalidCharacter;
    }
}
