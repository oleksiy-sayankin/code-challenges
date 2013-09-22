package com.javacogito.portfolio.particles.physics.state;

import com.javacogito.portfolio.math.IPolygon;
import com.javacogito.portfolio.math.Primitives;

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
