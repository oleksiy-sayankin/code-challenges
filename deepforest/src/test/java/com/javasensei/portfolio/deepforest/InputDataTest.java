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
        ICircle[] actualList = inputData.getCircles();
        ICircle[] expectedList = new ICircle[4];
        expectedList[0] = new Circle(new Point(-2, 2), 2);
        expectedList[1] = new Circle(new Point(-2, -2), 2);
        expectedList[2] = new Circle(new Point(2, -2), 2);
        expectedList[3] = new Circle(new Point(2, 2), 2);
        Assert.assertArrayEquals(expectedList, actualList);
        Assert.assertEquals(expectedPoint, actualPoint);
    }
}
