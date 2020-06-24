package simulation.screenobjects;

import simulation.basics.Vector;

public class Boid extends ScreenObject {

    public Boid(Vector position, Vector velocity) {
        super(position, velocity);
    }

    /**
     * Calculates a random place between the specified coordinates.
     *
     * @param minX minimum x value
     * @param maxX maximum x value
     * @param minY minimum y value
     * @param maxY maximum y value
     * @return a new position in form of a vector.
     */
    public static Vector randomPlace(int minX, int maxX, int minY, int maxY) {
        double posX = Math.random() * (maxX-minX) + minX;
        double posY = Math.random() * (maxY-minY) + minY;
        return new Vector(posX, posY);
    }

    /**
     * Calculates a random velocity between maximum and minimum speed.
     *
     * @param minSpeed minimum speed
     * @param maxSpeed maximum speed
     * @return a new velocity
     */
    public static Vector randomVelocity(double minSpeed, double maxSpeed) {
        double speedX = Math.random() * (maxSpeed-minSpeed) + minSpeed;
        double speedY = Math.random() * (maxSpeed-minSpeed) + minSpeed;

        Vector speed = new Vector(speedX, speedY);

        if (Vector.magnitude(speed) > maxSpeed) {       //if the random Velocity is greater than the max specified speed
            speed = Vector.multiplyByScale(Vector.divideByScale(speed, Vector.magnitude(speed)), maxSpeed);     //reduce the the vector (while keeping direction) until its magnitude matches maxSpeed
        }
        if (Vector.magnitude(speed) < minSpeed) {       //if the random Velocity is less than the min specified speed
            speed = Vector.multiplyByScale(Vector.divideByScale(speed, Vector.magnitude(speed)), minSpeed);     //scale the vector up to the min speed
        }

        return speed;
    }

    /**
     * Updates the position of the boid by adding its velocity to the position.
     */
    public void move() {
        position = Vector.addVector(position, velocity);
    }
}
