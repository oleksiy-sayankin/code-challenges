package com.javasensei.portfolio.particles.physics.model;

import com.javasensei.portfolio.particles.math.IPoint;
import com.javasensei.portfolio.particles.math.IPolygon;
import com.javasensei.portfolio.particles.math.IRectangle;
import com.javasensei.portfolio.particles.math.IVector;
import com.javasensei.portfolio.particles.physics.state.BoundsState;

/**
 * @author oleksiy sayankin
 */
public interface IBoundModel {
    IRectangle bounds();

    void stretch(IPoint stretchPoint, IVector direction);

    IPolygon getSides();

    BoundsState state();
}
