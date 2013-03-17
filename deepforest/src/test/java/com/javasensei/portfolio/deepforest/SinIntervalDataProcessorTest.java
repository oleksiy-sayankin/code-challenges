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
        expectedOutputData.setExit(new Point(86.60254037844386, 50.0));
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
        expectedOutputData.setExit(new Point(96.60254037844386, 61.0));
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
        expectedOutputData.setExit(new Point(91.44332774281067, 51.666666666666664));
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
        System.out.println(actualOutputData);
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
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


    @Test
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

    @Test
    public void test09() throws Exception {
        String output = "Answer09.txt";
        File result = new File(outputDir, output);
        String input = "Input09.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test10() throws Exception {
        String output = "Answer10.txt";
        File result = new File(outputDir, output);
        String input = "Input10.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test11() throws Exception {
        String output = "Answer11.txt";
        File result = new File(outputDir, output);
        String input = "Input11.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test12() throws Exception {
        String output = "Answer12.txt";
        File result = new File(outputDir, output);
        String input = "Input12.txt";
        SinIntervalDataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = new OutputData();
        actualOutputData.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }
}
