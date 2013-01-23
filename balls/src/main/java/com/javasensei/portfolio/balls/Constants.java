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
        public final static int WIN_MARGIN = 30;
        public final static int DIVIDER = 400;
        public final static int HEADER = 40;
    }

    public static class Button{

        public final static int WIDTH = 120;
        public final static int HEIGHT = 27;
    }

    public static class Common{
        public final static double ERROR = 0.000001d;
        public final static double MAX_VELOCITY = 5d;
        public final static double CONTAINER_WIDTH = Window.WIDTH - Window.WIN_MARGIN * 2;
        public final static double CONTAINER_HEIGHT = Window.DIVIDER - Window.WIN_MARGIN * 2 - Window.HEADER;
    }

    public static final class Strings{
        public static final String EXIT = "Exit";
        public static final String START = "Start";
        public static final String STOP = "Stop";
    }
}
