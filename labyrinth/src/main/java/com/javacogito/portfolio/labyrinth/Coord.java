package com.javacogito.portfolio.labyrinth;

import java.util.HashSet;
import java.util.Set;

/**
 *  @author oleksiy sayankin
 */
public class Coord {
    private int x;
    private int y;
    public Coord(int y, int x){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other){
        if(other == null){ return false; }
        if(!(other instanceof Coord)){ return false; }
        Coord otherCoord = (Coord) other;
        return otherCoord.x == this.x && otherCoord.y == this.y;
    }

    public Coord copy(){
        return new Coord(y, x);
    }

    @Override
    public int hashCode(){
        return x * y + x;
    }

    @Override
    public String toString(){
        return "[" + y + ", " + x + "]";
    }

    public int x(){ return x;}
    public int y(){ return y;}

    public Coord left(){
        return new Coord(y, x - 1);
    }
    public Coord right(){
        return new Coord(y, x + 1);
    }
    public Coord up(){
        return new Coord(y - 1, x);
    }
    public Coord down(){
        return new Coord(y + 1, x);
    }

    public Set<Coord> neighbours(){
        Set<Coord> neighbours = new HashSet<Coord>();
        neighbours.add(left());
        neighbours.add(right());
        neighbours.add(up());
        neighbours.add(down());
        return neighbours;
    }
}
