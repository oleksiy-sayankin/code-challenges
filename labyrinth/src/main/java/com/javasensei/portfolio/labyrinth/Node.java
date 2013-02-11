package com.javasensei.portfolio.labyrinth;

import java.util.*;

/**
 * @author oleksiy sayankin
 */
public class Node implements Comparable, INode{
    private Set<Edge> edges = new HashSet<Edge>();
    private Coord id;
    private int weight;
    private boolean isVisited = false;

    public Node(Coord id){
        this.id = id;
        this.weight = 0;
    }

    public Node(Coord id, int  weight){
        this.id = id;
        this.weight = weight;

    }

    public boolean isVisited(){
        return isVisited;
    }

    public void setVisited(boolean isVisited){
        this.isVisited = isVisited;
    }

    protected Set<Edge> getEdges(){
        return edges;
    }


    @Override
    public void setInfWeight(){
        weight = Integer.MAX_VALUE;
    }

    @Override
    public int getWeight(){
        return weight;
    }

    @Override
    public void setWeight(int  weight){
        this.weight = weight;
    }

    @Override
    public  void connectTo(INode node){
        edges.add(new Edge(this, node));
    }

    @Override
    public  void connectTo(INode node, int weight){
        edges.add(new Edge(this, node, weight));
    }

    @Override
    public Set<INode> connectedNodes(){
        Set<INode> connectedNodesSet = new HashSet<INode>();
        for(Edge edge : edges){
            connectedNodesSet.add(edge.endNode());
        }
        return connectedNodesSet.isEmpty()? Collections.<INode>emptySet() : connectedNodesSet;
    }

    @Override
    public Set<Edge> connectedEdges(){
        return edges;
    }

    @Override
    public boolean hasId(Coord id){
        return this.id.equals(id);
    }

    @Override
    public boolean isConnectedTo(INode node){
        for(Edge edge : edges){
            if(edge.endsWith(node)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other){
        if(other == null){ return false; }
        if(!(other instanceof Node)){ return false; }
        if(other == this) {return  true;}
        Node otherNode = (Node) other;
        return this.id.equals(otherNode.id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{id = ");
        sb.append(id);
        sb.append(", weight = ");
        sb.append(weight);
        sb.append(", connected to ");
        boolean first = true;
        for(Edge edge : edges){
            if(first){
                first = false;
            } else {
                sb.append(",");
            }
            sb.append(edge.endNode().id());
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public Coord id(){
        return id;
    }

    @Override
    public INode copyWithoutConnections(){
        return new Node(id.copy(), weight);
    }

    @Override
    public int compareTo(Object other) {
        Node otherNode =  (Node)other;
        if(weight < otherNode.weight) {
            return -1;
        }
        if(weight > otherNode.weight) {
            return 1;
        }
        return 0;
    }
}
