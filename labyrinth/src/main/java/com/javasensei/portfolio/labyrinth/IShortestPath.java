package com.javasensei.portfolio.labyrinth;

import java.util.Collection;

/**
 *  @author oleksiy sayankin
 */
public interface IShortestPath {
    Path shortestPath(Graph graph, INode startNode, INode endNode);
}
