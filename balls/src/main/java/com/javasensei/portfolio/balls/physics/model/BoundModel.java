package com.javasensei.portfolio.balls.physics.model;

import com.javasensei.portfolio.balls.Constants;
import com.javasensei.portfolio.balls.math.*;

/**
 * @author asayankin
 */
public class BoundModel implements IBoundModel{
    private final IPolygon sides;

    BoundModel(){
        sides = initRectanglePolygon();
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
    public double width() {
        return sides.width();
    }

    @Override
    public double height() {
        return sides.height();
    }

    private static IPolygon initRectanglePolygon() {
        IPolygon rect = new Polygon();
        rect.addPoint(new Point(0, 0));
        rect.addPoint(new Point(0, Constants.Common.CONTAINER_HEIGHT));
        rect.addPoint(new Point(Constants.Common.CONTAINER_WIDTH, Constants.Common.CONTAINER_HEIGHT));
        rect.addPoint(new Point(Constants.Common.CONTAINER_WIDTH, 0));
        return rect;
    }
}
