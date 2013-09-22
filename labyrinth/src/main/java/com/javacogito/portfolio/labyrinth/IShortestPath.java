package com.javacogito.portfolio.labyrinth;

/**
 *  @author oleksiy sayankin
 */
public interface IShortestPath {
    Path shortestPath(Graph graph, INode startNode, INode endNode);
}
