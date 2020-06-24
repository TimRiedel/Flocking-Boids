package simulation.rules;

import simulation.basics.Vector;
import simulation.screenobjects.Boid;

import java.util.ArrayList;

public class MatchVelocity extends Rule{

    private final double DISTANCE;

    /**
     * Initializes a new MatchVelocity Rule.
     *
     * @param distance distance in which the velocity of one boid should match the velocity of surrounding boids
     * @param weight weight of rule
     */
    public MatchVelocity(double distance, double weight) {
        super(weight);
        DISTANCE = distance;
    }

    /**
     * Calculates a change vector to match the velocity of other boids.
     *
     * @param b the boid of which the change amount for that rule should be calculated
     * @param flock a flock of boids
     * @return a change vector, which tries to match the velocity of other boids
     */
    @Override
    public Vector change(Boid b, ArrayList<Boid> flock) {
        Vector vel = b.velocity;

        int i = 1;
        for (Boid otherBoid : flock) {

            if (b != otherBoid) {
                Vector d = Vector.substractVector(otherBoid.velocity, vel);  //Vector between tested boid and other boid
                double distanceBetweenBoids = Vector.magnitude(d);   //distance between two boids

                if (distanceBetweenBoids < DISTANCE) {
                    vel = Vector.addVector(vel, otherBoid.velocity); //all velocities within distance added together
                    i++;   //increment number of boids in the surroundings
                }
            }
        }
        vel = Vector.divideByScale(vel, i);   //calculate average velocity in the surroundings of a boid by dividing the velocity vector by the number of boids in the surroundings

        return Vector.divideByScale(Vector.substractVector(vel, b.velocity), 8);   //scaling of change vector with a constant value
    }
}
