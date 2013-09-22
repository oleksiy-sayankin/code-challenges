package com.javacogito.portfolio.labyrinth;

import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public interface INode {
    Coord id();
    void connectTo(INode node);
    void connectTo(INode node, int weight);
    boolean isConnectedTo(INode node);
    boolean hasId(Coord id);
    INode copyWithoutConnections();
    void setInfWeight();
    int getWeight();
    void setWeight(int  weight);
    Set<INode> connectedNodes();
    Set<Edge> connectedEdges();
    void setVisited(boolean isVisited);
    boolean isVisited();
}
