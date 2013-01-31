package com.javasensei.portfolio.balls.physics.model;

import com.javasensei.portfolio.balls.math.IPoint;
import com.javasensei.portfolio.balls.math.IPolygon;
import com.javasensei.portfolio.balls.math.IRectangle;
import com.javasensei.portfolio.balls.math.IVector;

/**
 * @author asayankin
 */
public interface IBoundModel {
    IRectangle bounds();
    void stretch(IPoint stretchPoint, IVector direction);
    IPolygon getSides();
    double width();
    double height();
}
