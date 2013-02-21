package com.javasensei.portfolio.deepforest;

/**
 * @author oleksiy sayankin
 */
public class CircleSegment {
    private double start;
    private double end;
    public CircleSegment(double start, double end){
        this.start = start;
        this.end = end;
    }

    public CircleSegment(CircleSegment segment){
        start = segment.start;
        end = segment.end;
    }
    public void normalize(){
        start = MathHelper.normalize(start);
        end = MathHelper.normalize(end);
    }

    public void shiftOver2PI(){
        start += 2 * Math.PI;
        end += 2 * Math.PI;
    }

    public boolean contains(double angle){
        if(start < end){
            return angle >= start && angle <= end;
        }
        return angle >= start && angle <= end + 2 * Math.PI;
    }

    @Override
    public String toString(){
        return "[" + start + ", " + end + "]";
    }

    double getStart(){
        return start;
    }

    double getEnd(){
        return end;
    }

    public double midPoint(){
        if(start < end){
            return (start + end) /2d;
        }
        return (start + end + 2 * Math.PI) /2d;
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(!(other instanceof CircleSegment)){
            return false;
        }
        if(this == other){
            return true;
        }
        CircleSegment otherCircleSegment = (CircleSegment)other;
        double normalizedThisStart = MathHelper.normalize(this.start);
        double normalizedThisEnd = MathHelper.normalize(this.end);
        double normalizedOtherStart = MathHelper.normalize(otherCircleSegment.start);
        double normalizedOtherEnd = MathHelper.normalize(otherCircleSegment.end);
        return MathHelper.equalsZero(normalizedThisStart - normalizedOtherStart) && MathHelper.equalsZero(normalizedThisEnd - normalizedOtherEnd);
    }

    @Override
    public int hashCode(){
        return (int)start + (int)end;
    }
}
