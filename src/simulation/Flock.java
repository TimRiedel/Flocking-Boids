package simulation;

import simulation.basics.Vector;
import simulation.rules.Rule;
import simulation.screenobjects.Boid;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a Flock of Boids.
 */
public class Flock {

    private final ArrayList<Boid> FLOCK;     //a flock consists of many boids
    private final ArrayList<Rule> RULES;     //each flock follows a set of rules
    private double maxSpeed;                //maximum speed for all boids in the flock
    private Color color;                    //each flock has a different color

    /**
     * Initializes a new Flock with a random color.
     *
     * @param boidCount number of boids contained in this flock.
     * @param minX minimum x-coordinate, where a boid can be spawned.
     * @param maxX maximum x-coordinate, where a boid can be spawned.
     * @param minY minimum y-coordinate, where a boid can be spawned.
     * @param maxY maximum y-coordinate, where a boid can be spawned.
     * @param minSpeed minimum speed that a boid must have when spawned.
     * @param maxSpeed maximum speed that a boid can have when spawned.
     */

    public Flock(int boidCount, int minX, int maxX, int minY, int maxY, int minSpeed, int maxSpeed) {
        this.maxSpeed = maxSpeed;
        FLOCK = new ArrayList<>();
        RULES = new ArrayList<>();

        for (int i = 0; i < boidCount; i++) {        //fills the flock list with new Boids up until boidCount

            if (minSpeed == 0) minSpeed = 1;                //minimum speed can´t be 0

            Vector position = Boid.randomPlace(minX, maxX, minY, maxY);     //spawn at random place within the bounds
            Vector velocity = Boid.randomVelocity(minSpeed, maxSpeed);      //spawn with random velocity withing range
            Boid b = new Boid(position, velocity);
            FLOCK.add(b);   //adds the boid to the flock
        }

        this.color = new Color((int) (Math.random() * 200) + 55, (int) (Math.random() * 200) + 55, (int) (Math.random() * 200) + 55);

    }
    /**
     * Initializes a new Flock with a defined color.
     *
     * @param boidCount number of boids contained in this flock.
     * @param minX minimum x-coordinate, where a boid can be spawned.
     * @param maxX maximum x-coordinate, where a boid can be spawned.
     * @param minY minimum y-coordinate, where a boid can be spawned.
     * @param maxY maximum y-coordinate, where a boid can be spawned.
     * @param minSpeed minimum speed that a boid must have when spawned.
     * @param maxSpeed maximum speed that a boid can have when spawned.
     * @param color color of the flock.
     */
    public Flock(int boidCount, int minX, int maxX, int minY, int maxY, int minSpeed, int maxSpeed, Color color) {
        this(boidCount, minX, maxX, minY, maxY, minSpeed, maxSpeed);
        this.color = color;
    }

    /**
     * Returns the Color of the flock.
     * @return color of flock.
     */
    public Color color() {
        return this.color;
    }

    /**
     * Adds a rule to the rules List of the flock.
     * @param rule the rule to be added.
     */
    public void addRule(Rule rule) {
        RULES.add(rule);
    }

    //calculates the new velocity of the boid
    private void calculateVelocity(Boid b) {
        Vector change = new Vector(0,0);

        for (Rule rule : RULES) {       //iterate through all rules
            change = Vector.addVector(change, rule.getChange(b, FLOCK));    //calculates the change Vector, which complies to all rules
        }
        b.velocity = Vector.addVector(b.velocity, change);  //adds the change vector to the velocity

    }

    //limits the speed of the boid
    private void limitSpeed(Boid b) {
        //if boids speed is greater than maxSpeed
        if (Vector.magnitude(b.velocity) > maxSpeed) {

            //scale the velocity vector, so that its magnitude matches maxSpeed
            b.velocity = Vector.multiplyByScale(Vector.divideByScale(b.velocity, Vector.magnitude(b.velocity)), maxSpeed);
        }
    }

    /**
     * Applies rules to all boids by calculating a change vector,
     * which resembles the change to be made in order to comply with every rule.
     * Limits the speed of all boids.
     * Moves each boid to the new position.
     */
    public void update() {
        for (Boid b : FLOCK) {
            calculateVelocity(b);
            limitSpeed(b);
        }

        for (Boid b : FLOCK) b.move();
    }

    /**
     * Returns an ArrayList of all boids in the flock.
     * @return an ArrayList of boids.
     */
    public ArrayList<Boid> getBoidsInFlock() {
        return FLOCK;
    }

    /**
     * Gives the maximum speed that a boid in this flock can have.
     * @return the maximum speed.
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }
}
