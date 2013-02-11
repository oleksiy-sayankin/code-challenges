package com.javasensei.portfolio.labyrinth;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public class GraphTest {
    @Test
    public void shortestPathTest(){
        Coord id0 = new Coord(0, 0);
        Coord id1 = new Coord(0, 1);
        Coord id2 = new Coord(1, 0);
        Coord id3 = new Coord(1, 1);
        Graph graph = new Graph();

        graph.add(new Node(id0));
        graph.add(new Node(id1));
        graph.add(new Node(id2));
        graph.add(new Node(id3));

        graph.connect(graph.getNode(id0), graph.getNode(id1), 1);
        graph.connect(graph.getNode(id0), graph.getNode(id2), 1);
        graph.connect(graph.getNode(id0), graph.getNode(id3), 8);
        graph.connect(graph.getNode(id1), graph.getNode(id3), 3);
        graph.connect(graph.getNode(id2), graph.getNode(id3), 2);

        Path actualShortestPath = graph.shortestPath(graph.getNode(id0), graph.getNode(id3));
        Path expectedShortestPath = new Path();
        expectedShortestPath.addNode(new Node(id0));
        expectedShortestPath.addNode(new Node(id2));
        expectedShortestPath.addNode(new Node(id3));
        Assert.assertEquals(expectedShortestPath, actualShortestPath);
    }

    @Test
    public void shortestPathComplexTest(){
        Coord id0 = new Coord(0, 0);
        Coord id1 = new Coord(0, 1);
        Coord id2 = new Coord(1, 0);
        Coord id3 = new Coord(1, 1);
        Coord id4 = new Coord(2, 0);
        Coord id5 = new Coord(2, 1);
        Coord id6 = new Coord(2, 2);
        Graph graph = new Graph();

        graph.add(new Node(id0));
        graph.add(new Node(id1));
        graph.add(new Node(id2));
        graph.add(new Node(id3));
        graph.add(new Node(id4));
        graph.add(new Node(id5));
        graph.add(new Node(id6));

        graph.connect(graph.getNode(id0), graph.getNode(id1), 1);
        graph.connect(graph.getNode(id0), graph.getNode(id5), 5);
        graph.connect(graph.getNode(id0), graph.getNode(id4), 4);
        graph.connect(graph.getNode(id0), graph.getNode(id3), 8);

        graph.connect(graph.getNode(id1), graph.getNode(id3), 1);
        graph.connect(graph.getNode(id1), graph.getNode(id2), 9);
        graph.connect(graph.getNode(id1), graph.getNode(id0), 1);

        graph.connect(graph.getNode(id2), graph.getNode(id1), 9);
        graph.connect(graph.getNode(id2), graph.getNode(id3), 7);
        graph.connect(graph.getNode(id2), graph.getNode(id6), 1);


        graph.connect(graph.getNode(id3), graph.getNode(id0), 8);
        graph.connect(graph.getNode(id3), graph.getNode(id4), 1);
        graph.connect(graph.getNode(id3), graph.getNode(id2), 7);
        graph.connect(graph.getNode(id3), graph.getNode(id1), 1);

        graph.connect(graph.getNode(id4), graph.getNode(id0), 4);
        graph.connect(graph.getNode(id4), graph.getNode(id3), 1);
        graph.connect(graph.getNode(id4), graph.getNode(id6), 1);

        graph.connect(graph.getNode(id5), graph.getNode(id0), 5);
        graph.connect(graph.getNode(id5), graph.getNode(id6), 6);

        graph.connect(graph.getNode(id6), graph.getNode(id5), 6);
        graph.connect(graph.getNode(id6), graph.getNode(id4), 1);
        graph.connect(graph.getNode(id6), graph.getNode(id2), 1);


        Path actualShortestPath = graph.shortestPath(graph.getNode(id0), graph.getNode(id2));
        Path expectedShortestPath = new Path();
        expectedShortestPath.addNode(new Node(id0));
        expectedShortestPath.addNode(new Node(id1));
        expectedShortestPath.addNode(new Node(id3));
        expectedShortestPath.addNode(new Node(id4));
        expectedShortestPath.addNode(new Node(id6));
        expectedShortestPath.addNode(new Node(id2));
        Assert.assertEquals(expectedShortestPath, actualShortestPath);
    }

    @Test
    public void shortestPathSingleWeightTest(){
        Coord id0 = new Coord(0, 0);
        Coord id1 = new Coord(0, 1);
        Coord id2 = new Coord(1, 0);
        Coord id3 = new Coord(1, 1);
        Coord id4 = new Coord(2, 0);
        Coord id5 = new Coord(2, 1);
        Coord id6 = new Coord(2, 2);
        Graph graph = new Graph();

        graph.add(new Node(id0));
        graph.add(new Node(id1));
        graph.add(new Node(id2));
        graph.add(new Node(id3));
        graph.add(new Node(id4));
        graph.add(new Node(id5));
        graph.add(new Node(id6));

        graph.connect(graph.getNode(id0), graph.getNode(id1));
        graph.connect(graph.getNode(id0), graph.getNode(id5));
        graph.connect(graph.getNode(id0), graph.getNode(id4));
        graph.connect(graph.getNode(id0), graph.getNode(id3));

        graph.connect(graph.getNode(id1), graph.getNode(id3));
        graph.connect(graph.getNode(id1), graph.getNode(id0));

        graph.connect(graph.getNode(id2), graph.getNode(id3));
        graph.connect(graph.getNode(id2), graph.getNode(id6));


        graph.connect(graph.getNode(id3), graph.getNode(id0));
        graph.connect(graph.getNode(id3), graph.getNode(id4));
        graph.connect(graph.getNode(id3), graph.getNode(id2));
        graph.connect(graph.getNode(id3), graph.getNode(id1));

        graph.connect(graph.getNode(id4), graph.getNode(id0));
        graph.connect(graph.getNode(id4), graph.getNode(id3));
        graph.connect(graph.getNode(id4), graph.getNode(id6));

        graph.connect(graph.getNode(id5), graph.getNode(id0));
        graph.connect(graph.getNode(id5), graph.getNode(id6));

        graph.connect(graph.getNode(id6), graph.getNode(id5));
        graph.connect(graph.getNode(id6), graph.getNode(id4));
        graph.connect(graph.getNode(id6), graph.getNode(id2));


        Path actualShortestPath = graph.shortestPath(graph.getNode(id0), graph.getNode(id2));
        Path expectedShortestPath = new Path();
        expectedShortestPath.addNode(new Node(id0));
        expectedShortestPath.addNode(new Node(id3));
        expectedShortestPath.addNode(new Node(id2));
        Assert.assertEquals(expectedShortestPath, actualShortestPath);
    }

    @Test
    public void labyrinthTest() throws Exception {
        Labyrinth labyrinth = new Labyrinth(13, 10);
        labyrinth.addRow(0,  "0011111111");
        labyrinth.addRow(1,  "0010000000");
        labyrinth.addRow(2,  "1110110001");
        labyrinth.addRow(3,  "1010110001");
        labyrinth.addRow(4,  "1010110001");
        labyrinth.addRow(5,  "1010010001");
        labyrinth.addRow(6,  "1010010001");
        labyrinth.addRow(7,  "1010010001");
        labyrinth.addRow(8,  "1010010001");
        labyrinth.addRow(9,  "1010010001");
        labyrinth.addRow(10, "1000010001");
        labyrinth.addRow(11, "0011110001");
        labyrinth.addRow(12, "1111111111");
        Graph graph = labyrinth.toGraph();
        Coord start = new Coord(11, 6);
        Coord end =  new Coord(11, 0);
        Path path = graph.shortestPath(graph.getNode(start), graph.getNode(end));
        String expectedDirections = "UUUUUUUUUULLLDDDDDDDDDLLDL";
        String actualDirections = path.toDirectionsString();
        Assert.assertEquals(expectedDirections, actualDirections);
    }

    @Test
    public void labyrinthComplexTest() throws Exception {
        Labyrinth labyrinth = new Labyrinth(13, 10);
        labyrinth.addRow(0,  "1111111111");
        labyrinth.addRow(1,  "1010000101");
        labyrinth.addRow(2,  "1110110101");
        labyrinth.addRow(3,  "1010110101");
        labyrinth.addRow(4,  "1010110101");
        labyrinth.addRow(5,  "1010010101");
        labyrinth.addRow(6,  "1010010101");
        labyrinth.addRow(7,  "1010010101");
        labyrinth.addRow(8,  "1010010101");
        labyrinth.addRow(9,  "1010010101");
        labyrinth.addRow(10, "1000010101");
        labyrinth.addRow(11, "0011110001");
        labyrinth.addRow(12, "1111111111");
        Graph graph = labyrinth.toGraph();
        Coord start = new Coord(1, 8);
        Coord end =  labyrinth.exits().iterator().next();
        Path path = graph.shortestPath(graph.getNode(start), graph.getNode(end));
        String expectedDirections = "DDDDDDDDDDLLUUUUUUUUUULLLDDDDDDDDDLLDL";
        String actualDirections = path.toDirectionsString();
        Assert.assertEquals(expectedDirections, actualDirections);
    }

    @Test
    public void isConnectedTest() throws Exception {
        Labyrinth labyrinth = new Labyrinth(13, 10);
        labyrinth.addRow(0,  "0001111111");
        labyrinth.addRow(1,  "0001000101");
        labyrinth.addRow(2,  "0001110101");
        labyrinth.addRow(3,  "0001110101");
        labyrinth.addRow(4,  "1111110101");
        labyrinth.addRow(5,  "1010000101");
        labyrinth.addRow(6,  "1010010101");
        labyrinth.addRow(7,  "1010010101");
        labyrinth.addRow(8,  "1010010101");
        labyrinth.addRow(9,  "1010010101");
        labyrinth.addRow(10, "1000010101");
        labyrinth.addRow(11, "0011110001");
        labyrinth.addRow(12, "1111111111");
        Graph graph = labyrinth.toGraph();
        Coord start1 = new Coord(1, 8);
        Coord end1 =  new Coord(0, 0);
        boolean expectedIsConnected1 = false;
        boolean actualIsConnected1 = graph.isConnected(start1, end1);

        Coord start2 = new Coord(1, 8);
        Coord end2 =  new Coord(2, 2);
        boolean expectedIsConnected2 = false;
        boolean actualIsConnected2 = graph.isConnected(start2, end2);

        Coord start3 = new Coord(1, 8);
        Coord end3 =  new Coord(0, 0);
        boolean expectedIsConnected3 = false;
        boolean actualIsConnected3 = graph.isConnected(start3, end3);

        Coord start4 = new Coord(1, 8);
        Coord end4 =  new Coord(5, 1);
        boolean expectedIsConnected4 = true;
        boolean actualIsConnected4 = graph.isConnected(start4, end4);

        Coord start5 = new Coord(11, 8);
        Coord end5 =  new Coord(11, 0);
        boolean expectedIsConnected5 = true;
        boolean actualIsConnected5 = graph.isConnected(start5, end5);

        Assert.assertEquals(expectedIsConnected1, actualIsConnected1);
        Assert.assertEquals(expectedIsConnected2, actualIsConnected2);
        Assert.assertEquals(expectedIsConnected3, actualIsConnected3);
        Assert.assertEquals(expectedIsConnected4, actualIsConnected4);
        Assert.assertEquals(expectedIsConnected5, actualIsConnected5);
    }

    @Test
    public void isConnectedAdditionalTest() throws Exception {
        Labyrinth labyrinth = new Labyrinth(13, 10);
        labyrinth.addRow(0,  "0011111111");
        labyrinth.addRow(1,  "0010000001");
        labyrinth.addRow(2,  "1011010001");
        labyrinth.addRow(3,  "1111010001");
        labyrinth.addRow(4,  "1001010001");
        labyrinth.addRow(5,  "1001010001");
        labyrinth.addRow(6,  "1001010001");
        labyrinth.addRow(7,  "1001010001");
        labyrinth.addRow(8,  "1001010001");
        labyrinth.addRow(9,  "1001010001");
        labyrinth.addRow(10, "1001010001");
        labyrinth.addRow(11, "1001010001");
        labyrinth.addRow(12, "1111111101");
        Graph graph = labyrinth.toGraph();
        Coord start1 = new Coord(3, 4);
        Coord end1 =  new Coord(12, 8);
        boolean expectedIsConnected1 = true;
        boolean actualIsConnected1 = graph.isConnected(start1, end1);
        Assert.assertEquals(expectedIsConnected1, actualIsConnected1);
    }


}
