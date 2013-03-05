package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.Arc;
import com.javasensei.portfolio.math.IArc;
import com.javasensei.portfolio.math.ICircle;
import com.javasensei.portfolio.math.MathHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 @author oleksiy sayankin
 */
public class Aggregator {
    private List<IArc> arcs = new ArrayList<IArc>();
    private InputData inputData;

    public Aggregator(){}

    public Aggregator(InputData inputData){
        this.inputData  = inputData;
        for(ICircle circle : inputData.getCircles()){
            IArc normalizedArc = MathHelper.shadow(circle, inputData.getInitPos());
            normalizedArc.normalize();
            arcs.add(normalizedArc);
        }
    }

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
        for(int i = 0; i <= allAngles.length - 2; i++){
            start = allAngles[i];
            end = allAngles[i + 1];
            if(isFreeAngle(start, end)){
                result.add(new Arc(start, end));
            }
        }
        start = allAngles[allAngles.length - 1];
        end = allAngles[0] + 2 * Math.PI;

        if(isFreeAngle(start, end)){
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
        for(i = 0; i <= allAngles.length - 1; i++){
            System.out.print(allAngles[i]);
            if(i!= 0){
                System.out.println(", delta = " + (allAngles[i] - allAngles[i -1]));
            } else {
                System.out.println();
            }
        }
        return allAngles;
    }

    private boolean isFreeAngle(double start, double end){
        double angle = (start + end) / 2;
        for(IArc arc : arcs){
            if(arc.containsAngle(angle)) return false;
            System.out.print(arc);
            if(arc.getStartAngle() > arc.getEndAngle()){
                System.out.println("!!!");
            }else{
                System.out.println();
            }
        }
        System.out.println("start = " + start + ", end = " + end);
        return true;
    }
}