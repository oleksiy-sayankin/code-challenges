package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.Arc;
import com.javasensei.portfolio.math.IArc;
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
        agg.add(new Arc(0, 0.5));
        List<IArc> expectedFreeSegments = new ArrayList<IArc>();
        expectedFreeSegments.add(new Arc(0.5, 2 * Math.PI));
        List<IArc> actualFreeSegments = agg.freeArcs();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }

    @Test
    public void freeSegmentsSimple2Test(){
        Aggregator agg = new Aggregator();
        agg.add(new Arc(0, 0.5));
        agg.add(new Arc(1, 2));
        List<IArc> expectedFreeSegments = new ArrayList<IArc>();
        expectedFreeSegments.add(new Arc(0.5, 1));
        expectedFreeSegments.add(new Arc(2, 2 * Math.PI));
        List<IArc> actualFreeSegments = agg.freeArcs();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }


    @Test
    public void freeSegmentsSimple3Test(){
        Aggregator agg = new Aggregator();
        agg.add(new Arc(1, 2.5));
        agg.add(new Arc(1.5, 2));
        List<IArc> expectedFreeSegments = new ArrayList<IArc>();
        expectedFreeSegments.add(new Arc(2.5, 1 + 2 * Math.PI));
        List<IArc> actualFreeSegments = agg.freeArcs();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }


    @Test
    public void freeSegmentsSimple4Test(){
        Aggregator agg = new Aggregator();
        Arc cs = new Arc(6, 7);
        cs.normalize();
        agg.add(new Arc(6, 7));
        List<IArc> expectedFreeSegments = new ArrayList<IArc>();
        expectedFreeSegments.add(new Arc(7 - 2 * Math.PI, 6));
        List<IArc> actualFreeSegments = agg.freeArcs();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }

    @Test
    public void freeSegmentsSimple5Test(){
        Aggregator agg = new Aggregator();
        Arc cs = new Arc(6, 5 + 2 * Math.PI);
        cs.normalize();
        //System.out.println("norm = " + cs);
        agg.add(new Arc(cs));
        List<IArc> expectedFreeSegments = new ArrayList<IArc>();
        expectedFreeSegments.add(new Arc(5, 6));
        List<IArc> actualFreeSegments = agg.freeArcs();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }

    @Test
    public void freeSegmentsSimple6Test(){
        Aggregator agg = new Aggregator();
        agg.add(new Arc(6, 7));
        agg.add(new Arc(2, 3));
        List<IArc> expectedFreeSegments = new ArrayList<IArc>();
        expectedFreeSegments.add(new Arc(7 - 2 * Math.PI, 2));
        expectedFreeSegments.add(new Arc(3, 6));
        List<IArc> actualFreeSegments = agg.freeArcs();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }


    @Test
    public void freeSegmentsSimple7Test(){
        Aggregator agg = new Aggregator();
        Arc cs = new Arc(6, 7);
        cs.normalize();
        //System.out.println("norm = " + cs);
        agg.add(new Arc(0, 2));
        agg.add(new Arc(6, 7));
        List<IArc> expectedFreeSegments = new ArrayList<IArc>();
        expectedFreeSegments.add(new Arc(2, 6));
        List<IArc> actualFreeSegments = agg.freeArcs();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }


    @Test
    public void freeSegmentsTest(){
        Aggregator agg = new Aggregator();
        agg.add(new Arc(0, 0.5));
        agg.add(new Arc(0.6, 1));
        agg.add(new Arc(1.1, 2));
        agg.add(new Arc(1.05, 1.5));
        agg.add(new Arc(6, 7));
        List<IArc> expectedFreeSegments = new ArrayList<IArc>();
        expectedFreeSegments.add(new Arc(0.5, 0.6));
        expectedFreeSegments.add(new Arc(1.0, 1.05));
        expectedFreeSegments.add(new Arc(2.0, 6.0));
        List<IArc> actualFreeSegments = agg.freeArcs();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }
}
