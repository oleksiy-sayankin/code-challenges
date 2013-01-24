package com.javasensei.portfolio.balls;

import com.javasensei.portfolio.balls.graphics.CanvasBall;
import com.javasensei.portfolio.balls.graphics.CanvasPoint;
import com.javasensei.portfolio.balls.graphics.CanvasPolygon;
import com.javasensei.portfolio.balls.graphics.CanvasRectangle;
import com.javasensei.portfolio.balls.math.IPoint;
import com.javasensei.portfolio.balls.math.IPolygon;
import com.javasensei.portfolio.balls.math.IRectangle;
import com.javasensei.portfolio.balls.physics.BallState;

/**
 * @author asayankin
 */
public final class  GraphicalHelper {
    public static CanvasPoint transform(IPoint point){
        int x = (int) point.getX() + Constants.Window.WIN_MARGIN;
        int y = (int) point.getY() + Constants.Window.HEADER + Constants.Window.WIN_MARGIN;
        return new CanvasPoint(x, y);
    }

    public static CanvasBall transform(BallState ballState){
        CanvasPoint canvasPoint = transform(ballState.point);
        int size = ballState.size;
        return new CanvasBall(canvasPoint, size);
    }
    public static CanvasPolygon transform(IPolygon polygon){
        return new CanvasPolygon(polygon);
    }

    public static CanvasRectangle transform(IRectangle rectangle){
        CanvasPoint point = transform(rectangle.points().get(0));
        int width = (int) rectangle.width();
        int height = (int) rectangle.height();
        return new CanvasRectangle(point, width, height);
    }
}
