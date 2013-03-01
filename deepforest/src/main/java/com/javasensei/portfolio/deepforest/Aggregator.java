package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.Arc;
import com.javasensei.portfolio.math.IArc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 @author oleksiy sayankin
 */
public class Aggregator {
    private List<IArc> arcs = new ArrayList<IArc>();

    public void add(IArc arc){
        IArc normalizedArc = new Arc(arc);
        normalizedArc.normalize();
        arcs.add(normalizedArc);
    }

    public List<IArc> freeArcs(){
        if(arcs.isEmpty()){
            return null;
        }
        List<IArc> result = new ArrayList<IArc>();
        double[] allAngles = allAngles();
        double start;
        double end;
        double midAngle;
        for(int i = 0; i <= allAngles.length - 2; i++){
            start = allAngles[i];
            end = allAngles[i + 1];
            midAngle = (start + end) / 2;
            if(isFreeAngle(midAngle)){
                result.add(new Arc(start, end));
            }
        }
        start = allAngles[allAngles.length - 1];
        end = allAngles[0] + 2 * Math.PI;
        midAngle = (start + end) / 2;

        if(isFreeAngle(midAngle)){
            result.add(new Arc(start, end));
        }
        return result;
    }


    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        for(IArc arc : arcs){
            if(first){
                first = false;
            } else {
                sb.append("\n");
            }
            sb.append(arc);
        }
        return sb.toString();
    }

    private double[] allAngles(){
        double[] allAngles = new double[arcs.size() * 2];
        int i = 0;
        for(IArc arc : arcs){
            allAngles[i] = arc.getStartAngle();
            i++;
            allAngles[i] = arc.getEndAngle();
            i++;
        }
        Arrays.sort(allAngles);
        return allAngles;
    }

    private boolean isFreeAngle(double angle){
        for(IArc arc : arcs){
            if(arc.containsAngle(angle)) return false;
        }
        return true;
    }
}