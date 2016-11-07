package net.javacogito.labyrinth;

import java.util.*;

/**
 * @author oleksiy sayankin
 */
public class Dijkstra implements IShortestPath {
  private static Dijkstra instance = new Dijkstra();

  private Dijkstra() {
  }

  public static Dijkstra getInstance() {
    return instance;
  }

  @Override
  public Path shortestPath(Graph graph, INode startNode, INode endNode) {
    if (!graph.contains(startNode) || !graph.contains(endNode)) {
      return null;
    }

    if (!graph.isConnected(startNode, endNode)) {
      return null;
    }

    Tree tree = new Tree(graph.getNodes());
    tree.setInfWeight();
    tree.setRoot(startNode.id());
    tree.getRoot().setWeight(0);
    RenewalPriorityQueue<INode> queue = new RenewalPriorityQueue<INode>();
    queue.addAll(tree.getNodes());
    while (!queue.isEmpty()) {
      TreeNode parentNode = (TreeNode) queue.poll();// remove root
      Set<Edge> connectedEdges = graph.getNode(parentNode.id()).connectedEdges();
      for (Edge edge : connectedEdges) {
        Coord relaxNodeId = edge.endNode().id();
        TreeNode relaxNode = (TreeNode) tree.getNode(relaxNodeId);
        int newWeight = edge.getWeight() + parentNode.getWeight();
        if (relaxNode.getWeight() > newWeight) {
          relaxNode.setWeight(newWeight);
          relaxNode.setParentNode(parentNode);
        }
      }
      queue.renew();
    }
    Path path = tree.pathToRoot(tree.getNode(endNode.id()));
    path.reverse();
    return path;
  }
}
