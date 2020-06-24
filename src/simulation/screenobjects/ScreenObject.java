package simulation.screenobjects;

import simulation.basics.Vector;

public abstract class ScreenObject {

    /**
     * The position of the screen object.
     */
    public Vector position;
    /**
     * The velocity of the screen object.
     */
    public Vector velocity;

    /**
     * Initializes a new screen object based on a position and velocity.
     * @param position the origin position of the screen object
     * @param velocity the velocity of the screen object
     */
    public ScreenObject(Vector position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
    }
}
