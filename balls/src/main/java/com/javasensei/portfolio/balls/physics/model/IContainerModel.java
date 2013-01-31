package com.javasensei.portfolio.balls.physics.model;

import com.javasensei.portfolio.balls.math.IPolygon;
import com.javasensei.portfolio.balls.math.ISegment;

import java.util.List;

/**
 * @author asayankin
 */
public interface IContainerModel {
    double getWidth();
    double getHeight();
    void setWidthAndHeight(double newWidth, double newHeight) throws InterruptedException;
    IPolygon getSides();
}
