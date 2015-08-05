package net.javacogito.math.equation;

import java.util.Arrays;

/**
 * @author oleksiy sayankin
 */
public class Solution {
    private double[] roots;
    private Resolution resolution;

    public Solution(double[] roots, Resolution resolution){
        this.resolution = resolution;
        this.roots = roots;
    }

    public Solution(Resolution resolution){
        roots = null;
        this.resolution = resolution;
    }

    public double[] getRoots() {
        return roots;
    }

    public Resolution getResolution() {
        return resolution;
    }

    @Override
    public boolean equals(Object other){
        if(other == null) {
            return false;
        }
        if(!(other instanceof Solution)){
            return false;
        }
        if(other == this){
            return true;
        }
        Solution otherSolution = (Solution) other;
        if(this.resolution == Resolution.NO_REAL_ROOTS || this.resolution == Resolution.INFINITY_AMOUNT_OF_ROOTS){
            return this.resolution == otherSolution.resolution;
        }
        return this.resolution == otherSolution.resolution && Arrays.equals(this.roots, otherSolution.roots);
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(roots) + resolution.hashCode();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(resolution);
        if (resolution == Resolution.NO_REAL_ROOTS || resolution == Resolution.INFINITY_AMOUNT_OF_ROOTS){
            return sb.toString();
        }
        boolean first = true;
        for (double root : roots){
            if(first){
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append(root);
        }
        return sb.toString();
    }
}
