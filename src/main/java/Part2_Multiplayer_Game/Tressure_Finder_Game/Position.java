package Part2_Multiplayer_Game.Tressure_Finder_Game;

/**
 * This class encodes the Position of each player in the Map , it also contains public getters and setters since in order
 * to have encapsulation the x and y co-ordinates where set up to private.
 */
public class Position {
    private int x; // Stores the x co-ordinate in the map
    private int y; // Stores the y-co-ordinate in the map

    /**
     * Default constructor to create an object of type Position
     */
    public Position(){}

    /**
     * The setter method used to give values to the x co-ordinate variable
     * @param x
     * Contains the x co-ordinate value that will be assigned to the local variable 'x'
     */

    public void setX(int x){
        this.x = x;
    }

    /**
     * The setter method used to give value to the y co-ordinate variable
     * @param y
     * Contains the y co-ordinate value that will be assigned to the local variable 'y'
     */

    public void setY(int y){
        this.y = y;
    }

    /**
     * The getter method used to get the x co-ordinate value;
     * @return
     * The x co-ordinate value of this position in the map
     */

    public int getX(){
        return x;
    }

    /**
     * The getter method used to get the y co-ordinate value;
     * @return
     * The y co-ordinate value of this position in the map
     */

    public int getY(){
        return y;
    }

}
