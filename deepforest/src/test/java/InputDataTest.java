import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author oleksiy sayankin
 */
public class InputDataTest {
    @Test
    public void readTest() throws Exception {
        InputData.getInstance().read(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("i001.in")));
        Coord expectedCoord = new Coord(0, 0);
        Coord actualCoord = InputData.getInstance().getInitPos();
        List<Circle> actualList = InputData.getInstance().getCircles();
        List<Circle> expectedList = new ArrayList<Circle>();
        expectedList.add(new Circle(new Coord(-2, 2), 2));
        expectedList.add(new Circle(new Coord(-2, -2), 2));
        expectedList.add(new Circle(new Coord(2, -2), 2));
        expectedList.add(new Circle(new Coord(2, 2), 2));
        Assert.assertEquals(expectedList, actualList);
        Assert.assertEquals(expectedCoord, actualCoord);
    }
}
