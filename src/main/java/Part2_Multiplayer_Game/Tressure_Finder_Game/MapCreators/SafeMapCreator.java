package Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators;

import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreator;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Maps.SafeMap;

/**
 * Created by Dylan Galea on 27/04/2018.
 */
public class SafeMapCreator extends MapCreator {
    public Map createMap(String Type,int size){
        SafeMap map = new SafeMap(size);
        map.generateMap();
        return map;
    }
}
