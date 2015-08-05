package net.javacogito.longestlines;

import org.junit.Test;


import java.io.*;
import java.net.URL;

public class MainTest {
    @Test
    public void mainTest() throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("data001.in");
        String[] input = {url.getPath()};
        Main.main(input);
    }
}
