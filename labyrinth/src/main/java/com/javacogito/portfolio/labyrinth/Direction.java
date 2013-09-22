package com.javacogito.portfolio.labyrinth;

/**
 *  @author oleksiy sayankin
 */
public enum Direction {
    UP("U"), DOWN("D"), LEFT("L"), RIGHT("R");
    private String name;
    Direction(String name){
        this.name = name;
    }
    public static Direction find(Coord coordA, Coord coordB){
        int dx = coordB.x() - coordA.x();
        int dy = coordB.y() - coordA.y();
        if(dx >  0 && dy == 0 ) return RIGHT;
        if(dx <  0 && dy == 0 ) return LEFT;
        if(dx == 0 && dy  < 0 ) return UP;
        if(dx == 0 && dy  > 0 ) return DOWN;
        return null;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
       return name;
    }
}
