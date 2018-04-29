package Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators;

import Part2_Multiplayer_Game.Tressure_Finder_Game.Map;
import Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreator;
import Part2_Multiplayer_Game.Tressure_Finder_Game.Maps.HazardousMap;

/**
 * This class is used to create a new concrete creator of type Hazardous.
 */

public class HazardousMapCreator extends MapCreator{

    public Map createMap(String Type, int size){
        HazardousMap hazMap;
        hazMap = HazardousMap.getInstance(size); //Call the method that gets the single instance
        hazMap.generateMap(); //generate the map tiles
        return hazMap;
    }
}
