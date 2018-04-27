package Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators;

import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreator;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Maps.HazardousMap;

/**
 * This class is used to create a new concrete creator of type Hazardous.
 */

public class HazardousMapCreator extends MapCreator{
    public Map createMap(String type,int size){
        HazardousMap map = new HazardousMap(size); //crate hazardous map
        map.generateMap(); //generatethe tiles
        return map;
    }
}
