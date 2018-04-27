package Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators;

import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreator;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Maps.HazardousMap;

/**
 * Created by Dylan Galea on 27/04/2018.
 */
public class HazardousMapCreator extends MapCreator{
    public Map createMap(String type,int size){
        HazardousMap map = new HazardousMap(size);
        map.generateMap();
        return map;
    }
}
