package net.javacogito.labyrinth;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public class LabyrinthTest {
    @Test
    public void existTest() throws Exception {
        Labyrinth labyrinth = new Labyrinth(13, 10);
        labyrinth.addRow(0,  "0011111111");
        labyrinth.addRow(1,  "0010010000");
        labyrinth.addRow(2,  "1110010001");
        labyrinth.addRow(3,  "1000010001");
        labyrinth.addRow(4,  "1000010001");
        labyrinth.addRow(5,  "1000010001");
        labyrinth.addRow(6,  "1000010001");
        labyrinth.addRow(7,  "1000010001");
        labyrinth.addRow(8,  "1000010001");
        labyrinth.addRow(9,  "1000010001");
        labyrinth.addRow(10, "1000010001");
        labyrinth.addRow(11, "0011110001");
        labyrinth.addRow(12, "1111111111");
        Set<Coord> expectedExits =  new HashSet<Coord>();
        expectedExits.add(new Coord(11, 0));
        expectedExits.add(new Coord(1, 0));
        expectedExits.add(new Coord(0, 0));
        expectedExits.add(new Coord(0, 1));
        expectedExits.add(new Coord(1, 9));
        Set<Coord> actualExits = labyrinth.exits();
        Assert.assertEquals(expectedExits, actualExits);
    }

    @Test
    public void toGraphTest() throws Exception {
        Labyrinth labyrinth = new Labyrinth(3, 3);
        labyrinth.addRow(0,  "111");
        labyrinth.addRow(1,  "101");
        labyrinth.addRow(2,  "101");
        Graph graph = labyrinth.toGraph();
        Assert.assertTrue(graph.contains(new Node(new Coord(1, 1))));
        Assert.assertTrue(graph.contains(new Node(new Coord(2, 1))));
        Assert.assertFalse(graph.contains(new Node(new Coord(0, 0))));
        Assert.assertFalse(graph.contains(new Node(new Coord(1, 0))));
        Assert.assertFalse(graph.contains(new Node(new Coord(2, 2))));
        Assert.assertTrue(graph.getNode(new Coord(1, 1)).isConnectedTo(new Node(new Coord(2, 1))));
        Assert.assertTrue(graph.getNode(new Coord(2, 1)).isConnectedTo(new Node(new Coord(1, 1))));
    }

    @Test
    public void toGraphComplexTest() throws Exception {
        Labyrinth labyrinth = new Labyrinth(5, 6);
        labyrinth.addRow(0,  "111001");
        labyrinth.addRow(1,  "101001");
        labyrinth.addRow(2,  "101001");
        labyrinth.addRow(3,  "100001");
        labyrinth.addRow(4,  "111111");
        Graph graph = labyrinth.toGraph();
        Assert.assertTrue(graph.contains(new Node(new Coord(1, 1))));
        Assert.assertTrue(graph.contains(new Node(new Coord(2, 1))));
        Assert.assertTrue(graph.contains(new Node(new Coord(0, 3))));
        Assert.assertTrue(graph.contains(new Node(new Coord(0, 4))));
        Assert.assertTrue(graph.contains(new Node(new Coord(1, 3))));
        Assert.assertTrue(graph.contains(new Node(new Coord(1, 4))));
        Assert.assertTrue(graph.contains(new Node(new Coord(2, 3))));
        Assert.assertTrue(graph.contains(new Node(new Coord(2, 4))));
        Assert.assertTrue(graph.contains(new Node(new Coord(3, 3))));
        Assert.assertTrue(graph.contains(new Node(new Coord(3, 4))));

        Assert.assertFalse(graph.contains(new Node(new Coord(4, 0))));
        Assert.assertFalse(graph.contains(new Node(new Coord(4, 1))));
        Assert.assertFalse(graph.contains(new Node(new Coord(4, 2))));
        Assert.assertFalse(graph.contains(new Node(new Coord(4, 3))));
        Assert.assertFalse(graph.contains(new Node(new Coord(4, 4))));
        Assert.assertFalse(graph.contains(new Node(new Coord(4, 5))));

        Assert.assertTrue(graph.getNode(new Coord(1, 1)).isConnectedTo(new Node(new Coord(2, 1))));
        Assert.assertTrue(graph.getNode(new Coord(0, 3)).isConnectedTo(new Node(new Coord(0, 4))));
        Assert.assertTrue(graph.getNode(new Coord(1, 3)).isConnectedTo(new Node(new Coord(1, 4))));
        Assert.assertTrue(graph.getNode(new Coord(2, 3)).isConnectedTo(new Node(new Coord(2, 4))));
        Assert.assertTrue(graph.getNode(new Coord(3, 3)).isConnectedTo(new Node(new Coord(3, 4))));

        Assert.assertFalse(graph.getNode(new Coord(3, 3)).isConnectedTo(new Node(new Coord(1, 1))));
        Assert.assertFalse(graph.getNode(new Coord(1, 1)).isConnectedTo(new Node(new Coord(0, 3))));
        Assert.assertFalse(graph.getNode(new Coord(3, 3)).isConnectedTo(new Node(new Coord(0, 4))));
        Assert.assertFalse(graph.getNode(new Coord(3, 3)).isConnectedTo(new Node(new Coord(1, 3))));
    }
}
