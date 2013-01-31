package com.javasensei.portfolio.balls;

/**
 * @author oleksiy sayankin
 */

public final class Constants {
    private Constants(){}

    public static class Window{
        public final static int WIDTH = 700;
        public final static int HEIGHT = 500;
        public final static int MARGIN = 10;
        public final static int DIVIDER = 400;
    }

    public static class Panel{
        public final static int WIDTH = 650;
        public final static int HEIGHT = 400;
        public static final int MARGIN = 30;
    }

    public static class Button{
        public final static int WIDTH = 120;
        public final static int HEIGHT = 27;
    }

    public static class Common{
        public final static double ERROR = 0.000001d;
        public final static double MAX_VELOCITY = 5d;
        public final static double CONTAINER_WIDTH = Panel.WIDTH - Panel.MARGIN * 2;
        public final static double CONTAINER_HEIGHT = Panel.HEIGHT - Panel.MARGIN * 2;
        public static final int BALLS_AMOUNT = 1000;
    }

    public static final class Strings{
        public static final String EXIT = "Exit";
        public static final String START = "Start";
        public static final String STOP = "Stop";
    }
}
