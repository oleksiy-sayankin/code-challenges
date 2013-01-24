package com.javasensei.portfolio.balls.physics;

import com.javasensei.portfolio.balls.GraphicalHelper;
import com.javasensei.portfolio.balls.math.IPolygon;
import com.javasensei.portfolio.balls.math.MathHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author asayankin
 */
public class ContainerState {
    public final List<BallState> balls;
    public final IPolygon polygon;
    public ContainerState(List<IBall> aBalls, IPolygon aPolygon){
        List<BallState> bs = new ArrayList<BallState>();
        for(IBall ball : aBalls){
            bs.add(ball.state());
        }
        balls = Collections.unmodifiableList(bs);
        polygon = MathHelper.unmodifiablePolygon(aPolygon);
    }
}
