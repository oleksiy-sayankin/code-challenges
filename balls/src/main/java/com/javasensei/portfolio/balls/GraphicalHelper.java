package com.javasensei.portfolio.balls;

import com.javasensei.portfolio.balls.graphics.CanvasPoint;
import com.javasensei.portfolio.balls.graphics.CanvasPolygon;
import com.javasensei.portfolio.balls.math.IPoint;
import com.javasensei.portfolio.balls.math.IPolygon;

/**
 * @author asayankin
 */
public final class  GraphicalHelper {
    public static CanvasPoint transform(IPoint point){
        int x = (int) point.getX() + Constants.Window.WIN_MARGIN;
        int y = (int) point.getY() + Constants.Window.HEADER + Constants.Window.WIN_MARGIN;
        return new CanvasPoint(x, y);
    }

    public static GraphicalState transform(BallState ballState){
        CanvasPoint canvasPoint = transform(ballState.point);
        int size = ballState.size;
        return new GraphicalState(canvasPoint, size);
    }
    public static CanvasPolygon transform(IPolygon polygon){
        return new CanvasPolygon(polygon);
    }
}
