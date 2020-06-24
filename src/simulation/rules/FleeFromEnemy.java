package simulation.rules;

import simulation.Flock;
import simulation.basics.Vector;
import simulation.screenobjects.Boid;

import java.util.ArrayList;

public class FleeFromEnemy extends Rule {

    private final double DISTANCE;
    private final Flock ENEMY;

    /**
     * Initializes a new FleeFromEnemy Rule
     * @param distance distance in which enemies are detected
     * @param enemy flock of enemy boids
     * @param weight weight of the rule
     */
    public FleeFromEnemy(double distance, Flock enemy, double weight) {
        super(weight);
        DISTANCE = distance;
        ENEMY = enemy;
    }

    /**
     * Calculates a change vector in order to flee from an enemy boid.
     *
     * @param b the boid of which the change amount for that rule should be calculated
     * @param flock a flock of boids
     * @return a change vector in order to flee from an enemy boid
     */
    @Override
    public Vector change(Boid b, ArrayList<Boid> flock) {
        Vector change = new Vector(0,0);

        for (Boid enem : ENEMY.getBoidsInFlock()) {     //test which enemies are in the surroundings
            if (enem != b) {
                Vector d = Vector.substractVector(enem.position, b.position);  //get the vector between the two boids
                double distanceBetween = Vector.magnitude(d);   //calculate the distance between them
                if (distanceBetween < DISTANCE) {
                    change = Vector.substractVector(change, d);   //if the distance is smaller than specified, calculate the change vector
                }
            }
        }

        return change;
    }
}
