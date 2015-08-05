package net.javacogito.math;

/**
 * @author oleksiy sayankin
 */
public interface ILine extends IFigure {
    LineCoef coef();
    IVector toVector();
    IPoint point1();
    IPoint point2();
    ILine orthogonal(IPoint point);
    boolean isIntersection(ILine secondLine);
    IPoint intersection(ILine secondLine);
    IPoint intersection(ISegment segment);
}
