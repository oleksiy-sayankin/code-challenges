package com.javacogito.portfolio.anagrammer;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * @author oleksiy sayankin
 */


public class InputData {
    private String source;
    private String destination;

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public void read(Reader reader) throws Exception {
        BufferedReader br = new BufferedReader(reader);
        source = br.readLine();
        destination = br.readLine();
    }
}
