package simulation.screenobjects;

import simulation.basics.Screen;
import simulation.basics.Vector;

import java.awt.*;

public class DirectionalTriangle extends ScreenObject {

    private Vector[] corners;   //stores positions of all corners
    public static final int cornerCount = 3;

    /**
     * Initializes a new directional triangle.
     *
     * @param position the origin position of the triangle
     * @param direction the direction to which the triangle is pointing
     * @param height the height of the triangle
     * @param baseWidth the base width of the triangle
     */
    public DirectionalTriangle(Vector position, Vector direction, double height, double baseWidth) {
        super(position, direction);
        this.corners = calculateCorners(height, baseWidth);
    }

    private Vector[] calculateCorners(double height, double baseWidth) {
        Vector[] corn = new Vector[3];

        //first corner (top) is half of the height away from the origin position in the pointing direction
        Vector halfHeight = Vector.multiplyByScale(Vector.divideByScale(velocity, Vector.magnitude(velocity)), height/2);
        Vector corner1 = Vector.addVector(position, halfHeight);

        //the middle of the base is half of the height away from the origin position (rotated by 180 degrees)
        Vector baseOrigin = Vector.addVector(position, Vector.rotateAnticlockwise(halfHeight, 180));

        //calculates a vector, whichs magnitude is half of the base
        Vector halfBase = Vector.multiplyByScale(Vector.divideByScale(velocity, Vector.magnitude(velocity)), baseWidth/2);

        //b1 is half base rotated by 90 degrees, which is then added to the base origin to give the second corner
        Vector b1 = Vector.rotateAnticlockwise(halfBase, 90);
        Vector corner2 = Vector.addVector(baseOrigin, b1);

        //b2 is half base rotated by 270 degrees, which is then added to the base origin to give the third corner
        Vector b2 = Vector.rotateAnticlockwise(halfBase, 270);
        Vector corner3 = Vector.addVector(baseOrigin, b2);

        //add the corners to the corners array and return it
        corn[0] = corner1;
        corn[1] = corner2;
        corn[2] = corner3;
        return corn;
    }

    /**
     * Draws a directional triangle on a screen, by transferring the world coordinates to screen coordinates.
     *
     * @param screen the screen on which the triangle should be displayed
     * @param graphics2D the graphics used to paint the triangle
     */
    public void draw(Screen screen, Graphics2D graphics2D) {
        int[] pixelsX = new int[corners.length];    //stores x screen pixels
        int[] pixelsY = new int[corners.length];    //stores y screen pixels

        for (int i = 0; i < corners.length; i++) {
            //calculate the screen pixels from the world pixels
            pixelsX[i] = screen.toScreenPixelX(corners[i].x);
            pixelsY[i] = screen.toScreenPixelY(corners[i].y);
        }

        //draw the triangle based on the screen pixels arrays
        graphics2D.fillPolygon(pixelsX, pixelsY, DirectionalTriangle.cornerCount);
    }

}
