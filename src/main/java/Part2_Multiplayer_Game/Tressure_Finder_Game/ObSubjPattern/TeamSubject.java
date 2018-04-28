package Part2_Multiplayer_Game.Tressure_Finder_Game.ObSubjPattern;

/**
 * This class is used to encode a team in the game , where each player that will be observing this team , will have
 * the same map state and will be notified whenever their map state is updated.
 */

public class TeamSubject extends Subject {
    private boolean mapState[][]; //stores a 2-D containing true values if a tile is visited and false if not

    /**
     * This constructor is used to create a team subject based on a map size , the map size is important so that
     * null pointer exceptions do not happen
     * @param mapSize
     * Stores the size of the map
     */
    public TeamSubject(int mapSize){
        mapState = new boolean [mapSize][mapSize]; //give memory to the state array
    }

    /**
     * This getter is used to return the private state of the team map
     * @return
     * The mapState 2-D array
     */
    public boolean[][] getMapState(){
        return mapState;
    }

    /**
     * This method is used to set the map state to a new state , and notify all the team members of the change that
     * occurred
     * @param mapState
     * The state that will be assigned to this.mapState
     */
    public void setMapState(boolean mapState[][]){
        this.mapState = mapState;
        Notify();
    }
}
