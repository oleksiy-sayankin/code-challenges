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
    public void freeSegmentsTest(){
        Aggregator agg = new Aggregator();
        agg.add(new CircleSegment(0, 0.5));
        agg.add(new CircleSegment(0.6, 1));
        agg.add(new CircleSegment(1.1 + 2 * Math.PI, 2));
        agg.add(new CircleSegment(1.05, 1.5));
        agg.add(new CircleSegment(6, 7 + 2 * Math.PI));
        List<CircleSegment> expectedFreeSegments = new ArrayList<CircleSegment>();
        expectedFreeSegments.add(new CircleSegment(0.5, 0.6));
        expectedFreeSegments.add(new CircleSegment(1.0, 1.05));
        expectedFreeSegments.add(new CircleSegment(2.0, 6.0));
        List<CircleSegment> actualFreeSegments = agg.freeSegments();
        Assert.assertEquals(expectedFreeSegments, actualFreeSegments);
    }
}
