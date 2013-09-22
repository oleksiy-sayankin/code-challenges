package com.javacogito.portfolio.deepforest;

import com.javacogito.portfolio.math.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

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

    public SinIntervalAggregator(int length) {
        sinIntervals = new SinInterval[length];
    }

    public void add(SinInterval sinInterval) {
        int i = 0;
        while (sinIntervals[i] != null) {
            if (i == sinIntervals.length - 1) {
                return;
            }
            i++;
        }

        sinIntervals[i] = sinInterval;
    }

    public SinInterval freeSinInterval() {
        Arrays.sort(sinIntervals);
        if (sinIntervals.length == 0) {
            return null;
        }
        int index = 0;
        Sin currentEndSin = sinIntervals[index].getEndSin();
        while (index <= sinIntervals.length - 2) {
            Sin newCurrentEndSin = sinIntervals[index].getEndSin();
            if (currentEndSin.isLessThan(newCurrentEndSin)) {
                currentEndSin = newCurrentEndSin;
            }

            int nextIndex = next(index);
            Sin nextStartSin = sinIntervals[nextIndex].getStartSin();
            if (currentEndSin.isLessThan(nextStartSin)) {
                return new SinInterval(currentEndSin.normalized(), nextStartSin.normalized());
            }
            index = nextIndex;
        }


        Sin start = getMaxEndSin();
        Sin end = sinIntervals[0].getStartSin();

        if(start.isNormal()){
            return new SinInterval(start, end);
        } else{
            Sin startNormalized = start.normalized();
            if (startNormalized.isLessThan(end)) {
                return new SinInterval(startNormalized, end);
            }
        }

        return null;
    }

    private Sin getMaxEndSin() {
        Sin maxEndSin = new Sin(0, Quadrant.FIRST);
        for (SinInterval sinInterval : sinIntervals) {
            if (maxEndSin.compareTo(sinInterval.getEndSin()) < 0) {
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

        if (MathHelper.equalsZero(sinAlphaPlusDelta)) {
            sinAlphaPlusDelta = 0d;
        }

        if (MathHelper.equals(sinAlphaPlusDelta, 1)) {
            sinAlphaPlusDelta = 1;
        }

        if (MathHelper.equals(sinAlphaPlusDelta, -1)) {
            sinAlphaPlusDelta = -1;
        }

        if (MathHelper.equalsZero(sinAlphaMinusDelta)) {
            sinAlphaMinusDelta = 0d;
        }

        if (MathHelper.equals(sinAlphaMinusDelta, 1)) {
            sinAlphaMinusDelta = 1;
        }

        if (MathHelper.equals(sinAlphaMinusDelta, -1)) {
            sinAlphaMinusDelta = -1;
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

    private int next(int index) {
        int nextIndex = index;
        Sin currentStartSin = sinIntervals[index].getStartSin();
        while (currentStartSin.equals(sinIntervals[nextIndex].getStartSin())) {
            if (nextIndex == sinIntervals.length - 1) {
                break;
            }
            nextIndex++;
        }
        return nextIndex;
    }
}