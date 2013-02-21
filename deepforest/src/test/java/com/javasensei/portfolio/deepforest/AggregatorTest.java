package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.deepforest.Aggregator;
import com.javasensei.portfolio.deepforest.CircleSegment;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class AggregatorTest {

    @Test
    public void freeSegmentsSimpleTest(){
        Aggregator agg = new Aggregator();
        agg.add(new CircleSegment(0, 0.5));
        List<CircleSegment> expectedFreeSegments = new ArrayList<CircleSegment>();
        expectedFreeSegments.add(new CircleSegment(0.5, 2 * Math.PI));
        List<CircleSegment> actualFreeSegments = agg.freeSegments();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }

    @Test
    public void freeSegmentsSimple2Test(){
        Aggregator agg = new Aggregator();
        agg.add(new CircleSegment(0, 0.5));
        agg.add(new CircleSegment(1, 2));
        List<CircleSegment> expectedFreeSegments = new ArrayList<CircleSegment>();
        expectedFreeSegments.add(new CircleSegment(0.5, 1));
        expectedFreeSegments.add(new CircleSegment(2, 2 * Math.PI));
        List<CircleSegment> actualFreeSegments = agg.freeSegments();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }


    @Test
    public void freeSegmentsSimple3Test(){
        Aggregator agg = new Aggregator();
        agg.add(new CircleSegment(1, 2.5));
        agg.add(new CircleSegment(1.5, 2));
        List<CircleSegment> expectedFreeSegments = new ArrayList<CircleSegment>();
        expectedFreeSegments.add(new CircleSegment(2.5, 1 + 2 * Math.PI));
        List<CircleSegment> actualFreeSegments = agg.freeSegments();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }


    @Test
    public void freeSegmentsSimple4Test(){
        Aggregator agg = new Aggregator();
        CircleSegment cs = new CircleSegment(6, 7);
        cs.normalize();
        //System.out.println("norm = " + cs);
        agg.add(new CircleSegment(6, 7));
        List<CircleSegment> expectedFreeSegments = new ArrayList<CircleSegment>();
        expectedFreeSegments.add(new CircleSegment(7 - 2 * Math.PI, 6));
        List<CircleSegment> actualFreeSegments = agg.freeSegments();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }

    @Test
    public void freeSegmentsSimple5Test(){
        Aggregator agg = new Aggregator();
        CircleSegment cs = new CircleSegment(6, 5 + 2 * Math.PI);
        cs.normalize();
        //System.out.println("norm = " + cs);
        agg.add(new CircleSegment(cs));
        List<CircleSegment> expectedFreeSegments = new ArrayList<CircleSegment>();
        expectedFreeSegments.add(new CircleSegment(5, 6));
        List<CircleSegment> actualFreeSegments = agg.freeSegments();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }

    @Test
    public void freeSegmentsSimple6Test(){
        Aggregator agg = new Aggregator();
        agg.add(new CircleSegment(6, 7));
        agg.add(new CircleSegment(2, 3));
        List<CircleSegment> expectedFreeSegments = new ArrayList<CircleSegment>();
        expectedFreeSegments.add(new CircleSegment(7 - 2 * Math.PI, 2));
        expectedFreeSegments.add(new CircleSegment(3, 6));
        List<CircleSegment> actualFreeSegments = agg.freeSegments();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }


    @Test
    public void freeSegmentsSimple7Test(){
        Aggregator agg = new Aggregator();
        CircleSegment cs = new CircleSegment(6, 7);
        cs.normalize();
        //System.out.println("norm = " + cs);
        agg.add(new CircleSegment(0, 2));
        agg.add(new CircleSegment(6, 7));
        List<CircleSegment> expectedFreeSegments = new ArrayList<CircleSegment>();
        expectedFreeSegments.add(new CircleSegment(2, 6));
        List<CircleSegment> actualFreeSegments = agg.freeSegments();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }


    @Test
    public void freeSegmentsTest(){
        Aggregator agg = new Aggregator();
        agg.add(new CircleSegment(0, 0.5));
        agg.add(new CircleSegment(0.6, 1));
        agg.add(new CircleSegment(1.1, 2));
        agg.add(new CircleSegment(1.05, 1.5));
        agg.add(new CircleSegment(6, 7));
        List<CircleSegment> expectedFreeSegments = new ArrayList<CircleSegment>();
        expectedFreeSegments.add(new CircleSegment(0.5, 0.6));
        expectedFreeSegments.add(new CircleSegment(1.0, 1.05));
        expectedFreeSegments.add(new CircleSegment(2.0, 6.0));
        List<CircleSegment> actualFreeSegments = agg.freeSegments();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }
}
