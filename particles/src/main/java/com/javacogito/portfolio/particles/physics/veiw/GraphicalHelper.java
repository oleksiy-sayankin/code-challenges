package com.javacogito.portfolio.particles.physics.veiw;

import com.javacogito.portfolio.particles.Constants;
import com.javacogito.portfolio.particles.graphics.CanvasParticle;
import com.javacogito.portfolio.particles.graphics.CanvasPoint;
import com.javacogito.portfolio.particles.graphics.CanvasPolygon;
import com.javacogito.portfolio.particles.graphics.CanvasRectangle;
import com.javacogito.portfolio.math.IPoint;
import com.javacogito.portfolio.math.IPolygon;
import com.javacogito.portfolio.math.IRectangle;
import com.javacogito.portfolio.particles.physics.state.ParticleState;

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
