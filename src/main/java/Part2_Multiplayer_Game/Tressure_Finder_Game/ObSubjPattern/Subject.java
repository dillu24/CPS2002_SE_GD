package Part2_Multiplayer_Game.Tressure_Finder_Game.ObSubjPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to encode the Subject for the Observer Design pattern .This class providesthe basic functions
 * for the concrete observers.
 */
public class Subject {
    List<Observer> observerList = new ArrayList<Observer>(); //Stores the List of observers

    /**
     * This method is used to add a new observer to the observer list
     * @param observer
     * Stores the observer object to be added to the observer list
     */
    public void attach(Observer observer){
        observerList.add(observer);
    }

    /**
     * This method is used in order to remove an object from the observer list
     * @param observer
     * Stores the observer to be deleted from the list of observers
     */
    public void detach(Observer observer){
        observerList.remove(observer);
    }

    /**
     * This method is used to notify the observers that a change in state has occurred in the object.
     */
    public void Notify(){
        for (Observer anObserverList : observerList) {
            anObserverList.update();
        }
    }

}
