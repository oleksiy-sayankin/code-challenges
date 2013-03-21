package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class SinIntervalAggregator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SinIntervalAggregator.class);
    private SinInterval sinIntervals[];

    public SinIntervalAggregator(InputData inputData) {
        int circlesCount = inputData.getCirclesCount();
        sinIntervals = new SinInterval[circlesCount];
        int i = 0;
        for (ICircle circle : inputData.getCircles()) {
            sinIntervals[i] = sinInterval(circle, inputData.getInitPos());
            i++;
        }
    }

    public SinIntervalAggregator(int length){
        sinIntervals = new SinInterval[length];
    }

    public void add(SinInterval sinInterval) {
        int i = 0;
        while(sinIntervals[i] != null){
            if(i == sinIntervals.length - 1) {
                return;
            }
            i++;
        }

        sinIntervals[i] = sinInterval;
    }

//    public SinInterval freeSinInterval() {
//        if (sinIntervals.length == 0) {
//            return null;
//        }
//
//        Sin[] allAngles = allSin();
//        Sin start;
//        Sin end;
//        for (int i = 0; i <= allAngles.length - 2; i++) {
//            start = allAngles[i];
//            end = allAngles[i + 1];
//            SinInterval interval = new SinInterval(start, end);
//            if (isFreeAngle(interval)) {
//                return interval;
//            }
//        }
//        start = allAngles[allAngles.length - 1];
//        end = allAngles[0];
//        SinInterval lastInterval = new SinInterval(start, end);
//        if (isFreeAngle(lastInterval)) {
//            return lastInterval;
//        }
//        return null;
//    }


    public SinInterval freeSinInterval() {
        Arrays.sort(sinIntervals);
        if (sinIntervals.length == 0) {
            return null;
        }
        int index = 0;
        while (index <= sinIntervals.length - 2){
            Sin currentEndSin = sinIntervals[index].getEndSin();
            int nextIndex = next(index);
            Sin nextStartSin = sinIntervals[nextIndex].getStartSin();
            if(currentEndSin.compareTo(nextStartSin) < 0){
                return new SinInterval(currentEndSin.normalized(), nextStartSin.normalized());
            }
            index = nextIndex;
        }


        Sin start = getMaxEndSin().normalized();
        Sin end = sinIntervals[0].getStartSin().normalized();
        SinInterval lastInterval = new SinInterval(start, end);
        return lastInterval;
    }

    private Sin getMaxEndSin(){
        Sin maxEndSin = new Sin(0, Quadrant.FIRST);
        for(SinInterval sinInterval : sinIntervals){
            if(maxEndSin.compareTo(sinInterval.getEndSin()) < 0){
                maxEndSin = sinInterval.getEndSin();
            }
        }
        return maxEndSin;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        for (SinInterval sinInterval : sinIntervals) {
            if (first) {
                first = false;
            } else {
                sb.append("\n");
            }
            sb.append(sinInterval);
        }
        return sb.toString();
    }

    private Sin[] allSin() {
        Sin[] allSin = new Sin[sinIntervals.length * 2];
        int i = 0;
        for (SinInterval sinInterval : sinIntervals) {
            allSin[i] = sinInterval.getStartSin().normalized();
            i++;
            allSin[i] = sinInterval.getEndSin().normalized();
            i++;
        }

        long startTime = new Date().getTime();
        Arrays.sort(allSin);
        long endTime = new Date().getTime();
        LOGGER.info("sort duration = "  + (endTime - startTime) + " ms for size = " + allSin.length);
        return allSin;
    }

    private boolean isFreeAngle(SinInterval interval) {
        Sin innerSin = interval.innerSin();
        for (SinInterval sinInterval : sinIntervals) {
            if (sinInterval.contains(innerSin))
                return false;
        }
        return true;
    }

    private static SinInterval sinInterval(ICircle circle, IPoint point) {
        double h = circle.getCenter().distanceTo(point);
        if (MathHelper.equalsZero(h) || h <= circle.getRadius()) {
            return null;
        }
        double x = circle.getCenter().getX() - point.getX();
        double y = circle.getCenter().getY() - point.getY();
        double R = circle.getRadius();
        double p = Math.sqrt(h * h - R * R);


        double sinAlpha = y / h;
        double cosAlpha = x / h;
        double sinDelta = R / h;
        double cosDelta = p / h;
        double sinAlphaMinusDelta = sinAlpha * cosDelta - sinDelta * cosAlpha;
        double cosAlphaMinusDelta = cosAlpha * cosDelta + sinAlpha * sinDelta;

        double sinAlphaPlusDelta = sinAlpha * cosDelta + sinDelta * cosAlpha;
        double cosAlphaPlusDelta = cosAlpha * cosDelta - sinAlpha * sinDelta;

        if (Math.abs(sinAlphaPlusDelta) < Settings.error()) {
            sinAlphaPlusDelta = 0d;
        }

        if (Math.abs(sinAlphaPlusDelta - 1) < Settings.error()) {
            sinAlphaPlusDelta = Math.signum(sinAlphaPlusDelta);
        }


        if (Math.abs(sinAlphaMinusDelta) < Settings.error()) {
            sinAlphaMinusDelta = 0d;
        }

        if (Math.abs(sinAlphaMinusDelta - 1) < Settings.error()) {
            sinAlphaMinusDelta = Math.signum(sinAlphaMinusDelta);
        }

        Sin sinStart = new Sin(sinAlphaMinusDelta, quadrant(cosAlphaMinusDelta, sinAlphaMinusDelta));
        Sin sinEnd = new Sin(sinAlphaPlusDelta, quadrant(cosAlphaPlusDelta, sinAlphaPlusDelta));

        return new SinInterval(sinStart, sinEnd);
    }

    private static Quadrant quadrant(double x, double y) {
        if (x > 0 && y >= 0) {
            return Quadrant.FIRST;
        }
        if (x <= 0 && y >= 0) {
            return Quadrant.SECOND;
        }
        if (x <= 0 && y < 0) {
            return Quadrant.THIRD;
        }
        if (x > 0 && y < 0) {
            return Quadrant.FORTH;
        }

        return null;
    }

    private int next(int index){
        int nextIndex = index;
        Sin currentStartSin = sinIntervals[index].getStartSin();
        while (currentStartSin.equals(sinIntervals[nextIndex].getStartSin())){
            if(nextIndex == sinIntervals.length - 1){
                break;
            }
            nextIndex++;
        }
        return nextIndex;
    }

}