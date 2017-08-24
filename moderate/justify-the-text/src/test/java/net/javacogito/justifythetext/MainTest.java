package net.javacogito.justifythetext;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static net.javacogito.justifythetext.Main.Line;
import static net.javacogito.justifythetext.Main.parseLine;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

  @Test
  public void parseLineTest(){
    String input = "But   he   who would be a great man ought to regard";
    Line line = parseLine(input);
    Assert.assertEquals(input, line.toString());
  }

  @Test
  public void parseInputTest(){
    String inputLine1 = "But   he   who would be a great man ought to regard";
    String inputLine2 = "whether the just act be his own or that of another";
    List<String> rawList = new ArrayList<>();
    rawList.add(inputLine1);
    rawList.add(inputLine2);

    Line line1 = parseLine(inputLine1);
    Line line2 = parseLine(inputLine2);
    List<Line> expectedResult = new LinkedList<>();
    expectedResult.add(line1);
    expectedResult.add(line2);
    List<Line> actualResult = Main.parseInput(rawList);
    int length = expectedResult.size();
    for(int i = 0; i <= length - 1; i++){
      Assert.assertEquals(expectedResult.get(i), actualResult.get(i));
    }
  }


  @Test
  public void adjustTest(){
    String inputLine = "AAA AA AAAAAAA AAAAAAAA AAAAA";
    Line line = Main.parseLine(inputLine);
    line.adjust();
    int[] expectedLength = {3, 14, 2, 14, 7, 14, 8, 13, 5};
    int[] actualLength = new int[9];
    for(int i = 0; i <= 8; i++){
      actualLength[i] = line.get(i).length();
    }
    Assert.assertEquals(80, line.length());
    Assert.assertArrayEquals(expectedLength, actualLength);
    System.out.println(line);
  }

  @Test
  public void splitTest(){
    String inputLine1 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    Line line1 = parseLine(inputLine1);
    List<Line> actualLines1 = line1.split();

    String inputLine2 = "   AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA    AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA   ";
    Line line2 = parseLine(inputLine2);
    List<Line> actualLines2 = line2.split();

    Assert.assertEquals(40, actualLines1.get(0).length());
    Assert.assertEquals(40, actualLines1.get(1).length());

    Assert.assertEquals(40, actualLines2.get(0).length());
    Assert.assertEquals(40, actualLines2.get(1).length());
  }

  @Test
  public void mainTest() throws IOException {
    long startTime = System.currentTimeMillis();
    URL url = Thread.currentThread().getContextClassLoader().getResource("data001.in");
    String[] input = {url.getPath()};
    Main.main(input);
    long endTime = System.currentTimeMillis();
    long intervalMilliseconds = endTime - startTime;
    long intervalSeconds = intervalMilliseconds / 1000;
    System.out.println("duration = " + intervalSeconds);
  }
}