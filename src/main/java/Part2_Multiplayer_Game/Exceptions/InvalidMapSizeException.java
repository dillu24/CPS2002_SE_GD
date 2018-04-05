package Part2_Multiplayer_Game.Exceptions;

/**
 * This exception class is used as to create an exception whenever a user enters a map size outside the range
 */
public class InvalidMapSizeException extends Exception {
    private int invalidNumber; //Stores the invalid number that the user entered

    /**
     * The Constructor used to give the invalidNumber variable the number that caused the exception
     * @param invalidNumber
     * Contains the value that caused the exception , that will be assigned to the local variable
     * 'invalidNumber
     */
    public InvalidMapSizeException(int invalidNumber){
        this.invalidNumber = invalidNumber;
    }

    /**
     * A method used to get the value of the invalidNumber that caused the exception
     * @return
     * The value of the invalid number that caused the exception.
     */

    int getInvalidNumber(){
        return invalidNumber;
    }
}
