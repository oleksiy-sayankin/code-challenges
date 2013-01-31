package com.javasensei.portfolio.balls.physics.model;

import com.javasensei.portfolio.balls.math.IPoint;
import com.javasensei.portfolio.balls.math.IPolygon;
import com.javasensei.portfolio.balls.math.IVector;
import com.javasensei.portfolio.balls.math.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author asayankin
 */
public class BallCollection<E extends IBallModel> implements IBallCollection<E>{
    private final List<IBallModel> balls;
    private final IContainerModel containerModel;

    public BallCollection(IContainerModel aContainerModel){
        balls = new ArrayList<IBallModel>();
        containerModel = aContainerModel;
    }

    @Override
    public void moveAll() {
            for (IBallModel ball : balls) {
                ball.move();
        }
    }

    @Override
    public void addBalls(int count){
        for (int i = 0; i < count - 1; i++) {
            balls.add(new BallModel(containerModel));
        }
    }

    @Override
    public  void stretch(IPoint stretchPoint, IVector direction) {
        IPolygon sides = containerModel.getSides();
        double rightBound = sides.rightBound();
        double leftBound = sides.leftBound();
        double upBound = sides.upBound();
        double downBound = sides.downBound();
        double dX = direction.getX();
        double dY = direction.getY();
        double stretchPointX = stretchPoint.getX();
        double stretchPointY = stretchPoint.getY();
        for (IBallModel ball : balls){
            double x = ball.getX();
            double y = ball.getY();
            double coefX = 1;
            double coefY = 1;
            if(stretchPointX >= rightBound){
                coefX = (x - leftBound) / (stretchPointX - leftBound);
            }
            if(stretchPointX <= leftBound){
                coefX = (rightBound - x) / (rightBound - stretchPointX);
            }
            if(stretchPointY >= upBound){
                coefY = (y - downBound) / (stretchPointY - downBound);
            }
            if(stretchPointY <= downBound){
                coefY = (upBound - y) / (upBound - stretchPointY);
            }
            IVector vector = new Vector(dX * coefX, dY * coefY);
            ball.translateInDirection(vector);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>)balls.iterator();
    }
}
