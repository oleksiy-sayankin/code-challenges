package com.javacogito.portfolio.labyrinth;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author oleksiy sayankin
 */
public class Path {
    private List<INode> nodes = new LinkedList<INode>();


    public void addNode(INode node){
        nodes.add(node);
    }


    public void clear() {
        nodes.clear();
    }

    public int length() {
        return nodes.size();
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public static Path emptyPath(){
        return new Path();
    }

    public void reverse(){
        List<INode> reversedList = new LinkedList<INode>();
        ListIterator<INode> listIterator = nodes.listIterator(nodes.size());
        while (listIterator.hasPrevious()){
            reversedList.add(listIterator.previous());
        }
        nodes = reversedList;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (INode node : nodes) {
            if (first) {
                first = false;
            } else {
                sb.append("->");
            }
            sb.append(node.id());
        }
        sb.append("}");
        return sb.toString();
    }

    public String toDirectionsString(){
        StringBuilder sb = new StringBuilder();
        int size = nodes.size();
        for(int i = 0; i <= size - 2; i++){
            Coord currentCoord = nodes.get(i).id();
            Coord nextCoord = nodes.get(i + 1).id();
            Direction direction = Direction.find(currentCoord, nextCoord);
            sb.append(direction);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(!(other instanceof Path)){
            return false;
        }
        if(this == other){
            return true;
        }
        Path otherPath = (Path)other;
        return nodes.equals(otherPath.nodes);
    }

    @Override
    public int hashCode(){
        return nodes.hashCode();
    }
}
