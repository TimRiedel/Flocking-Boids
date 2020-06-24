package simulation.basics;

import main.Driver;
import simulation.Flock;
import simulation.FlockManager;
import simulation.basics.listener.BarrierClickListener;
import simulation.screenobjects.Barrier;
import simulation.screenobjects.Boid;
import simulation.screenobjects.DirectionalTriangle;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Screen extends JPanel {

    private final FlockManager FLOCKMANAGER;
    private final int WIDTH;
    private final int HEIGHT;
    private final int BOID_HEIGHT;
    private final int BOID_WIDTH;
    private final boolean TRIANGLES;
    private int barrierDiameter;
    private List<Barrier> allBarriers = new LinkedList<>();

    /**
     * Initializes a new Screen.
     *
     * @param width width of the screen
     * @param height height of the screen
     * @param flockManager the flockManager that should be visualized on the screen
     * @param triangles true, if boids should be displayed as triangles; false, if the should be displayed as circles
     * @param boid_height height of boid
     * @param barrier_diameter diameter of barrier
     */
    public Screen(int width, int height, FlockManager flockManager, boolean triangles, int boid_height, int barrier_diameter) {
        WIDTH = width;
        HEIGHT = height;
        FLOCKMANAGER = flockManager;
        TRIANGLES = triangles;
        BOID_HEIGHT = boid_height;
        BOID_WIDTH = BOID_HEIGHT /2;
        barrierDiameter = barrier_diameter;

        JFrame frame = new JFrame("Flocking Boids");        //new JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0,0);

        this.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().add(this);
        frame.getContentPane().addMouseListener(new BarrierClickListener(this));

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Draws on the screen.
     * {@inheritDoc}
     *
     * @param g graphics that should be used to paing
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawAllBoids(graphics2D);
        drawAllBarriers(graphics2D);
    }

    private void drawAllBoids(Graphics2D graphics2D) {
        graphics2D.setColor(Color.darkGray);             //set background color
        graphics2D.fillRect(0,0, WIDTH, HEIGHT);  //fill screen with background color


        for (Flock flock : FLOCKMANAGER.getAllFlocks()) {   //for each flock
            graphics2D.setColor(flock.color());             //set the drawing color to color of this flock

            for (Boid b : flock.getBoidsInFlock()) {        //for each boid in this flock
                if (TRIANGLES) {
                    DirectionalTriangle triangle = new DirectionalTriangle(b.position, b.velocity, BOID_HEIGHT, BOID_WIDTH);    //draw a triangle
                    triangle.draw(this, graphics2D);
                } else {
                    graphics2D.fillOval(toScreenPixelX(b.position.x) + BOID_WIDTH, toScreenPixelY(b.position.y) + BOID_WIDTH, BOID_HEIGHT, BOID_HEIGHT); //draw a filled circle
                }
            }
        }
    }

    private void drawAllBarriers(Graphics2D graphics2D) {
        for (Barrier barrier : allBarriers) {               //for each barrier
            barrier.draw(this, graphics2D, Color.RED);   //draw it with color red
        }
    }

    /**
     * Adds a barrier, which should be displayed on the screen.
     *
     * @param barrier the barrier to be added
     */
    public void addBarrier(Barrier barrier) {
        allBarriers.add(barrier);
        this.repaint();
    }

    /**
     * Removes a barrier from the screen.
     *
     * @param barrier the barrier to be removed
     */
    public void removeBarrier(Barrier barrier) {
        allBarriers.remove(barrier);
        this.repaint();
    }

    /**
     * Returns a list of all barriers on the screen.
     *
     * @return a list of all barriers on the screen
     */
    public List<Barrier> getAllBarriers() {
        return allBarriers;
    }

    /**
     * Sets the diameter of a barrier.
     *
     * @param diameter diameter of barrier.
     */
    public void setBarrierDiameter(int diameter) {
        this.barrierDiameter = diameter;
    }

    /**
     * Returns the diameter of the barriers.
     *
     * @return the diameter of the barriers
     */
    public int getBarrierDiameter() {
        return barrierDiameter;
    }

    /**
     * Converts a world pixel x to a screen pixel x.
     *
     * @param worldX the world x coordinate to be converted
     * @return the screen pixel x coordinate
     */
    public int toScreenPixelX(double worldX) {
        double screenX = worldX + (this.getWidth() / 2);
        return (int) Math.round(screenX);
    }

    /**
     * Converts a world pixel y to a screen pixel y.
     *
     * @param worldY the world y coordinate to be converted
     * @return the screen pixel y coordinate
     */
    public int toScreenPixelY(double worldY) {
        double screenY = (this.getHeight() / 2) - worldY;
        return (int) Math.round(screenY);
    }

    /**
     * Converts a screen pixel x to a world pixel x.
     *
     * @param screenX the screen x coordinate to be converted
     * @return the world pixel x coordinate
     */
    public double toWorldPixelX(int screenX) {
        double worldX = screenX - (this.getWidth() / 2);
        return worldX;
    }

    /**
     * Converts a screen pixel y to a world pixel y.
     *
     * @param screenY the screen y coordinate to be converted
     * @return the world pixel y coordinate
     */
    public double toWorldPixelY(int screenY) {
        double worldY = (this.getHeight() / 2) - screenY;
        return worldY;
    }
}
