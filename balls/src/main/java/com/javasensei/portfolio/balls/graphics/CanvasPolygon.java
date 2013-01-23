package com.javasensei.portfolio.balls.graphics;

import com.javasensei.portfolio.balls.GraphicalHelper;
import com.javasensei.portfolio.balls.math.IPoint;
import com.javasensei.portfolio.balls.math.IPolygon;
import com.javasensei.portfolio.balls.math.Polygon;
import com.javasensei.portfolio.balls.math.Vector;

import java.util.List;

/**
 * @author asayankin
 */
public final class CanvasPolygon {
    public final int xPoints[];
    public final int yPoints[];
    public final int n;

    public CanvasPolygon(IPolygon polygon){
        IPolygon scaledPolygon = new Polygon(polygon);
        scaledPolygon.scale(1.02);
        scaledPolygon.translateInDirection(new Vector(-1, -1));
        List<IPoint> points = scaledPolygon.points();
        n = points.size();
        xPoints = new int[n];
        yPoints = new int[n];
        int i = 0;
        for(IPoint point : points){
            CanvasPoint canvasPoint = GraphicalHelper.transform(point);
            xPoints[i] = canvasPoint.x;
            yPoints[i] = canvasPoint.y;
            i++;
        }
    }
}
