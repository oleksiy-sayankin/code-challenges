package com.javasensei.portfolio.deepforest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.*;

/**
 * @author oleksiy sayankin
 */

public class DataProcessorTest{
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
    public void noExitSimpleTest() throws Exception {
        String output = "i002.out";
        File result = new File(outputDir, output);
        String input = "i002.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void exitExistSimpleTest() throws Exception {
        String output = "i003.out";
        File result = new File(outputDir, output);
        String input = "i003.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        expectedOutputData.setExit(new Coord(70.71067811865477, 70.71067811865477));
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }


    @Test
    public void exitExistComplexTest() throws Exception {
        String output = "i004.out";
        File result = new File(outputDir, output);
        String input = "i004.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        expectedOutputData.setExit(new Coord(80.71067811865477, 81.71067811865474));
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void exitExistComplex2Test() throws Exception {
        String output = "i005.out";
        File result = new File(outputDir, output);
        String input = "i005.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        expectedOutputData.setExit(new Coord(58.098987149150446, 88.45119301206995));
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

}
