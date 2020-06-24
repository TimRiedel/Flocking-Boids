package simulation.basics.listener;

import simulation.basics.Screen;
import simulation.basics.Vector;
import simulation.screenobjects.Barrier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class BarrierClickListener extends MouseAdapter {

    private Screen screen;

    public BarrierClickListener(Screen screen) {
        this.screen = screen;
    }

    /**
     * Adds a barrier, when left mouse button is clicked.
     * Removes a barrier, when right mouse button is clicked.
     *
     * {@inheritDoc}
     * @param e mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            addBarrier(e);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            removeBarrier(e);
        }
    }

    //adds a barrier to the screen
    private void addBarrier(MouseEvent e) {
        int radius = screen.getBarrierDiameter() / 2; //radius is half of diameter

        Vector pos = new Vector(screen.toWorldPixelX(e.getX() - radius), screen.toWorldPixelY(e.getY())); //position is mouse position transferred to world position

        screen.addBarrier(new Barrier(pos, radius, Color.RED)); //adds a new barrier with position, radius and red color
    }

    //removes a barrier from the screen
    private void removeBarrier(MouseEvent e) {
        int radius = screen.getBarrierDiameter() / 2; //radius is half of diameter

        //check all barriers on the screen, whether their position is near to the mouse position
        //and if so remove the barrier
        for (Barrier b : screen.getAllBarriers()) {
            Vector mousePosition = new Vector(screen.toWorldPixelX(e.getX() - radius), screen.toWorldPixelY(e.getY()));
            Vector d = Vector.substractVector(mousePosition, b.position);
            double distanceBetween = Vector.magnitude(d);
            if (distanceBetween <= radius) {
                screen.removeBarrier(b);
            }
        }
    }
}
