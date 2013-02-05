package com.javasensei.portfolio.particles.physics.model;

import com.javasensei.portfolio.particles.math.MathDimension;
import com.javasensei.portfolio.particles.math.IPolygon;

/**
 * @author asayankin
 */
public interface IContainerModel {
    public void setDimension(MathDimension newDimension);
    public MathDimension getDimension();
    IPolygon getSides();
}
