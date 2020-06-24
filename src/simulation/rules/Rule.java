package simulation.rules;

import simulation.basics.Vector;
import simulation.screenobjects.Boid;

import java.util.ArrayList;

public abstract class Rule {

    /**
     * Importance of the rule compared to other rules.
     */
    public double weight;

    /**
     * Initializes a new rule with a weight.
     * @param weight weight of that rule (factor)
     */
    public Rule(double weight) {
        this.weight = weight;
    }

    /**
     * Sets the weight of a rule to a new value.
     * @param weight new weight of rule
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets the amount of change to the velocity of a boid scaled by the importance of a rule, when a rule is applied.
     * See also "Vector change(Boid b, ArrayList<Boid> flock)"
     *
     * @param b the boid of which the change amount for that rule should be calculated.
     * @param flock a flock of boids
     * @return the change vector in order to comply to the rule multiplied by the weight
     */
    public Vector getChange(Boid b, ArrayList<Boid> flock) {
        return Vector.multiplyByScale(change(b, flock), weight);
    }

    /**
     * Returns a vector (change of velocity) in order to comply to the rule.
     *
     * @param b the boid of which the change amount for that rule should be calculated.
     * @param flock a flock of boids
     * @return change of velocity
     */
    public abstract Vector change(Boid b, ArrayList<Boid> flock);

}
