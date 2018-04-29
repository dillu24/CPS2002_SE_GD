package Part2_Multiplayer_Game.Tressure_Finder_Game.ObSubjPattern;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Position;

/**
 * This class is used to define the Observer in the Treasure Finder multi-player game ,in this game each player has a
 * position , where this position represents the player's location in the map . This class also contains a move method
 * that allows the player to move to a new position in the map , note that this method does not verify if the position
 * within the map is valid , but assumes that in the game engine the move was verified to be correct , and thus in this
 * method the new position of the player in the map is set. Also , each move is done according to the index of the
 * the 2-D array and not the actual x-y plane . The player also has an isVisited boolean 2-D array that is used by the
 * HTML object to check the parts of the map that are uncovered and hence need to be viewed.
 */
public class TreasureFinderPlayer extends Observer {
    private Position position = new Position(); // Stores the position of the player in the map
    public boolean[][] isVisited; // The array used to specify which tiles are uncovered or not
    private TeamSubject tSubject; //stores the team subject

    /**
     * This constructor is used to create a new player with given parameters below
     * @param tSubject
     * Stores the team subject the player is assigned to
     * @param x
     * Stores the x value position of the player
     * @param y
     * Stores the y value position of the player
     * @param mapSize
     * Stores the map size the player is playing in
     */

    public TreasureFinderPlayer(TeamSubject tSubject, int x, int y, int mapSize){
        this.tSubject = tSubject;
        this.tSubject.attach(this);
        isVisited = tSubject.getState(); //to not loose previous state
        setPosition(x,y);
    }


    /**
     * A constructor used to give the created player a position in the map
     * @param x
     * Stores the x co-ordinate that will be given to the position of this new player
     * @param mapSize
     * Stores the map size so that the isVisited array is initialized
     * @param y
     * Stores the y co-ordinate that will be given to the position of this new player
     */
    public TreasureFinderPlayer(int x, int y, int mapSize){

        isVisited = new boolean[mapSize][mapSize];
        setPosition(x,y);
    }

    /**
     * The method is used to set the position of the player to a new position in the map , and uncover the starting position
     * @param x
     * Stores the new x co-ordinate of the position of the player
     * @param y
     * Stores the new y co-ordinate of the position of the player
     */

    public void setPosition(int x,int y){
        position.setX(x);
        position.setY(y);
        isVisited[x][y] = true;
        if(tSubject != null){ //in order to check if we are in collaborative mode
            tSubject.setState(isVisited);
        }
    }

    /**
     * A getter used to get the position value of the player
     * @return
     * A Position type variable that contains the player's position in the map
     */
    public Position getPosition(){
        return position;
    }

    /**
     * The move method is used so that the player will be moved to a new position upon the entering of a new character
     * input by the user , as already mentioned this method assumes that the game engine has already verified a correct
     * input from the user , thus this method only sets the new position values according to the entered character.
     * @param direction
     * Stores the character entered by the user so that the player moves accordingly.
     */

    public void move(char direction){

        switch (direction){
            case 'U':
                setPosition(position.getX()-1,position.getY());
                break;
            case 'D':
                setPosition(position.getX()+1,position.getY());
                break;
            case 'L':
                setPosition(position.getX(),position.getY()-1);
                break;
            case 'R':
                setPosition(position.getX(),position.getY()+1);
                break;
        }
    }

    /**
     * This method is used by the subject to update the observer's state
     */

    @Override
    public void update() {
        isVisited = tSubject.getState();
    }

}
