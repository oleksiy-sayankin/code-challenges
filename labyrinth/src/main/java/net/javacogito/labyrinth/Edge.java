package net.javacogito.labyrinth;

/**
 * @author oleksiy sayankin
 */
public class Edge {
    private INode startNode;
    private INode endNode;
    private int weight;
    public Edge(INode startNode, INode endNode){
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = 1;
    }

    public Edge(INode startNode, INode endNode, int weight){
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(this == other){
            return true;
        }
        if(!(other instanceof Edge)){
            return false;
        }
        Edge otherEdge = (Edge)other;
        return  startNode.equals(otherEdge.startNode()) && endNode.equals(otherEdge.endNode()) && weight == otherEdge.weight;
    }

    @Override
    public int hashCode(){
        return startNode.hashCode() + endNode.hashCode() + weight;
    }

    @Override
    public String toString(){
        return "(" + startNode.id() + ", " + endNode.id() + ", " + weight + ")";
    }

    public boolean endsWith(INode node){
       return endNode.equals(node);
    }

    public boolean startsWith(INode node){
        return startNode.equals(node);
    }

    public INode startNode(){
        return startNode;
    }

    public INode endNode(){
        return endNode;
    }

    public int getWeight(){
        return weight;
    }
}
