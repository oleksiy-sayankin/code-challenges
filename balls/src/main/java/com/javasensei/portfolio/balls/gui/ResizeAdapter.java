package com.javasensei.portfolio.balls.gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author asayankin
 */
public class ResizeAdapter extends ComponentAdapter{
    @Override
    public void componentResized(ComponentEvent e) {
        int x = e.getComponent().getWidth();
        int y = e.getComponent().getHeight();
        System.out.println("x: " + x);
        System.out.println("y: " + y);
    }
}
