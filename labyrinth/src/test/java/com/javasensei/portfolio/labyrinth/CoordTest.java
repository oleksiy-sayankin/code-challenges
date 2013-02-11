package com.javasensei.portfolio.labyrinth;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public class CoordTest {
    @Test
    public void neighboursTest(){
        Coord coord = new Coord(5, 6);
        Set<Coord> expectedNeighbours = new HashSet<Coord>();
        expectedNeighbours.add(new Coord(4, 6));
        expectedNeighbours.add(new Coord(6, 6));
        expectedNeighbours.add(new Coord(5, 5));
        expectedNeighbours.add(new Coord(5, 7));
        Set<Coord> actualNeighbours = coord.neighbours();
        Assert.assertEquals(expectedNeighbours, actualNeighbours);
    }
}
