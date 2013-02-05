package com.javasensei.portfolio.particles;

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
        public static final double ERROR = 0.000001d;
        public static final double MAX_VELOCITY = 5d;
        public static final double CONTAINER_MIN_WIDTH = 10;
        public static final double CONTAINER_MIN_HEIGHT = 10;
        public static final int PARTICLES_AMOUNT = 1000;
    }

    public static final class Strings {
        public static final String EXIT = "Exit";
        public static final String START = "Start";
        public static final String STOP = "Stop";
    }
}
