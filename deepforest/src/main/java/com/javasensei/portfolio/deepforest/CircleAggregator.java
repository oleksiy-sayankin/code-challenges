package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class CircleAggregator {
    private List<IArc> arcs = new ArrayList<IArc>();
    private InputData inputData;

    public CircleAggregator(InputData inputData) {
        this.inputData = inputData;
        for (ICircle circle : inputData.getCircles()) {
            IArc normalizedArc = MathHelper.shadow(circle, inputData.getInitPos());
            //normalizedArc.normalize();
            arcs.add(normalizedArc);
        }
    }


    public List<IArc> freeArcs() {
        if (arcs.isEmpty()) {
            return null;
        }
        List<IArc> result = new ArrayList<IArc>();
        double[] allAngles = allAngles();
        double start;
        double end;
        for (int i = 0; i <= allAngles.length - 2; i++) {
            start = allAngles[i];
            end = allAngles[i + 1];
            if (isFreeAngle(start, end)) {
                result.add(new Arc(start, end));
            }
        }
        start = allAngles[allAngles.length - 1];
        end = allAngles[0] + 2 * Math.PI;

        if (isFreeAngle(start, end)) {
            result.add(new Arc(start, end));
        }
        return result;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        for (IArc arc : arcs) {
            if (first) {
                first = false;
            } else {
                sb.append("\n");
            }
            sb.append(arc);
        }
        return sb.toString();
    }

    private double[] allAngles() {
        double[] allAngles = new double[arcs.size() * 2];
        int i = 0;
        for (IArc arc : arcs) {
            allAngles[i] = arc.getStartAngle();
            i++;
            allAngles[i] = arc.getEndAngle();
            i++;
        }
        Arrays.sort(allAngles);
        return allAngles;
    }

    private boolean isFreeAngle(double start, double end) {
        double angle = (start + end) / 2;
        double dx = Math.cos(angle) * Constants.DEFAULT_RADIUS;
        double dy = Math.sin(angle) * Constants.DEFAULT_RADIUS;
        IRay ray = new Ray(inputData.getInitPos(), new Vector(dx, dy));

        for (ICircle circle : inputData.getCircles()) {
            if (!circle.intersection(ray).isEmpty()) {

                return false;
            }
        }
        System.out.println("start = " + start + ", end = " + end + ", ray = " + ray);
        return true;
    }
}