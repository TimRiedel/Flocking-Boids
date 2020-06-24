package simulation;

import java.util.ArrayList;

/**
 * Manages flocks (groups of boids).
 */
public class FlockManager {

    /**
     * An ArrayList that contains all flocks managed by the flock manager.
     */
    private final ArrayList<Flock> allFlocks;

    /**
     * Initializes a new FlockManager and creates an empty ArrayList, where flocks can be added to.
     */
    public FlockManager() {
        allFlocks = new ArrayList<>();
    }

    /**
     * Adds a flock to the FlockManager.
     * @param flock Specifies the flock to be added.
     */
    public void add(Flock flock) {
        allFlocks.add(flock);
    }

    /**
     * Removes a flock from the FlockManager.
     * @param flock Specifies the flock to be removed.
     */
    public void remove(Flock flock) {
        allFlocks.remove(flock);
    }

    /**
     * Updates all flocks.
     */
    public void update() {
        for (Flock f : allFlocks) {
            f.update();
        }
    }

    /**
     * Gives an ArrayList of all flocks that are managed by the flockManager.
     * @return the ArraysList of all flocks managed by the flockManager.
     */
    public ArrayList<Flock> getAllFlocks() {
        return allFlocks;
    }
}
