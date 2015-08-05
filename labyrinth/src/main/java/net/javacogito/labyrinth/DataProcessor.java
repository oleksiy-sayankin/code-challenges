package net.javacogito.labyrinth;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public final class DataProcessor {

    private DataProcessor(){}

    public static void process(Reader inputFileReader, Writer outputFileWriter) throws Exception {
        InputData.getInstance().read(inputFileReader);
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
        OutputData.getInstance().write(outputFileWriter);
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
