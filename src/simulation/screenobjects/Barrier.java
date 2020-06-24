package simulation.screenobjects;

import simulation.basics.Screen;
import simulation.basics.Vector;

import java.awt.*;

public class Barrier extends ScreenObject {

    private int diameter;
    private Color color;


    /**
     * Initializes a new Barrier on a specific position, with diameter and color.
     *
     * @param position vector of the position.
     * @param diameter diameter of barrier on screen.
     * @param color color of barrier.
     */
    public Barrier(Vector position, int diameter, Color color) {
        super(position, new Vector(0,0));
        this.diameter = diameter;
        this.color = color;
    }

    /**
     * Draws the barrier on the screen as a filled circle.
     *
     * @param screen the screen on which the barrier will be displayed.
     * @param graphics2D the graphics2D that are used to draw the shape.
     * @param color the color of the barrier.
     */
    public void draw(Screen screen, Graphics2D graphics2D, Color color) {
        graphics2D.setColor(color);
        graphics2D.fillOval(screen.toScreenPixelX((int) position.x + diameter/2), screen.toScreenPixelY((int) position.y + diameter/2), diameter, diameter );
    }

    /**
     * Returns the diameter of the barrier.
     *
     * @return diameter of barrier.
     */
    public int getDiameter() {
        return diameter;
    }

    /**
     * Sets the diameter of barrier.
     *
     * @param diameter the new diameter
     */
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    /**
     * Returns the color of the barrier.
     * @return
     */
    public Color getColor() {
        return color;
    }
}
