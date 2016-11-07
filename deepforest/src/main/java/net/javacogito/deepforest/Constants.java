package net.javacogito.deepforest;

/**
 * @author oleksiy sayankin
 */
public final class Constants {
  private Constants() {
  }

  public static final String YES = "YES";
  public static final String NO = "NO";
  public static final double DEFAULT_RADIUS = 100d;


  public static final Sin PI_0_5_LEFT = new Sin(1, Quadrant.FIRST);
  public static final Sin PI_0_5_RIGHT = new Sin(1, Quadrant.SECOND);
  public static final Sin PI_1_0_LEFT = new Sin(0, Quadrant.SECOND);
  public static final Sin PI_1_0_RIGHT = new Sin(0, Quadrant.THIRD);
  public static final Sin PI_1_5_LEFT = new Sin(-1, Quadrant.THIRD);
  public static final Sin PI_1_5_RIGHT = new Sin(-1, Quadrant.FORTH);
  public static final Sin PI_2_0_LEFT = new Sin(0, Quadrant.FORTH);
  public static final Sin PI_2_0_RIGHT = new Sin(0, Quadrant.FIFTH);

  public static final Sin PI_2_5_LEFT = new Sin(1, Quadrant.FIFTH);
  public static final Sin PI_2_5_RIGHT = new Sin(1, Quadrant.SIXTH);
  public static final Sin PI_3_0_LEFT = new Sin(0, Quadrant.SIXTH);
  public static final Sin PI_3_0_RIGHT = new Sin(0, Quadrant.SEVENTH);
  public static final Sin PI_3_5_LEFT = new Sin(-1, Quadrant.SEVENTH);
  public static final Sin PI_3_5_RIGHT = new Sin(-1, Quadrant.EIGHT);

  public static final Sin[][] EDGE_SIN = new Sin[7][2];

  static {
    EDGE_SIN[0][0] = PI_0_5_LEFT;
    EDGE_SIN[0][1] = PI_0_5_RIGHT;
    EDGE_SIN[1][0] = PI_1_0_LEFT;
    EDGE_SIN[1][1] = PI_1_0_RIGHT;
    EDGE_SIN[2][0] = PI_1_5_LEFT;
    EDGE_SIN[2][1] = PI_1_5_RIGHT;
    EDGE_SIN[3][0] = PI_2_0_LEFT;
    EDGE_SIN[3][1] = PI_2_0_RIGHT;
    EDGE_SIN[4][0] = PI_2_5_LEFT;
    EDGE_SIN[4][1] = PI_2_5_RIGHT;
    EDGE_SIN[5][0] = PI_3_0_LEFT;
    EDGE_SIN[5][1] = PI_3_0_RIGHT;
    EDGE_SIN[6][0] = PI_3_5_LEFT;
    EDGE_SIN[6][1] = PI_3_5_RIGHT;
  }

  public static final double FULL_CIRCLE = 1d;
}
