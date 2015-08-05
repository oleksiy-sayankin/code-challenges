package net.javacogito.labyrinth;

import java.util.*;

/**
 * @author oleksiy sayankin
 */
public class Graph {
    private Set<INode> nodes = new HashSet<INode>();
    private IShortestPath pathCalculator = Dijkstra.getInstance();

    public void add(INode node) {
        nodes.add(node);
    }

    public void addAll(Set<INode> nodes){
        nodes.addAll(nodes);
    }

    public void connect(INode nodeA, INode nodeB) {
        connect(nodeA, nodeB, 1);
    }

    public void connect(INode nodeA, INode nodeB, int weight) {
        if (nodes.contains(nodeA) && nodes.contains(nodeB)) {
            nodeA.connectTo(nodeB, weight);
            nodeB.connectTo(nodeA, weight);
        }
    }

    public boolean isConnected(Coord idA, Coord idB){
        if(!contains(idA) || !contains(idB)){
            return false;
        }
        return isConnected(getNode(idA), getNode(idB));
    }

    public boolean isConnected(INode nodeA, INode nodeB){
        if(!contains(nodeA) || !contains(nodeB)){
            return false;
        }
        setAllNotVisited();
        markAsVisited(nodeA);
        return nodeB.isVisited();
    }

    private void setAllNotVisited(){
        for(INode node : nodes){
            node.setVisited(false);
        }
    }


    private void markAsVisited(INode node){
        node.setVisited(true);
        if(isAllConnectedNodesVisited(node)){
            return;
        }
        for(Edge edge : node.connectedEdges()){
            if(!edge.endNode().isVisited()) {
                markAsVisited(edge.endNode());
            }
        }
    }

    private boolean isAllConnectedNodesVisited(INode node){
        boolean result = true;
        for(Edge edge : node.connectedEdges()){
            if(!edge.endNode().isVisited()){
                return false;
            }
        }

        return result;
    }

    public INode getNode(Coord id) {
        for (INode node : nodes) {
            if (node.hasId(id)) {
                return node;
            }
        }
        return null;
    }

    public void setInfWeight(){
        for (INode node : nodes){
            node.setInfWeight();
        }
    }

    public boolean contains(INode node){
        return nodes.contains(node);
    }

    public boolean contains(Coord id){
        for (INode node : nodes) {
            if (node.hasId(id)) {
                return true;
            }
        }
        return false;
    }

    public Set<INode> getNodes() {
        return nodes;
    }

    public Path shortestPath(INode nodeA, INode nodeB) {
        return pathCalculator.shortestPath(this, nodeA, nodeB);
    }

    public Path shortestPath(Coord idA, Coord idB) {
        if(contains(idA) && contains(idB)){
            return shortestPath(getNode(idA), getNode(idB));
        }
        return Path.emptyPath();
    }

    public Set<INode> copyNodes(){
        Set<INode> copyNodes = new HashSet<INode>();
        for(INode node : nodes){
            copyNodes.add(node.copyWithoutConnections());
        }
        return copyNodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (INode node : nodes) {
            if (first) {
                first = false;
            } else {
                sb.append("\n");
            }
            sb.append(node);
        }
        return sb.toString();
    }
}
