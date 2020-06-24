package main;

import simulation.Flock;
import simulation.FlockManager;
import simulation.basics.Screen;
import simulation.rules.*;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This program simulates swarm behaviour of flocking boids.
 *
 * @author Tim Riedel
 *
 */
public class Driver {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

    /**
     * Creates a flock Manager and also a screen with the display properties.
     * Then it creates flocks (groups of boids with specific characteristics).
     * Specifies rules for each flock.
     * Adds flocks to the flock manager and takes care of updating the screen and the position of the boids.
     *
     * @param args Not used
     */
    public static void main(String[] args) {

        //Create a flock manager and also a screen with some properties for display
        FlockManager flockManager = new FlockManager();
        Screen screen = new Screen(WIDTH, HEIGHT, flockManager, false, 6, 80);

        //Create flocks
        Flock e1 = new Flock(2, -500, 500, -500, 500, 1, 10, Color.RED);
        Flock f1 = new Flock(1500, -500, 500, -500, 500, 1, 7, Color.CYAN);

        //defining all the rules for an enemy flock ("Predator")
        e1.addRule(new KeepInBounds(-500, 500, -500, 500, 20, 5, true, 10));
        e1.addRule(new KeepDistance(100, e1,1));
        e1.addRule(new AvoidObject(screen, 80, 3));
        e1.addRule(new AttackBoids(200, f1, 1));

        //defining all the rules for a boid flock ("Prey")
        f1.addRule(new KeepInBounds(-500, 500, -500, 500, 20, 5, true, 10));
        f1.addRule(new KeepDistance(20, f1, 1));
        f1.addRule(new KeepTowardCentre(200, 1));
        f1.addRule(new MatchVelocity(200, 1));
        f1.addRule(new AvoidObject(screen, 80, 3));
        f1.addRule(new FleeFromEnemy(250, e1, 1));

        //add the flocks to the flock manager for coordination
        flockManager.add(e1);
        flockManager.add(f1);

        //start and repeat a timer to update each boids position and repaint the screen
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                flockManager.update();
                screen.repaint();
            }
        }, 0, 30);

    }

}
