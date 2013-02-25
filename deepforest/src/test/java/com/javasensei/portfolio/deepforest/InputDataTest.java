package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.Circle;
import com.javasensei.portfolio.math.ICircle;
import com.javasensei.portfolio.math.IPoint;
import com.javasensei.portfolio.math.Point;
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
        IPoint expectedPoint = new Point(0, 0);
        IPoint actualPoint = inputData.getInitPos();
        List<ICircle> actualList = inputData.getCircles();
        List<ICircle> expectedList = new ArrayList<ICircle>();
        expectedList.add(new Circle(new Point(-2, 2), 2));
        expectedList.add(new Circle(new Point(-2, -2), 2));
        expectedList.add(new Circle(new Point(2, -2), 2));
        expectedList.add(new Circle(new Point(2, 2), 2));
        Assert.assertEquals(expectedList, actualList);
        Assert.assertEquals(expectedPoint, actualPoint);
    }
}
