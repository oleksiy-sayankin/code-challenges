package com.javasensei.portfolio.labyrinth;

import org.junit.Test;
import org.junit.Assert;

/**
 * @author oleksiy sayankin
 */
public class TreeTest {
    @Test
    public void pathToRootTest(){
        Tree tree = new Tree();
        Coord id0 = new Coord(0, 0);
        Coord id1 = new Coord(0, 1);
        Coord id2 = new Coord(1, 0);
        Coord id3 = new Coord(1, 1);
        Coord id4 = new Coord(2, 0);
        Coord id5 = new Coord(2, 1);
        Coord id6 = new Coord(2, 2);
        tree.add(new TreeNode(id0));
        tree.add(new TreeNode(id1));
        tree.add(new TreeNode(id2));
        tree.add(new TreeNode(id3));
        tree.add(new TreeNode(id4));
        tree.add(new TreeNode(id5));
        tree.add(new TreeNode(id6));
        tree.getNode(id0).connectTo(tree.getNode(id1));
        tree.getNode(id0).connectTo(tree.getNode(id2));
        tree.getNode(id2).connectTo(tree.getNode(id3));
        tree.getNode(id2).connectTo(tree.getNode(id4));
        tree.getNode(id3).connectTo(tree.getNode(id5));
        tree.getNode(id5).connectTo(tree.getNode(id6));
        Path actualPath = tree.pathToRoot(tree.getNode(id6));
        Path expectedPath = new Path();
        expectedPath.addNode(new Node(id6));
        expectedPath.addNode(new Node(id5));
        expectedPath.addNode(new Node(id3));
        expectedPath.addNode(new Node(id2));
        expectedPath.addNode(new Node(id0));
        Assert.assertEquals(expectedPath, actualPath);

    }
}
