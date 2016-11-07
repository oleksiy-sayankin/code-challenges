package net.javacogito.particles;

/**
 * @author oleksiy sayankin
 */

public final class Constants {
  private Constants() {
  }

  public static class Window {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
  }

  public static class Panel {
    public static final int MARGIN = 30;
  }

  public static class Common {
    public static final double MAX_VELOCITY = 5d;
    public static final double MAX_MASS = 5d;
    public static final double CONTAINER_MIN_WIDTH = 10;
    public static final double CONTAINER_MIN_HEIGHT = 10;
    public static final int PARTICLES_AMOUNT = 1000;
  }

  public static final class Strings {
    public static final String EXIT = "Exit";
    public static final String START = "Start";
    public static final String STOP = "Stop";
  }

  public static final class Util {
    public static final String PROPERTIES_FILE_NAME = "particles.ini";
  }

  public static final class ParticleType {
    public static final String SIMPLE = "Simple";
    public static final String MASS = "Mass";
  }

  public static final class Properties {
    public static final String PARTICLE_TYPE = "particleType";
  }
}
