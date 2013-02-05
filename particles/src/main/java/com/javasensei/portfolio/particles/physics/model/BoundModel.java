package com.javasensei.portfolio.particles.physics.model;

import com.javasensei.portfolio.particles.math.*;
import com.javasensei.portfolio.particles.physics.state.BoundsState;

/**
 * @author oleksiy sayankin
 */
public class BoundModel implements IBoundModel {
    private final IPolygon sides;

    BoundModel(MathDimension dimension) {
        sides = initRectanglePolygon(dimension);
    }

    @Override
    public IRectangle bounds() {
        return sides.bounds();
    }

    @Override
    public void stretch(IPoint stretchPoint, IVector direction) {
        sides.stretch(stretchPoint, direction);
    }

    @Override
    public IPolygon getSides() {
        return MathHelper.unmodifiablePolygon(sides);
    }

    @Override
    public BoundsState state() {
        return new BoundsState(sides);
    }

    private static IPolygon initRectanglePolygon(MathDimension dimension) {
        IPolygon rect = new Polygon();
        rect.addPoint(new Point(0, 0));
        rect.addPoint(new Point(0, dimension.height));
        rect.addPoint(new Point(dimension.width, dimension.height));
        rect.addPoint(new Point(dimension.width, 0));
        return rect;
    }
}
