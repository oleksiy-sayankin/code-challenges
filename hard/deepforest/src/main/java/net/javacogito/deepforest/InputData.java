package net.javacogito.deepforest;

import net.javacogito.math.Circle;
import net.javacogito.math.ICircle;
import net.javacogito.math.IPoint;
import net.javacogito.math.Point;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * @author oleksiy sayankin
 */


public class InputData {
  private ICircle[] circles;
  private IPoint initPos;
  private int circlesCount;

  public IPoint getInitPos() {
    return initPos;
  }

  public ICircle[] getCircles() {
    return circles;
  }

  public int getCirclesCount() {
    return circlesCount;
  }

  public void read(Reader reader) throws Exception {
    BufferedReader br = new BufferedReader(reader);
    String firstLine = br.readLine();
    circlesCount = Integer.valueOf(firstLine);
    if (circlesCount < 0) {
      throw new Exception("Illegal argument (circlesCount = " + circlesCount + ")! Circles count must be positive or zero.");
    }
    circles = new ICircle[circlesCount];
    String secondLine = br.readLine();
    String[] pos = secondLine.split(" ");
    double xPos = Double.valueOf(pos[0]);
    double yPos = Double.valueOf(pos[1]);
    initPos = new Point(xPos, yPos);
    for (int i = 0; i <= circlesCount - 1; i++) {
      String line = br.readLine();
      String[] params = line.split(" ");
      double x = Double.valueOf(params[0]);
      double y = Double.valueOf(params[1]);
      double r = Double.valueOf(params[2]);
      if (r < 0) {
        throw new Exception("Illegal argument (radius = " + r + ")! Radius must be positive or zero.");
      }
      circles[i] = new Circle(new Point(x, y), r);
    }
  }
}
