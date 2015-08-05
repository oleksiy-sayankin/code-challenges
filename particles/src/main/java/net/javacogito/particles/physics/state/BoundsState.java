package net.javacogito.particles.physics.state;

import net.javacogito.math.IPolygon;
import net.javacogito.math.Primitives;

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
