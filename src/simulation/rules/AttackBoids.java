package simulation.rules;

import simulation.Flock;
import simulation.basics.Vector;
import simulation.screenobjects.Boid;

import java.util.ArrayList;

public class AttackBoids extends Rule {

    private final double DISTANCE;
    private final Flock FLOCK;

    /**
     * Initializes a new AttackBoids Rule.
     *
     * @param distance distance in which boids of the enemy flock should be attacked
     * @param flock enemy flock (the boids to be attacked)
     * @param weight weight of the rule
     */
    public AttackBoids(double distance, Flock flock, double weight) {
        super(weight);
        DISTANCE = distance;
        FLOCK = flock;
    }

    /**
     * Calculates a change vector towards an average position of enemy boids in order to attack them.
     *
     * @param b the boid of which the change amount for that rule should be calculated.
     * @param flock a flock of boids
     * @return a change vector towards other boids
     */
    @Override
    public Vector change(Boid b, ArrayList<Boid> flock) {
        Vector pos = b.position;

        int i = 1;
        for (Boid otherBoid : FLOCK.getBoidsInFlock()) {

            if (b != otherBoid) {
                Vector d = Vector.substractVector(otherBoid.position, pos);  //Vector between tested boid and other boid
                double distanceBetweenBoids = Vector.magnitude(d);  //distance between two boids

                if (distanceBetweenBoids < DISTANCE) {
                    pos = Vector.addVector(pos, otherBoid.position);    //all positions within distance added together
                    i++;   //increment number of enemy boids in the surroundings
                }
            }
        }
        pos = Vector.divideByScale(pos, i);  //calculate average position in the surroundings of a boid by dividing the position vector by the number of boids in the surroundings

        return Vector.divideByScale(Vector.substractVector(pos, b.position), 20); //scaling of change vector with a constant value
    }
}
