package com.javasensei.portfolio.deepforest;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class SinIntervalAggregatorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SinIntervalDataProcessorTest.class);
    @Before
    public void createOutputDir(){
        PropertyConfigurator.configure("log4j.properties");
    }

    @Test
    public void freeSinIntervals001Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.5, Quadrant.FIRST)));
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(0.5, Quadrant.FIRST), new Sin(0.1, Quadrant.FIRST)));
        LOGGER.info(fsiActual.toString());
        Assert.assertEquals(fsiExpected, fsiActual);
    }

    @Test
    public void freeSinIntervals002Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.1, Quadrant.SECOND)));
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(0.1, Quadrant.SECOND), new Sin(0.1, Quadrant.FIRST)));
        LOGGER.info(fsiActual.toString());
        Assert.assertEquals(fsiExpected, fsiActual);
    }

    @Test
    public void freeSinIntervals003Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(-0.1, Quadrant.THIRD)));
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(-0.1, Quadrant.THIRD), new Sin(0.1, Quadrant.FIRST)));
        LOGGER.info(fsiActual.toString());
        Assert.assertEquals(fsiExpected, fsiActual);
    }

    @Test
    public void freeSinIntervals004Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(-0.1, Quadrant.FORTH), new Sin(0.1, Quadrant.FIRST)));
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(-0.1, Quadrant.FORTH)));
        LOGGER.info(fsiActual.toString());
        Assert.assertEquals(fsiExpected, fsiActual);
    }


    @Test
    public void freeSinIntervals005Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(-0.1, Quadrant.FORTH), new Sin(0.1, Quadrant.FIRST)));
        sia.add(new SinInterval(new Sin(-0.4, Quadrant.FORTH), new Sin(0.4, Quadrant.FIRST)));
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(0.4, Quadrant.FIRST), new Sin(-0.4, Quadrant.FORTH)));
        LOGGER.info(fsiActual.toString());
        Assert.assertEquals(fsiExpected, fsiActual);
    }


    @Test
    public void freeSinIntervals006Test(){
        SinIntervalAggregator sia = new SinIntervalAggregator();
        sia.add(new SinInterval(new Sin(-0.4, Quadrant.FORTH), new Sin(0.5, Quadrant.FIRST)));
        sia.add(new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.8, Quadrant.FIRST)));
        List<SinInterval> fsiActual = sia.freeSinIntervals();
        List<SinInterval> fsiExpected = new ArrayList<SinInterval>();
        fsiExpected.add(new SinInterval(new Sin(0.8, Quadrant.FIRST), new Sin(-0.4, Quadrant.FORTH)));
        LOGGER.info(fsiActual.toString());
        Assert.assertEquals(fsiExpected, fsiActual);
    }

}
