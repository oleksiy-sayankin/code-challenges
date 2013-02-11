package com.javasensei.portfolio.labyrinth;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */

public class MainApp {
    public static void main(String[] args) throws Exception {
        DataProcessor.process(new BufferedReader(new FileReader(new File("data.in"))), new BufferedWriter(new FileWriter(new File("result.out"))));
    }

}
