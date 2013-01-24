package com.javasensei.portfolio.balls.physics;

import com.javasensei.portfolio.balls.math.ISegment;

import java.util.List;

/**
 * @author ashvayka
 */
public interface IContainer {
    double getWidth();
    double getHeight();
    List<ISegment> getSides();
}
