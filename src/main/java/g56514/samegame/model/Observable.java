package g56514.samegame.model;

import g56514.samegame.view.Observer;

/**
 *
 * @author yohan
 */
public interface Observable {
    
    /**
     * Adds observer.
     * @param observer the observer.
     */
    void addObserver(Observer observer);
    
    /**
     * Removes observer.
     * @param observer the observer.
     */
    void removeObserver(Observer observer);
    
    /**
     * nNtifies to update.
     */
    void notifyObservers();
}
