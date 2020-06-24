package simulation.rules;

import simulation.basics.Screen;
import simulation.basics.Vector;
import simulation.screenobjects.Barrier;
import simulation.screenobjects.Boid;

import java.util.ArrayList;

public class AvoidObject extends Rule {

    private final double DISTANCE;
    private final Screen SCREEN;

    /**
     * Initializes a new AvoidObject Rule.
     *
     * @param screen screen on which the barriers are displayed
     * @param distance distance to be kept to barriers
     * @param weight weight of the rule
     */
    public AvoidObject(Screen screen, double distance, double weight) {
        super(weight);
        SCREEN = screen;
        DISTANCE = distance;
    }

    /**
     * Calculates the change vector in order to keep the specified distance from all barriers on the screen.
     *
     * @param b the boid of which the change amount for that rule should be calculated.
     * @param flock a flock of boids
     * @return a change vector in order to keep distance from barriers
     */
    @Override
    public Vector change(Boid b, ArrayList<Boid> flock) {
        Vector change = new Vector(0,0);

        for (Barrier barrier : SCREEN.getAllBarriers()) {
            Vector d = Vector.substractVector(barrier.position, b.position); //get the vector between the boid and the barrier
            double distanceBetween = Vector.magnitude(d);   //calculate the distance between them
            if (distanceBetween < DISTANCE) {
                change = Vector.substractVector(change, d);  //if the distance is smaller than specified, calculate the change vector
            }
        }
        return Vector.divideByScale(change, 1);    //return the change vector reduced by a constant factor
    }
}
