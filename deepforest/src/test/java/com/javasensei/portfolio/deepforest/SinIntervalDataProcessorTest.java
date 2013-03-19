package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.Point;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.*;

import java.io.*;
import java.util.Date;

/**
 * @author oleksiy sayankin
 */

public class SinIntervalDataProcessorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SinIntervalDataProcessorTest.class);
    private File outputDir;
    @Before
    public void createOutputDir(){
        outputDir = new File("output");
        outputDir.mkdir();
        PropertyConfigurator.configure("log4j.properties");
    }

    @After
    public void deleteOutputDir(){
        Util.rmdir(outputDir);
    }

    @Test
    public void noExitSimple01Test() throws Exception {
        LOGGER.info("---noExitSimple01Test");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String inputFileName = "i002.in";
        InputData inputData = new InputData();
        inputData.read(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFileName))));
        OutputData actualOutputData = SinIntervalDataProcessor.process(inputData);
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }



    @Test
    public void exitExistSimple003Test() throws Exception {
        LOGGER.info("---exitExistSimple003Test");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String inputFileName = "i003.in";
        InputData inputData = new InputData();
        inputData.read(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFileName))));
        OutputData actualOutputData = SinIntervalDataProcessor.process(inputData);
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        expectedOutputData.setExit(new Point(86.60254037844386, 50.0));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertFalse(Util.isIntersections(inputData, actualOutputData));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }


    @Test
    public void exitExistComplexTest() throws Exception {
        LOGGER.info("---exitExistComplexTest");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String inputFileName = "i004.in";
        InputData inputData = new InputData();
        inputData.read(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFileName))));
        OutputData actualOutputData = SinIntervalDataProcessor.process(inputData);
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        expectedOutputData.setExit(new Point(96.60254037844386, 61.0));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertFalse(Util.isIntersections(inputData, actualOutputData));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void exitExistComplex2Test() throws Exception {
        LOGGER.info("---exitExistComplex2Test");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String inputFileName = "i005.in";
        InputData inputData = new InputData();
        inputData.read(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFileName))));
        OutputData actualOutputData = SinIntervalDataProcessor.process(inputData);
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        expectedOutputData.setExit(new Point(91.44332774281067, 51.666666666666664));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertFalse(Util.isIntersections(inputData, actualOutputData));
        Assert.assertEquals(expectedOutputData, actualOutputData);

    }

    @Test
    public void noExitSimple006Test() throws Exception {
        LOGGER.info("---noExitSimple006Test");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "i006.out";
        File result = new File(outputDir, output);
        String input = "i006.in";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }


    @Test
    public void noExitSimple007Test() throws Exception {
        LOGGER.info("---noExitSimple007Test");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "i007.out";
        File result = new File(outputDir, output);
        String input = "i007.in";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void noExitSimple008Test() throws Exception {
        LOGGER.info("---noExitSimple008Test");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "i008.out";
        File result = new File(outputDir, output);
        String input = "i008.in";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test01() throws Exception {
        LOGGER.info("---test01");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer01.txt";
        File result = new File(outputDir, output);
        String input = "Input01.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test02() throws Exception {
        LOGGER.info("---test02");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer02.txt";
        File result = new File(outputDir, output);
        String input = "Input02.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test03() throws Exception {
        LOGGER.info("---test03");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String inputFileName = "Input03.txt";
        InputData inputData = new InputData();
        inputData.read(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFileName))));
        OutputData actualOutputData = SinIntervalDataProcessor.process(inputData);
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test04() throws Exception {
        LOGGER.info("---test04");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer04.txt";
        File result = new File(outputDir, output);
        String input = "Input04.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test05() throws Exception {
        LOGGER.info("---test05");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer05.txt";
        File result = new File(outputDir, output);
        String input = "Input05.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test06() throws Exception {
        LOGGER.info("---test06");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer06.txt";
        File result = new File(outputDir, output);
        String input = "Input06.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test07() throws Exception {
        LOGGER.info("---test07");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer07.txt";
        File result = new File(outputDir, output);
        String input = "Input07.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }


    @Test
    public void test08() throws Exception {
        LOGGER.info("---test08");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer08.txt";
        File result = new File(outputDir, output);
        String input = "Input08.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test09() throws Exception {
        LOGGER.info("---test09");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer09.txt";
        File result = new File(outputDir, output);
        String input = "Input09.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test10() throws Exception {
        LOGGER.info("---test10");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer10.txt";
        File result = new File(outputDir, output);
        String input = "Input10.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test11() throws Exception {
        LOGGER.info("---test11");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer11.txt";
        File result = new File(outputDir, output);
        String input = "Input11.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test12() throws Exception {
        LOGGER.info("---test12");
        long startTime = new Date().getTime();
        LOGGER.info("test start: " + new Date(startTime));
        String output = "Answer12.txt";
        File result = new File(outputDir, output);
        String input = "Input12.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        long endTime = new Date().getTime();
        LOGGER.info("test end : " + new Date(endTime) + ", duration = "  + (endTime - startTime) + " ms");
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }
}
