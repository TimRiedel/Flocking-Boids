package simulation.rules;

import simulation.basics.Vector;
import simulation.screenobjects.Boid;

import java.util.ArrayList;

public class KeepInBounds extends Rule {

    private final int MIN_X, MAX_X, MIN_Y, MAX_Y, RADIUS;
    private final double RETURN_SPEED;
    private final boolean MOVE_THROUGH;

    /**
     * Initializes a new KeepInBounds Rule.
     *
     * @param min_x minimum x-coordinate for bounds
     * @param max_x maximum x-coordinate for bounds
     * @param min_y minimum y-coordinate for bounds
     * @param max_y maximum y-coordinate for bounds
     * @param radius radius of bound detection
     * @param returnSpeed returnSpeed when changing velocity because of a close boundary
     * @param moveThrough whether a boid can move through the walls (appear on other side of screen) or not
     * @param weight weight of the rule
     */
    public KeepInBounds(int min_x, int max_x, int min_y, int max_y, int radius, double returnSpeed, boolean moveThrough, double weight) {
        super(weight);
        MIN_X = min_x;
        MAX_X = max_x;
        MIN_Y = min_y;
        MAX_Y = max_y;
        RADIUS = radius;
        RETURN_SPEED = returnSpeed;
        MOVE_THROUGH = moveThrough;
    }

    /**
     * Calculates the change vector in order to keep in the bounds (change direction) or move through the bounds (appear on other side of screen).
     *
     * @param b the boid of which the change amount for that rule should be calculated.
     * @param flock a flock of boids
     * @return a change vector in order to keep within the bounds
     */
    @Override
    public Vector change(Boid b, ArrayList<Boid> flock) {
        Vector v = new Vector(0,0);

        if (MOVE_THROUGH) {     //if the boids in a flock can move through the boundaries (appear on other side of screen)
            if (b.position.x > MAX_X) b.position.x = MIN_X;  //make it appear on the left, if position is too far right
            else if (b.position.x < MIN_X) b.position.x = MAX_X;  //make it appear on the right, if position is too far left

            if (b.position.y > MAX_Y) b.position.y = MIN_Y;  //make it appear on the top, if position is too far down
            else if (b.position.y < MIN_Y) b.position.y = MAX_Y;  //make it appear on the bottom, if position is too far up
        } else {

            //reverse direction if too close to a boundary
            if (b.position.x + RADIUS > MAX_X) v.x = -RETURN_SPEED;
            else if (b.position.x - RADIUS < MIN_X) v.x = RETURN_SPEED;

            if (b.position.y + RADIUS > MAX_Y) v.y = -RETURN_SPEED;
            else if (b.position.y - RADIUS < MIN_Y) v.y = RETURN_SPEED;
        }


        return v;
    }
}
