package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class SinIntervalAggregator {
    private List<SinInterval> sinIntervals = new ArrayList<SinInterval>();
    private InputData inputData;

    public SinIntervalAggregator() {
    }

    public SinIntervalAggregator(InputData inputData) {
        this.inputData = inputData;
        for (ICircle circle : inputData.getCircles()) {
            sinIntervals.add(sinInterval(circle, inputData.getInitPos()));
        }
    }

    public void add(SinInterval sinInterval) {
        sinIntervals.add(sinInterval);
    }

    public List<SinInterval> freeSinIntervals() {
        if (sinIntervals.isEmpty()) {
            return null;
        }
        List<SinInterval> result = new ArrayList<SinInterval>();
        Sin[] allAngles = allSin();
        Sin start;
        Sin end;
        for (int i = 0; i <= allAngles.length - 2; i++) {
            start = allAngles[i];
            end = allAngles[i + 1];
            //System.out.println("### start = " + start + ", end = " + end);
            SinInterval interval = new SinInterval(start, end);
            if (isFreeAngle(interval)) {
                result.add(interval);
            }
        }
        start = allAngles[allAngles.length - 1];
        end = allAngles[0];
     //   System.out.println("### start = " + start + ", end = " + end);
        SinInterval lastInterval = new SinInterval(start, end);
        if (isFreeAngle(lastInterval)) {
            result.add(lastInterval);
        }
        return result;
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
        Sin[] allSin = new Sin[sinIntervals.size() * 2];
        int i = 0;
        for (SinInterval sinInterval : sinIntervals) {
            allSin[i] = sinInterval.getStartSin().normalized();
            i++;
            allSin[i] = sinInterval.getEndSin().normalized();
            i++;
        }
        Arrays.sort(allSin);
//        for (i = 0; i <= allSin.length - 1; i++) {
//            System.out.print(allSin[i]);
//            if (i != 0) {
//                System.out.println(", delta = " + (allSin[i].getSinValue() - allSin[i - 1].getSinValue()));
//            } else {
//                System.out.println();
//            }
//        }
//        System.out.println("---");
        return allSin;
    }

    private boolean isFreeAngle(SinInterval interval) {
        Sin innerSin = interval.innerSin();
        for (SinInterval sinInterval : sinIntervals) {
            if (sinInterval.contains(innerSin))
                return false;
        }
        //System.out.println("start = " + interval);
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

}