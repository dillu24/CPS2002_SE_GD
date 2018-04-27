package Part2_Multiplayer_Game.Exceptions;

/**
 * Created by Dylan Galea on 27/04/2018.
 */
public class InvalidMapTypeException extends Exception {
    private String invalidMapType;
    public InvalidMapTypeException(String invalidMapType){
        this.invalidMapType = invalidMapType;
    }
    public String getInvalidMapType(){
        return invalidMapType;
    }

}
