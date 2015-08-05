package net.javacogito.particles.physics.model;

import net.javacogito.math.IPoint;
import net.javacogito.math.IPolygon;
import net.javacogito.math.IRectangle;
import net.javacogito.math.IVector;
import net.javacogito.particles.physics.state.BoundsState;

/**
 * @author oleksiy sayankin
 */
public interface IBoundModel {
    IRectangle bounds();

    void stretch(IPoint stretchPoint, IVector direction);

    IPolygon getSides();

    BoundsState state();
}
