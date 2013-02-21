package com.javasensei.portfolio.deepforest;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
/**
 * @author oleksiy sayankin
 */


public class InputData {
    private List<Circle> circles = new ArrayList<Circle>();
    private Coord initPos;

    public Coord getInitPos(){
        return initPos;
    }
    public List<Circle> getCircles(){
        return circles;
    }

    public void read(Reader reader) throws Exception {
        BufferedReader br = new BufferedReader(reader);
        String firstLine = br.readLine();
        int circlesCount = Integer.valueOf(firstLine);
        if(circlesCount < 0){
            throw new Exception("Illegal argument (circlesCount = " + circlesCount + ")! Circles count must be positive or zero.");
        }
        String secondLine = br.readLine();
        String[] pos = secondLine.split(" ");
        double xPos = Double.valueOf(pos[0]);
        double yPos = Double.valueOf(pos[1]);
        initPos = new Coord(xPos, yPos);
        for(int i = 0; i <= circlesCount - 1; i++){
            String line = br.readLine();
            String[] params = line.split(" ");
            double x = Double.valueOf(params[0]);
            double y = Double.valueOf(params[1]);
            double r = Double.valueOf(params[2]);
            if(r < 0){
                throw new Exception("Illegal argument (radius = " + r + ")! Radius must be positive or zero.");
            }
            circles.add(new Circle(new Coord(x, y), r));
        }
    }
}
