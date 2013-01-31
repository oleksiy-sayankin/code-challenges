package com.javasensei.portfolio.balls.physics.state;

import com.javasensei.portfolio.balls.math.IPolygon;
import com.javasensei.portfolio.balls.math.MathHelper;
import com.javasensei.portfolio.balls.physics.model.IBallCollection;
import com.javasensei.portfolio.balls.physics.model.IBallModel;
import com.javasensei.portfolio.balls.physics.model.IBoundModel;
import com.javasensei.portfolio.balls.physics.state.BallState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author asayankin
 */
public class ContainerState {
    public final List<BallState> balls;
    public final IPolygon polygon;
    public ContainerState(IBallCollection<IBallModel> aBalls, IBoundModel aBoundModel){
        List<BallState> bs = new ArrayList<BallState>();
        for(IBallModel ball : aBalls){
            bs.add(ball.state());
        }
        balls = Collections.unmodifiableList(bs);
        polygon = aBoundModel.getSides();
    }
}
