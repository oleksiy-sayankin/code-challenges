package com.javasensei.portfolio.labyrinth;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */

public class MainApp {
    public static void main(String[] args) throws Exception {
        InputData.getInstance().read(new BufferedReader(new FileReader(new File("data.in"))));
        Labyrinth labyrinth = InputData.getInstance().getLabyrinth();
        Coord initPos = InputData.getInstance().getInitPos();
        Graph graph = labyrinth.toGraph();
        Set<Coord> exits = labyrinth.exits();
        Set<Path> resultPathSet = new HashSet<Path>();
        for (Coord exit : exits) {
            Path path = graph.shortestPath(initPos, exit);
            if (path != null) {
                resultPathSet.add(path);
            }
        }
        Path result = shortest(resultPathSet);
        OutputData.getInstance().setPath(result);
        OutputData.getInstance().write(new BufferedWriter(new FileWriter(new File("result.out"))));
    }

    private static Path shortest(Set<Path> pathSet) {
        Path shortestPath = Path.emptyPath();
        int minLength = Integer.MAX_VALUE;
        for (Path currentPath : pathSet) {
            if (currentPath.length() < minLength) {
                minLength = currentPath.length();
                shortestPath = currentPath;
            }
        }
        return shortestPath;
    }
}
