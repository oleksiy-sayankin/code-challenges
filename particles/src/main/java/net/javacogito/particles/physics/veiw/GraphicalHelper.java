package net.javacogito.particles.physics.veiw;

import net.javacogito.particles.Constants;
import net.javacogito.particles.graphics.CanvasParticle;
import net.javacogito.particles.graphics.CanvasPoint;
import net.javacogito.particles.graphics.CanvasPolygon;
import net.javacogito.particles.graphics.CanvasRectangle;
import net.javacogito.math.IPoint;
import net.javacogito.math.IPolygon;
import net.javacogito.math.IRectangle;
import net.javacogito.particles.physics.state.ParticleState;

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
