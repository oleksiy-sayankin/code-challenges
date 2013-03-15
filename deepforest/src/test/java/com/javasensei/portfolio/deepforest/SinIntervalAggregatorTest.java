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

    @Test
    public void freeSinIntervals002Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.1, Quadrant.SECOND)));
        System.out.println("---");
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(0.1, Quadrant.SECOND), new Sin(0.1, Quadrant.FIRST)));
        System.out.println("---");
        System.out.println(fsiActual);
        Assert.assertEquals(fsiExpected, fsiActual);
    }

    @Test
    public void freeSinIntervals003Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(-0.1, Quadrant.THIRD)));
        System.out.println("---");
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(-0.1, Quadrant.THIRD), new Sin(0.1, Quadrant.FIRST)));
        System.out.println("---");
        System.out.println(fsiActual);
        Assert.assertEquals(fsiExpected, fsiActual);
    }

    @Test
    public void freeSinIntervals004Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(-0.1, Quadrant.FORTH), new Sin(0.1, Quadrant.FIRST)));
        System.out.println("---");
        System.out.println(sia);
        System.out.println("---");
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(-0.1, Quadrant.FORTH)));
        System.out.println("---");
        System.out.println(fsiActual);
        Assert.assertEquals(fsiExpected, fsiActual);
    }


    @Test
    public void freeSinIntervals005Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(-0.1, Quadrant.FORTH), new Sin(0.1, Quadrant.FIRST)));
        sia.add(new SinInterval(new Sin(-0.4, Quadrant.FORTH), new Sin(0.4, Quadrant.FIRST)));
        System.out.println("---");
        System.out.println(sia);
        System.out.println("---");
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(0.4, Quadrant.FIRST), new Sin(-0.4, Quadrant.FORTH)));
        System.out.println("---");
        System.out.println(fsiActual);
        Assert.assertEquals(fsiExpected, fsiActual);
    }


    @Test
    public void freeSinIntervals006Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(-0.4, Quadrant.FORTH), new Sin(0.5, Quadrant.FIRST)));
        sia.add(new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.8, Quadrant.FIRST)));
        System.out.println("---");
        System.out.println(sia);
        System.out.println("---");
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(0.8, Quadrant.FIRST), new Sin(-0.4, Quadrant.FORTH)));
        System.out.println("---");
        System.out.println(fsiActual);
        Assert.assertEquals(fsiExpected, fsiActual);
    }

}
