package simulation.rules;

import simulation.Flock;
import simulation.basics.Vector;
import simulation.screenobjects.Boid;

import java.util.ArrayList;

public class KeepDistance extends Rule {

    private final double DISTANCE;
    private final Flock FLOCK;

    /**
     * Initializes a new KeepDistance Rule.
     *
     * @param distance distance to be kept
     * @param flock flock to which's boids the distance must be kept
     * @param weight weight of the rule
     */
    public KeepDistance(double distance, Flock flock, double weight) {
        super(weight);
        DISTANCE = distance;
        FLOCK = flock;
    }

    /**
     * Calculates the change vector in order to keep the specified distance from all boids in the flock.
     *
     * @param b the boid of which the change amount for that rule should be calculated
     * @param flock a flock of boids
     * @return a change vector in order to keep distance from other boids
     */
    @Override
    public Vector change(Boid b, ArrayList<Boid> flock) {
        Vector change = new Vector(0,0);

        for (Boid otherBoid : FLOCK.getBoidsInFlock()) {
            if (otherBoid != b) {
                Vector d = Vector.substractVector(otherBoid.position, b.position);  //get the vector between the two boids
                double distanceBetweenBoids = Vector.magnitude(d);      //calculate the distance between them
                if (distanceBetweenBoids < DISTANCE) {              //if the distance is smaller than specified, calculate the change vector
                    change = Vector.substractVector(change, d);
                }
            }
        }
        return Vector.divideByScale(change, 15);    //return the change vector reduced by a constant factor
    }
}
