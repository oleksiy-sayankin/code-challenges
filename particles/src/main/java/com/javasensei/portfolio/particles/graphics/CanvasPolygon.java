package com.javasensei.portfolio.particles.graphics;

import com.javasensei.portfolio.particles.physics.veiw.GraphicalHelper;
import com.javasensei.portfolio.math.IPoint;
import com.javasensei.portfolio.math.IPolygon;

import java.util.List;

/**
 * @author oleksiy sayankin
 */
public final class CanvasPolygon {
    public final int xPoints[];
    public final int yPoints[];
    public final int n;

    public CanvasPolygon(IPolygon polygon) {
        List<IPoint> points = polygon.points();
        n = points.size();
        xPoints = new int[n];
        yPoints = new int[n];
        int i = 0;
        for (IPoint point : points) {
            CanvasPoint canvasPoint = GraphicalHelper.transform(point);
            xPoints[i] = canvasPoint.x;
            yPoints[i] = canvasPoint.y;
            i++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= n - 2; i++) {
            sb.append("(");
            sb.append(xPoints[i]);
            sb.append(", ");
            sb.append(yPoints[i]);
            sb.append("), ");
        }
        sb.append("(");
        sb.append(xPoints[n - 1]);
        sb.append(", ");
        sb.append(yPoints[n - 1]);
        sb.append(")");
        sb.append("]");
        return sb.toString();
    }
}
