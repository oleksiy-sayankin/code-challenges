package com.javasensei.portfolio.balls;

/**
 * @author oleksiy sayankin
 */

public final class Constants {
    private Constants(){}

    public static class Window{
        public final static int WIN_WIDTH = 700;
        public final static int WIN_HEGHT = 500;
        public final static int BUTTON_WIDTH = 120;
        public final static int BUTTON_HEIGHT = 27;
        public final static int MARGIN = 10;
        public final static int WIN_MARGIN = 7;
        public final static int DIVIDER = 400;
        public final static int WIN_HEADER = 40;
    }

    public static class Common{
        public final static double ERROR = 0.000001d;
        public final static double MAX_VELOCITY = 5d;
        public final static int MAX_STEPS = 1000000;
    }

    public static final class Strings{
        public static final String EXIT = "Exit";
        public static final String START = "Start";
    }
}
