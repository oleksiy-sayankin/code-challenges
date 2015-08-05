package net.javacogito.labyrinth;

import org.junit.Test;
import org.junit.Assert;


/**
 * @author oleksiy sayankin
 */
public class PathTest {
    @Test
    public void toDirectionsStringTest(){
        Path path = new Path();
        path.addNode(new Node(new Coord(0, 0)));
        path.addNode(new Node(new Coord(1, 0)));
        path.addNode(new Node(new Coord(1, 1)));
        path.addNode(new Node(new Coord(1, 2)));
        path.addNode(new Node(new Coord(2, 2)));
        path.addNode(new Node(new Coord(2, 1)));
        path.addNode(new Node(new Coord(2, 0)));
        path.addNode(new Node(new Coord(2, -1)));
        path.addNode(new Node(new Coord(1, -1)));
        String expectedPath = "DRRDLLLU";
        String actualPath = path.toDirectionsString();
        Assert.assertEquals(expectedPath, actualPath);
    }

    @Test
    public void reverseTest(){
        Path path = new Path();
        path.addNode(new Node(new Coord(0, 0)));
        path.addNode(new Node(new Coord(1, 0)));
        path.addNode(new Node(new Coord(1, 1)));
        path.addNode(new Node(new Coord(1, 2)));
        path.addNode(new Node(new Coord(2, 2)));
        path.addNode(new Node(new Coord(2, 1)));
        path.addNode(new Node(new Coord(2, 0)));
        path.addNode(new Node(new Coord(2, -1)));
        path.addNode(new Node(new Coord(1, -1)));
        path.reverse();
        String expectedPath = "DRRRULLU";
        String actualPath = path.toDirectionsString();
        Assert.assertEquals(expectedPath, actualPath);

    }
}
