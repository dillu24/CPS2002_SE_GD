package Part2_Multiplayer_Game.Tressure_Finder_Game;

import Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators.HazardousMapCreator;
import Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators.SafeMapCreator;

/**
 * Created by Dylan Galea on 27/04/2018.
 */
public class MapCreator {
    public Map createMap(String type,int size){
        MapCreator creator = findConcreteCreator(type,size);
        return creator.createMap(type,size);
    }
    private MapCreator findConcreteCreator(String type,int size){
        if(type.equals("Safe")){
            return new SafeMapCreator();
        }else if (type.equals("Hazardous")){
            return new HazardousMapCreator();
        }
        return new SafeMapCreator();
    }
}
