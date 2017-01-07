package net.javacogito.labyrinth;

/**
 * @author oleksiy sayankin
 */
public interface IShortestPath {
  Path shortestPath(Graph graph, INode startNode, INode endNode);
}
