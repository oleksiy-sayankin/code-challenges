package net.javacogito.labyrinth;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.junit.Assert;

/**
 * @author oleksiy sayankin
 */
public class NodeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NodeTest.class);

    @Before
    public void init(){
        PropertyConfigurator.configure("log4j.properties");
    }

    @Test
    public void isConnectedToTrueTest(){
        Node nodeA = new Node(new Coord(0, 0));
        Node nodeB = new Node(new Coord(2, 4));
        Node nodeC = new Node(new Coord(2, 5));
        Node nodeD = new Node(new Coord(6, 4));
        Node nodeE = new Node(new Coord(2, 42));
        nodeA.connectTo(nodeB);
        nodeC.connectTo(nodeE);
        nodeE.connectTo(nodeD);
        Assert.assertTrue(nodeA.isConnectedTo(nodeB));
        Assert.assertTrue(nodeC.isConnectedTo(nodeE));
        Assert.assertTrue(nodeE.isConnectedTo(nodeD));
    }

    @Test
    public void isConnectedToFalseTest(){
        Node nodeA = new Node(new Coord(0, 0));
        Node nodeB = new Node(new Coord(2, 4));
        Node nodeC = new Node(new Coord(2, 5));
        Node nodeD = new Node(new Coord(6, 4));
        Node nodeE = new Node(new Coord(2, 42));
        nodeA.connectTo(nodeB);
        nodeC.connectTo(nodeE);
        nodeE.connectTo(nodeD);
        Assert.assertFalse(nodeB.isConnectedTo(nodeB));
        Assert.assertFalse(nodeE.isConnectedTo(nodeB));
        Assert.assertFalse(nodeA.isConnectedTo(nodeE));
        Assert.assertFalse(nodeA.isConnectedTo(nodeC));
        Assert.assertFalse(nodeC.isConnectedTo(nodeA));
        Assert.assertFalse(nodeB.isConnectedTo(nodeE));
    }

    @Test
    public void priorityTest(){
        INode nodeA = new Node(new Coord(0, 0), 4);
        INode nodeB = new Node(new Coord(0, 1), 6);
        INode nodeC = new Node(new Coord(1, 0), 1);
        INode nodeD = new Node(new Coord(1, 1), 12);
        RenewalPriorityQueue<INode> queue = new RenewalPriorityQueue<INode>();
        queue.add(nodeA);
        queue.add(nodeB);
        queue.add(nodeC);
        queue.add(nodeD);
        nodeB.setWeight(128);
        nodeC.setWeight(300);
        queue.renew();
        while (!queue.isEmpty()){
            LOGGER.info(queue.poll().toString());
        }
        LOGGER.info(queue.toString());
    }
}
