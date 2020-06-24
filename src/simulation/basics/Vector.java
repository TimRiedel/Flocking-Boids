package simulation.basics;

public class Vector {

    /**
     * X-Direction
     */
    public double x;
    /**
     * Y-Direction
     */
    public double y;

    /**
     * Initializes a new Vector
     * @param x x-direction
     * @param y y-direction
     */
    public Vector(double x, double y) {
        this.x = x;       //each Vector has a length in x direction
        this.y = y;       //each Vector has a length in y direction
    }

    /**
     * Adds two vectors together.
     *
     * @param vector1 first vector
     * @param vector2 second vector
     * @return a new vector based on the sum of the two vectors
     */
    public static Vector addVector(Vector vector1, Vector vector2) {
        double x = vector1.x + vector2.x;       //add lenght in x direction
        double y = vector1.y + vector2.y;       //add lenght in y direction
        return new Vector(x, y);
    }

    /**
     * Adds all vectors in a vector array together.
     *
     * @param vectors all vectors that should be added
     * @return a new vector based on the sum of all vectors
     */
    public static Vector addAllVectors(Vector[] vectors) {
        Vector resVector = new Vector(0,0);
        for (Vector v : vectors) {
            resVector = addVector(resVector, v);    //add all Vectors in the array to get one resulting vector
        }
        return resVector;
    }

    /**
     * Subtracts one vector from another.
     *
     * @param vector1 Minuend
     * @param vector2 Subtrahend
     * @return a new vector which is the difference between vector1 and vector2
     */
    public static Vector substractVector(Vector vector1, Vector vector2) {
        double x = vector1.x - vector2.x;
        double y = vector1.y - vector2.y;
        return new Vector(x, y);
    }

    /**
     * Multiplies a vector by a scale.
     *
     * @param vector the vector, that should be multiplied by the scale (factor)
     * @param scale factor
     * @return a new scaled vector
     */
    public static Vector multiplyByScale(Vector vector, double scale) {
        double x = vector.x * scale;
        double y = vector.y * scale;
        return new Vector(x, y);
    }

    /**
     * Divides a vector by a scale.
     *
     * @param vector the vector, that should be divided by the scale (dividend)
     * @param scale divisor
     * @return a new scaled vector
     */
    public static Vector divideByScale(Vector vector, double scale) {
        double x = vector.x / scale;
        double y = vector.y / scale;
        return new Vector(x, y);
    }

    /**
     * Returns the magnitude of a vector (its length).
     * @param vector the vector of which the magnitude should be determined
     * @return the length of the vector
     */
    public static double magnitude(Vector vector) {
        double mag = Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2));  //magnitude of vector = Sqrt(x^2 + y^2)
        return mag;
    }

    /**
     * Rotates a vector anti-clockwise by a specified number of degrees.
     *
     * @param vector the vector, which should be rotated
     * @param degrees amount of degrees for rotation
     * @return the resultant vector
     */
    public static Vector rotateAnticlockwise(Vector vector, double degrees) {
        if (degrees > 360 || degrees < 0) {     //limit the number of degrees to 360
            degrees = 0;
        }
        double radians = Math.toRadians(degrees);   //convert degrees to radians

        //formulas for new x and y coordinates of the resultant vector
        double xr = Math.cos(radians) * vector.x - Math.sin(radians) * vector.y;
        double yr = Math.sin(radians) * vector.x + Math.cos(radians) * vector.y;

        return new Vector(xr, yr);
    }

}
