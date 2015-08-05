package net.javacogito.math.equation;

import net.javacogito.math.MathHelper;

/**
 * @author oleksiy sayankin
 */
public final class Solver {
    private Solver(){}

    public static Solution quadratic(double a, double b, double c){
        double discriminant = b * b - 4 * a * c;
        if(discriminant < 0){
            return NO_REAL_SOLUTION;
        }
        double[] x = new double[2];

        if(MathHelper.equalsZero(discriminant)){
            x[0] = (- b ) / (2 * a);
            x[1] = (- b ) / (2 * a);
            return new Solution(x, Resolution.ONE_REAL_ROOT);
        }

        x[0] = (- b - Math.sqrt(discriminant)) / (2 * a);
        x[1] = (- b + Math.sqrt(discriminant)) / (2 * a);
        return new Solution(x, Resolution.TWO_REAL_ROOTS);
    }

    private static Solution NO_REAL_SOLUTION = new Solution(Resolution.NO_REAL_ROOTS);
}
