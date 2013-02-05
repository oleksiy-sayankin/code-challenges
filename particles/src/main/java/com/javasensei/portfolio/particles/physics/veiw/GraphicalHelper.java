package com.javasensei.portfolio.particles.physics.veiw;

import com.javasensei.portfolio.particles.Constants;
import com.javasensei.portfolio.particles.graphics.CanvasParticle;
import com.javasensei.portfolio.particles.graphics.CanvasPoint;
import com.javasensei.portfolio.particles.graphics.CanvasPolygon;
import com.javasensei.portfolio.particles.graphics.CanvasRectangle;
import com.javasensei.portfolio.particles.math.IPoint;
import com.javasensei.portfolio.particles.math.IPolygon;
import com.javasensei.portfolio.particles.math.IRectangle;
import com.javasensei.portfolio.particles.physics.state.ParticleState;

/**
 * @author oleksiy sayankin
 */
public final class GraphicalHelper {

    private GraphicalHelper(){}

    public static CanvasPoint transform(IPoint point) {
        int x = (int) point.getX() + Constants.Panel.MARGIN;
        int y = (int) point.getY() + Constants.Panel.MARGIN;
        return new CanvasPoint(x, y);
    }

    public static CanvasParticle transform(ParticleState particleState) {
        CanvasPoint canvasPoint = transform(particleState.point);
        int size = particleState.size;
        return new CanvasParticle(canvasPoint, size);
    }

    public static CanvasPolygon transform(IPolygon polygon) {
        return new CanvasPolygon(polygon);
    }

    public static CanvasRectangle transform(IRectangle rectangle) {
        CanvasPoint point = transform(rectangle.topLeftPoint());
        int width = (int) rectangle.width();
        int height = (int) rectangle.height();
        return new CanvasRectangle(point, width, height);
    }
}
