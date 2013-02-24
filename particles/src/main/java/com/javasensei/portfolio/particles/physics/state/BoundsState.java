package com.javasensei.portfolio.particles.physics.state;

import com.javasensei.portfolio.math.IPolygon;
import com.javasensei.portfolio.math.Primitives;

/**
 * @author oleksiy sayankin
 */
public final class BoundsState {
    public final IPolygon sides;

    public BoundsState(IPolygon polygon) {
        sides = Primitives.unmodifiablePolygon(polygon);
    }

    @Override
    public String toString() {
        return sides.toString();
    }
}
