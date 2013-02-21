package com.javasensei.portfolio.deepforest;

import org.junit.*;

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
    public void noExitSimple01Test() throws Exception {
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
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void noExitSimple006Test() throws Exception {
        String output = "i006.out";
        File result = new File(outputDir, output);
        String input = "i006.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }


    @Test
    public void noExitSimple007Test() throws Exception {
        String output = "i007.out";
        File result = new File(outputDir, output);
        String input = "i007.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void noExitSimple008Test() throws Exception {
        String output = "i008.out";
        File result = new File(outputDir, output);
        String input = "i008.in";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test01() throws Exception {
        String output = "Answer01.txt";
        File result = new File(outputDir, output);
        String input = "Input01.txt";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test02() throws Exception {
        String output = "Answer02.txt";
        File result = new File(outputDir, output);
        String input = "Input02.txt";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test03() throws Exception {
        String output = "Answer03.txt";
        File result = new File(outputDir, output);
        String input = "Input03.txt";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test04() throws Exception {
        String output = "Answer04.txt";
        File result = new File(outputDir, output);
        String input = "Input04.txt";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test05() throws Exception {
        String output = "Answer05.txt";
        File result = new File(outputDir, output);
        String input = "Input05.txt";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test06() throws Exception {
        String output = "Answer06.txt";
        File result = new File(outputDir, output);
        String input = "Input06.txt";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }

    @Test
    public void test07() throws Exception {
        String output = "Answer07.txt";
        File result = new File(outputDir, output);
        String input = "Input07.txt";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(false);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }


    @Ignore
    public void test08() throws Exception {
        String output = "Answer08.txt";
        File result = new File(outputDir, output);
        String input = "Input08.txt";
        DataProcessor.process(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(input))),
                new BufferedWriter(new FileWriter(result)));
        OutputData expectedOutputData = new OutputData();
        expectedOutputData.setForestIsDeep(true);
        OutputData actualOutputData = Util.read(new FileReader(result));
        Assert.assertEquals(expectedOutputData, actualOutputData);
    }
}
