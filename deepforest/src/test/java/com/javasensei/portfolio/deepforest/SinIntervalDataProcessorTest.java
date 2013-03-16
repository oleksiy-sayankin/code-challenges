package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.Point;
import org.junit.*;

import java.io.*;

/**
 * @author oleksiy sayankin
 */

public class SinIntervalDataProcessorTest {
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
    public void noExitSimple01Test() throws Exception {
        String inputFileName = "i002.in";
        InputData inputData = new InputData();
        inputData.read(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFileName))));
        OutputData actualOutputData = SinIntervalDataProcessor.process(inputData);
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }



    @Test
    public void exitExistSimple003Test() throws Exception {
        String inputFileName = "i003.in";
        InputData inputData = new InputData();
        inputData.read(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFileName))));
        OutputData actualOutputData = SinIntervalDataProcessor.process(inputData);
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        expectedOutputData.setExit(new Point(0, 100));
        Assert.assertFalse(Util.isIntersections(inputData, actualOutputData));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }


    @Test
    public void exitExistComplexTest() throws Exception {
        String inputFileName = "i004.in";
        InputData inputData = new InputData();
        inputData.read(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFileName))));
        OutputData actualOutputData = SinIntervalDataProcessor.process(inputData);
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        expectedOutputData.setExit(new Point(80.71067811865477, 81.71067811865474));
        Assert.assertFalse(Util.isIntersections(inputData, actualOutputData));
        Assert.assertEquals(expectedOutputData, actualOutputData);
        System.out.println(actualOutputData);
    }

    @Test
    public void exitExistComplex2Test() throws Exception {
        String inputFileName = "i005.in";
        InputData inputData = new InputData();
        inputData.read(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFileName))));
        OutputData actualOutputData = SinIntervalDataProcessor.process(inputData);
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        expectedOutputData.setExit(new Point(-91.28090415820634, -28.333333333333307));
        System.out.println(actualOutputData);
        Assert.assertFalse(Util.isIntersections(inputData, actualOutputData));
        Assert.assertEquals(expectedOutputData, actualOutputData);

    }

    @Test
    public void noExitSimple006Test() throws Exception {
        String output = "i006.out";
        File result = new File(outputDir, output);
        String input = "i006.in";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }


    @Test
    public void noExitSimple007Test() throws Exception {
        String output = "i007.out";
        File result = new File(outputDir, output);
        String input = "i007.in";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void noExitSimple008Test() throws Exception {
        String output = "i008.out";
        File result = new File(outputDir, output);
        String input = "i008.in";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test01() throws Exception {
        String output = "Answer01.txt";
        File result = new File(outputDir, output);
        String input = "Input01.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test02() throws Exception {
        String output = "Answer02.txt";
        File result = new File(outputDir, output);
        String input = "Input02.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test03() throws Exception {
        String inputFileName = "Input03.txt";
        InputData inputData = new InputData();
        inputData.read(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(inputFileName))));
        OutputData actualOutputData = SinIntervalDataProcessor.process(inputData);
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        expectedOutputData.setExit(new Point(3732.379887523458, 11192.009984318382));
        System.out.println(actualOutputData);
        Assert.assertFalse(Util.isIntersections(inputData, actualOutputData));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test@Ignore
    public void test04() throws Exception {
        String output = "Answer04.txt";
        File result = new File(outputDir, output);
        String input = "Input04.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test@Ignore
    public void test05() throws Exception {
        String output = "Answer05.txt";
        File result = new File(outputDir, output);
        String input = "Input05.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test@Ignore
    public void test06() throws Exception {
        String output = "Answer06.txt";
        File result = new File(outputDir, output);
        String input = "Input06.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test@Ignore
    public void test07() throws Exception {
        String output = "Answer07.txt";
        File result = new File(outputDir, output);
        String input = "Input07.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }


    @Test@Ignore
    public void test08() throws Exception {
        String output = "Answer08.txt";
        File result = new File(outputDir, output);
        String input = "Input08.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }
}