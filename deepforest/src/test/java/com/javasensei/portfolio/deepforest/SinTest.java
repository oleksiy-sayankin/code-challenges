package com.javasensei.portfolio.deepforest;


import org.junit.Test;
import org.junit.Assert;

/**
 * @author oleksiy sayankin
 */
public class SinTest {
    @Test
    public void compareTo001Test(){
        Sin a = new Sin(0.34323, Quadrant.FIRST);
        Sin b = new Sin(0.44323, Quadrant.FIRST);
        int expectedCompareTo = -1;
        int actualCompareTo = a.compareTo(b);
        Assert.assertEquals(expectedCompareTo, actualCompareTo);
    }

    @Test
    public void compareTo002Test(){
        Sin a = new Sin(0.34323, Quadrant.FIRST);
        Sin b = new Sin(0.44323, Quadrant.SECOND);
        int expectedCompareTo = -1;
        int actualCompareTo = a.compareTo(b);
        Assert.assertEquals(expectedCompareTo, actualCompareTo);
    }

    @Test
    public void compareTo003Test(){
        Sin a = new Sin(-0.34323, Quadrant.THIRD);
        Sin b = new Sin(-0.44323, Quadrant.THIRD);
        int expectedCompareTo = -1;
        int actualCompareTo = a.compareTo(b);
        Assert.assertEquals(expectedCompareTo, actualCompareTo);
    }

    @Test
    public void innerSin001Test(){
        SinInterval sinInterval = new SinInterval(new Sin(0, Quadrant.FIRST), new Sin(1, Quadrant.SECOND));
        Sin actualInnerSin = sinInterval.innerSin();
        Sin expectedInnerSin = new Sin(0.5, Quadrant.FIRST);
        Assert.assertEquals(expectedInnerSin, actualInnerSin);
    }

}
