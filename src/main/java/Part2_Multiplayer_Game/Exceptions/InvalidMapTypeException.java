package Part2_Multiplayer_Game.Exceptions;

/**
 * This class is used to encode an exception which is created whenever the users enters an invalid type name for the
 * map.
 */
public class InvalidMapTypeException extends Exception {
    private String invalidMapType; //Stores the error message that created the exception

    /**
     * This constructor is used to create a new InvalidMapTypeException
     * @param invalidMapType
     * Stores the error message to be stored in this.invalidMapType
     */
    public InvalidMapTypeException(String invalidMapType){
        this.invalidMapType = invalidMapType;
    }

    /**
     * This method is used to return the private error message stored in invalidMapType
     * @return
     * The error message of type string
     */
    public String getInvalidMapType(){
        return invalidMapType;
    }

}
