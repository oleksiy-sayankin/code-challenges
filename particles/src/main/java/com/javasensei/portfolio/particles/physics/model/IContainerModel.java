package com.javasensei.portfolio.particles.physics.model;

import com.javasensei.portfolio.math.MathDimension;
import com.javasensei.portfolio.math.IPolygon;

/**
 * @author oleksiy sayankin
 */
public interface IContainerModel {
    void setDimension(MathDimension newDimension);

    MathDimension getDimension();

    IPolygon getSides();
}
