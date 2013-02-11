package com.javasensei.portfolio.labyrinth;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public class Labyrinth {
    private int width;
    private int height;

    byte[][] elem;

    public Labyrinth(int height, int width) {
        assert (height >= 0);
        assert (width >= 0);
        this.width = width;
        this.height = height;
        elem = new byte[height][width];
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public boolean isWall(Coord coord) {
        return contains(coord) && elem[coord.y()][coord.x()] == 1;
    }

    public boolean isEmpty(Coord coord) {
        return contains(coord) && elem[coord.y()][coord.x()] == 0;
    }

    public void setEmpty(Coord coord) {
        if (contains(coord)) {
            elem[coord.y()][coord.x()] = 0;
        }
    }

    public void setWall(Coord coord) {
        if (contains(coord)) {
            elem[coord.y()][coord.x()] = 1;
        }
    }

    public Graph toGraph() {
        Graph graph = new Graph();
        for (int i = 0; i <= height - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                Coord id = new Coord(i, j);
                if (isEmpty(id)) {
                    graph.add(new Node(id));
                }
            }
        }
        for (INode node : graph.getNodes()) {
            for (Coord neighbourId : node.id().neighbours()) {
                if (isEmpty(neighbourId)) {
                    INode neighbourNode = graph.getNode(neighbourId);
                    graph.connect(node, neighbourNode);
                }
            }
        }
        return graph;
    }

    public Set<Coord> exits() {
        Set<Coord> exits = new HashSet<Coord>();
        for (int i = 0; i <= height - 1; i++) {
            Coord leftSide = new Coord(i, 0);
            Coord rightSide = new Coord(i, width - 1);
            if (isEmpty(leftSide)) {
                exits.add(leftSide);
            }
            if (isEmpty(rightSide)) {
                exits.add(rightSide);
            }
        }

        for (int j = 0; j <= width - 1; j++) {
            Coord upSide = new Coord(0, j);
            Coord downSide = new Coord(height - 1, j);
            if (isEmpty(upSide)) {
                exits.add(upSide);
            }
            if (isEmpty(downSide)) {
                exits.add(downSide);
            }
        }

        return exits.isEmpty() ? Collections.<Coord>emptySet() : exits;
    }

    public void addRow(int index, String row) throws Exception {
        for (int j = 0; j <= width - 1; j++) {
            char symbol = row.charAt(j);
            switch (symbol) {
                case '0':
                    setEmpty(new Coord(index, j));
                    break;
                case '1':
                    setWall(new Coord(index, j));
                    break;
                default:
                    throw new Exception("Illegal value for labyrinth element : " + symbol);
            }
        }
    }

    public boolean contains(Coord coord) {
        return coord.y() >= 0 && coord.y() <= height - 1 && coord.x() >= 0 && coord.x() <= width - 1;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Labyrinth)) {
            return false;
        }
        Labyrinth otherLabyrinth = (Labyrinth) other;
        for (int i = 0; i <= height - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                if (elem[i][j] != otherLabyrinth.elem[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i <= height - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                result += elem[i][j];
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = 0; i <= height - 1; i++) {
            if (first) {
                first = false;
            } else {
                sb.append("\n");
            }
            for (int j = 0; j <= width - 1; j++) {
                byte value = elem[i][j];
                switch (value) {
                    case 0:
                        sb.append("0");
                        break;
                    case 1:
                        sb.append("1");
                        break;
                }
            }
        }
        return sb.toString();
    }
}
