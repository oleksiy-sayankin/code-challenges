package com.javacogito.portfolio.math;

/**
 * @author oleksiy sayankin
 */
public final class Settings {
    private static double error = MathConstants.ERROR;
    private Settings(){}
    public static double error(){
        return error;
    }
    public static void setError(double newError){
        error = newError;
    }

    public static void restoreDefaults(){
        error = MathConstants.ERROR;
    }
}
