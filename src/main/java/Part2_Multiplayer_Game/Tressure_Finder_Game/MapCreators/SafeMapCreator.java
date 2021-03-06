package Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators;

import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreator;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Maps.SafeMap;

/**
 * This class is used to create a SafeMapCreator that creates a safe map of size passed as parameters
 */

public class SafeMapCreator extends MapCreator {

    public Map createMap(String Type, int size){
        SafeMap safeMap;
        safeMap = SafeMap.getInstance(size);//Call the method that gets the single instance
        safeMap.generateMap(); //generate the map tiles
        return safeMap;
    }
}
