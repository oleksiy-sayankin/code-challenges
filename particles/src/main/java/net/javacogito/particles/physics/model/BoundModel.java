package net.javacogito.particles.physics.model;

import net.javacogito.math.*;
import net.javacogito.particles.physics.state.BoundsState;

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
        return Primitives.unmodifiablePolygon(sides);
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
