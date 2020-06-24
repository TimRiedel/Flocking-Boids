package simulation.rules;

import simulation.basics.Vector;
import simulation.screenobjects.Boid;

import java.util.ArrayList;

public class KeepTowardCentre extends Rule {

    private final double DISTANCE;

    /**
     * Initializes a new KeepTowardCentre Rule.
     *
     * @param distance radius of search for centre of surrounding boids
     * @param weight weight of rule
     */
    public KeepTowardCentre(double distance, double weight) {
        super(weight);
        DISTANCE = distance;
    }

    /**
     * Calculates the change vector in order to move toward the center of surrounding boids.
     *
     * @param b the boid of which the change amount for that rule should be calculated.
     * @param flock a flock of boids
     * @return a change vector to keep near the centre of surrounding boids
     */
    @Override
    public Vector change(Boid b, ArrayList<Boid> flock) {

        Vector pos = b.position;

        int i = 1;
        for (Boid otherBoid : flock) {

            if (b != otherBoid) {
                Vector d = Vector.substractVector(otherBoid.position, pos);   //Vector between tested boid and other boid
                double distanceBetweenBoids = Vector.magnitude(d);          //distance between two boids

                if (distanceBetweenBoids < DISTANCE) {
                    pos = Vector.addVector(pos, otherBoid.position);     //all positions within distance added together
                    i++;    //increment number of boids in the surroundings
                }
            }
        }
        pos = Vector.divideByScale(pos, i);  //calculate average position in the surroundings of a boid by dividing the position vector by the number of boids in the surroundings

        return Vector.divideByScale(Vector.substractVector(pos, b.position), 100);  //scaling of change vector with a constant value
    }
}
