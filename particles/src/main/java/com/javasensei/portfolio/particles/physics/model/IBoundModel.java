package com.javasensei.portfolio.particles.physics.model;

import com.javasensei.portfolio.math.IPoint;
import com.javasensei.portfolio.math.IPolygon;
import com.javasensei.portfolio.math.IRectangle;
import com.javasensei.portfolio.math.IVector;
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
