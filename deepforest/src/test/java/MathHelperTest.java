import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
/**
 * @author oleksiy sayankin
 */
public class MathHelperTest {
    @Test
    public void minCircleContainingAllTest(){
        List<Circle> circles = new ArrayList<Circle>();
        double radius = 3d;
        double offset = 4;
        circles.add(new Circle(new Coord(offset, offset), radius));
        circles.add(new Circle(new Coord(offset, -offset), radius));
        circles.add(new Circle(new Coord(-offset, -offset), radius));
        circles.add(new Circle(new Coord(-offset, offset), radius));
        Coord center = new Coord(0, 0);
        double expectedRadius = Math.sqrt(offset * offset +  offset * offset) + radius;
        Circle expectedCircle = new Circle(center, expectedRadius);
        Circle actualCircle = MathHelper.minCircleContainingAll(circles, center);
        Assert.assertEquals(expectedCircle, actualCircle);
    }

    @Test
    public void shadowTest(){
        Circle circle = new Circle(new Coord(5, 5), 5);
        Coord coord = new Coord(0, 0);
        CircleSegment expectedSegment = new CircleSegment(0, Math.PI / 2);
        CircleSegment actualSegment = MathHelper.shadow(circle, coord);
        Assert.assertEquals(expectedSegment, actualSegment);
    }

    @Test
    public void shadowCoplexTest(){
        double radius = 7;
        double h = radius / Math.sin(Math.PI / 12);
        double x = h * Math.sin(Math.PI / 4);
        double y = - h * Math.sin(Math.PI / 4);
        Circle circle = new Circle(new Coord(x, y), radius);
        Coord coord = new Coord(0, 0);
        CircleSegment expectedSegment = new CircleSegment((2 * Math.PI / 12) * 10,  (2 * Math.PI / 12) * 11);
        CircleSegment actualSegment = MathHelper.shadow(circle, coord);
        Assert.assertEquals(expectedSegment, actualSegment);
    }

}
