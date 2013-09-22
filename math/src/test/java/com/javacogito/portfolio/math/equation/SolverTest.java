package com.javacogito.portfolio.math.equation;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author oleksiy sayankin
 */
public class SolverTest {
    @Test
    public void quadraticTwoRealRootsTest(){
        double a = 3;
        double b = 8;
        double c = 2;
        double discriminant = b * b - 4 * a * c;
        double[] x = new double[2];
        x[0] = (- b - Math.sqrt(discriminant)) / (2 * a);
        x[1] = (- b + Math.sqrt(discriminant)) / (2 * a);
        Solution expectedSolution = new Solution(x, Resolution.TWO_REAL_ROOTS);
        Solution actualSolution = Solver.quadratic(a, b, c);
        Assert.assertEquals(expectedSolution, actualSolution);
    }

    @Test
    public void quadraticOneRealRootsTest(){
        double a = 2;
        double b = 4;
        double c = 2;
        double[] x = new double[2];
        x[0] = (- b ) / (2 * a);
        x[1] = (- b ) / (2 * a);
        Solution expectedSolution = new Solution(x, Resolution.ONE_REAL_ROOT);
        Solution actualSolution = Solver.quadratic(a, b, c);
        Assert.assertEquals(expectedSolution, actualSolution);
    }

    @Test
    public void quadraticNoRealRootsTest(){
        double a = 5;
        double b = 4;
        double c = 8;
        Solution expectedSolution = new Solution(Resolution.NO_REAL_ROOTS);
        Solution actualSolution = Solver.quadratic(a, b, c);
        Assert.assertEquals(expectedSolution, actualSolution);
    }
}
