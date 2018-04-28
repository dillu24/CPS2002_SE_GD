package Part2_Multiplayer_Game.Tressure_Finder_Game;

import Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators.HazardousMapCreator;
import Part2_Multiplayer_Game.Tressure_Finder_Game.MapCreators.SafeMapCreator;

/**
 * This class is used as the super class of all creators , thus calling the concrete creator's createMap method.
 */
public class MapCreator {

    public Map createMap(String type,int size){
        MapCreator creator = findConcreteCreator(type,size); //get the concrete class according to type passed by user
        return creator.createMap(type,size); // call its factory method
    }

    /**
     * This method is used to return the instance of the creator according to the type passed by the user
     * @param type
     * Stores the type of the map the user wants
     * @param size
     * Stores the size of the new map
     * @return
     * An instance of the creator to create the map
     */
    private MapCreator findConcreteCreator(String type,int size){
        if(type.equals("Safe")){
            return new SafeMapCreator();
        }else if (type.equals("Hazardous")){
            return new HazardousMapCreator();
        }
        return new SafeMapCreator(); //by default do it safe map
    }
}
