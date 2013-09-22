package com.javacogito.portfolio.particles.physics.model;

import com.javacogito.portfolio.math.IPoint;
import com.javacogito.portfolio.math.IPolygon;
import com.javacogito.portfolio.math.IRectangle;
import com.javacogito.portfolio.math.IVector;
import com.javacogito.portfolio.particles.physics.state.BoundsState;

/**
 * @author oleksiy sayankin
 */
public interface IBoundModel {
    IRectangle bounds();

    void stretch(IPoint stretchPoint, IVector direction);

    IPolygon getSides();

    BoundsState state();
}
