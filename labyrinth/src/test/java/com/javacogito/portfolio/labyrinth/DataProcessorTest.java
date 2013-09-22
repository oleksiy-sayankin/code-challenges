package com.javacogito.portfolio.labyrinth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.*;

/**
 * @author oleksiy sayankin
 */
public class DataProcessorTest {

    private File outputDir;
    @Before
    public void createOutputDir(){
        outputDir = new File("output");
        outputDir.mkdir();
    }

    @After
    public void deleteOutputDir(){
        Util.rmdir(outputDir);
    }

    @Test
    public void mainTest() throws Exception {
        File result = new File(outputDir, "result.out");
        String input = "data002.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));

        String actualResult = Util.readFirstStringFromFile(new BufferedReader(new FileReader(result)));
        String expectedResult = "UUUUUUUUULLDDDDDDDDLL";
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void simpleTest() throws Exception {
        File result = new File(outputDir, "result.out");
        String input = "data003.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));

        String actualResult = Util.readFirstStringFromFile(new BufferedReader(new FileReader(result)));
        String expectedResult = Constant.NO_EXIT;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void simpleOneExitTest() throws Exception {
        File result = new File(outputDir, "result.out");
        String input = "data004.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));

        String actualResult = Util.readFirstStringFromFile(new BufferedReader(new FileReader(result)));
        String expectedResult = "U";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void simpleTwoExitsTest() throws Exception {
        File result = new File(outputDir, "result.out");
        String input = "data005.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));

        String actualResult = Util.readFirstStringFromFile(new BufferedReader(new FileReader(result)));
        String expectedResult = "U";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void complexNoExitTest() throws Exception {
        File result = new File(outputDir, "result.out");
        String input = "data006.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));

        String actualResult = Util.readFirstStringFromFile(new BufferedReader(new FileReader(result)));
        String expectedResult = Constant.NO_EXIT;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void complexOneExitTest() throws Exception {
        File result = new File(outputDir, "result.out");
        String input = "data007.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));

        String actualResult = Util.readFirstStringFromFile(new BufferedReader(new FileReader(result)));
        String expectedResult = "DDDDDLLUUUUULLDDDDDLLUUUUUU";
        Assert.assertEquals(expectedResult, actualResult);
    }
}
