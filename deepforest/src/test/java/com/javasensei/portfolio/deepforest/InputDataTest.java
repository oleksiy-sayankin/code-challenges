package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.deepforest.Circle;
import com.javasensei.portfolio.deepforest.Coord;
import com.javasensei.portfolio.deepforest.InputData;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;

/**
 * @author oleksiy sayankin
 */
public class InputDataTest {
    @Test
    public void readTest() throws Exception {
        String input = "i002.in";
        InputData inputData = new InputData();
        inputData.read(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input)));
        Coord expectedCoord = new Coord(0, 0);
        Coord actualCoord = inputData.getInitPos();
        List<Circle> actualList = inputData.getCircles();
        List<Circle> expectedList = new ArrayList<Circle>();
        expectedList.add(new Circle(new Coord(-2, 2), 2));
        expectedList.add(new Circle(new Coord(-2, -2), 2));
        expectedList.add(new Circle(new Coord(2, -2), 2));
        expectedList.add(new Circle(new Coord(2, 2), 2));
        Assert.assertEquals(expectedList, actualList);
        Assert.assertEquals(expectedCoord, actualCoord);
    }
}
