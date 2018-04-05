package Part2_Multiplayer_Game.Exceptions;

/**
 * This exception class is used as to create an exception whenever a user entered a number of players outside the range
 */
public class InvalidNumberOfPlayersException extends Exception {
    private int invalidNumber; //Stores the invalid number that the user entered

    /**
     * The Constructor used to give the invalidNumber variable the number that caused the exception
     * @param invalidNumber
     * Contains the value that caused the exception , that will be assigned to the local variable
     * 'invalidNumber
     */
    public InvalidNumberOfPlayersException(int invalidNumber){
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

