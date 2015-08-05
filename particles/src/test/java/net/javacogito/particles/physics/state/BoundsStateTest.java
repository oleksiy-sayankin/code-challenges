package net.javacogito.particles.physics.state;

import net.javacogito.math.IPolygon;
import net.javacogito.math.Point;
import net.javacogito.math.Polygon;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author oleksiy sayankin
 */
public class BoundsStateTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoundsStateTest.class);

    @Before
    public void createOutputDir(){
        PropertyConfigurator.configure("log4j.properties");
    }

    @Test
    public void toStringTest() {
        IPolygon polygon = new Polygon();
        polygon.addPoint(new Point(0, 0));
        polygon.addPoint(new Point(0, 10));
        polygon.addPoint(new Point(20, 10));
        polygon.addPoint(new Point(20, 0));
        BoundsState bs = new BoundsState(polygon);
        LOGGER.info(bs.toString());
    }

}
