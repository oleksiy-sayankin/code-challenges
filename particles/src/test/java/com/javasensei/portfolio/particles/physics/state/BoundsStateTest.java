package com.javasensei.portfolio.particles.physics.state;

import com.javasensei.portfolio.particles.math.IPolygon;
import com.javasensei.portfolio.particles.math.Point;
import com.javasensei.portfolio.particles.math.Polygon;
import org.junit.Test;

/**
 * @author asayankin
 */
public class BoundsStateTest {
    @Test
    public void toStringTest(){
        IPolygon polygon = new Polygon();
        polygon.addPoint(new Point(0, 0));
        polygon.addPoint(new Point(0, 10));
        polygon.addPoint(new Point(20, 10));
        polygon.addPoint(new Point(20, 0));
        BoundsState bs = new BoundsState(polygon);
        System.out.println(bs);
    }

}
