package com.javasensei.portfolio.balls.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.javasensei.portfolio.balls.math.MathHelper.unmodifiableSegment;
import static com.javasensei.portfolio.balls.math.MathHelper.unmodifiablePoint;

/**
 * @author asayankin
 */
public class Polygon implements IPolygon{
    private List <IPoint> points;

    public Polygon(){
        points = new ArrayList<IPoint>();
    }

    public Polygon(IPolygon polygon){
        points = new ArrayList<IPoint>();
        for (IPoint point : polygon.points()){
            points.add(new Point(point));
        }
    }

    @Override
    public boolean has(IPoint point) {
        ISegment segment = MathHelper.nearestSegment(toSegmentsClockwise(), point);
        return isOnTheRightSide(segment.toVector(), new Point(point.getX() - segment.getPoint1X(), point.getY() - segment.getPoint1Y()));
    }

    @Override
    public double distanceTo(IPoint point) {
        return MathHelper.nearestSegment(toSegmentsClockwise(), point).distanceTo(point);
    }

    @Override
    public void translateInDirection(IVector vector) {
         for (IPoint point : points){
             point.translateInDirection(vector);
         }
    }

    @Override
    public void scale(double coef) {
        for (IPoint point : points){
            point.scale(coef);
        }
    }

    @Override
    public List<ISegment> toSegmentsClockwise(){
        List<ISegment> segments = new ArrayList<ISegment>();
        for (int i = 0; i <= points.size() - 2; i++){
            segments.add(unmodifiableSegment(new Segment(points.get(i), points.get(i + 1))));
        }
        segments.add(unmodifiableSegment(new Segment(points.get(points.size() - 1), points.get(0))));
        return segments;
    }

    @Override
    public void addPoint(IPoint point){
        points.add(point.copy());
    }

    @Override
    public List<IPoint> points(){
        List<IPoint> unmodifiablePoints = new ArrayList<IPoint>();
        for (IPoint point : points){
            unmodifiablePoints.add(unmodifiablePoint(point));
        }
        return Collections.unmodifiableList(unmodifiablePoints);
    }

    @Override
    public void stretchInDirection(IVector direction){
        double rightBound = rightBound();
        double leftBound = leftBound();
        double topBound = upBound();
        double bottomBound = downBound();
        double width = width();
        double height = height();
        double dX = direction.getX();
        double dY = direction.getY();
        for (IPoint point : points){
            double x = point.getX();
            double y = point.getY();
            double coefX = dX > 0 ? (x - leftBound) / width : (rightBound - x) / width;
            double coefY = dY > 0 ? (y - bottomBound) / height : (topBound - y) / height;
            IVector vector = new Vector(dX * coefX, dY * coefY);
            point.translateInDirection(vector);
        }
    }

    private static boolean isOnTheRightSide(IVector a, IPoint point){
         return MathHelper.signedArea(a, new Vector(point.getX(), point.getY())) < 0;
    }

    @Override
    public double width(){
        return rightBound() - leftBound();
    }

    @Override
    public double height(){
        return upBound() - downBound();
    }

    @Override
    public IRectangle bounds(){
        return MathHelper.unmodifiableRectangle(new Rectangle(leftBound(), rightBound(), upBound(), downBound()));
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int size = points.size();
        for (int i = 0; i <= size - 2; i++){
            sb.append(points.get(i));
            sb.append(", ");
        }
        sb.append(points.get(size - 1));
        sb.append("}");
        return sb.toString();
    }

    protected void setPoints(List<IPoint> newPoints){
        points = newPoints;
    }


    private double leftBound(){
        double leftBound = Double.MAX_VALUE;
        for (IPoint point : points){
            if(point.getX() < leftBound) {
                leftBound = point.getX();
            }
        }
        return leftBound;
    }

    private double rightBound(){
        double rightBound = -Double.MAX_VALUE;
        for (IPoint point : points){
            if(point.getX() > rightBound) {
                rightBound = point.getX();
            }
        }
        return rightBound;
    }

    private double upBound(){
        double topBound = -Double.MAX_VALUE;
        for (IPoint point : points){
            if(point.getY() > topBound) {
                topBound = point.getY();
            }
        }
        return topBound;
    }

    private double downBound(){
        double bottomBound = Double.MAX_VALUE;
        for (IPoint point : points){
            if(point.getY() < bottomBound) {
                bottomBound = point.getY();
            }
        }
        return bottomBound;
    }
}
