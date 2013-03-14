package com.javasensei.portfolio.deepforest;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class SinIntervalAggregatorTest {

    @Test
    public void freeSinIntervals001Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.5, Quadrant.FIRST)));
        System.out.println("---");
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(0.5, Quadrant.FIRST), new Sin(0.1, Quadrant.FIRST)));
        System.out.println("---");
        System.out.println(fsiActual);
        Assert.assertEquals(fsiExpected, fsiActual);
    }
}
