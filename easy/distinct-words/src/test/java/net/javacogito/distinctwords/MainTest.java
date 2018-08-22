package net.javacogito.distinctwords;

import java.io.IOException;
import java.net.URL;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

  @Test
  public void mainTest() throws IOException {
    long startTime = System.currentTimeMillis();
    URL url = Thread.currentThread().getContextClassLoader().getResource("data001.in");
    String[] input = { url.getPath() };
    Main.main(input);
    long endTime = System.currentTimeMillis();
    long intervalMilliseconds = endTime - startTime;
    long intervalSeconds = intervalMilliseconds / 1000;
    System.out.println("duration = " + intervalSeconds);
  }

  @Test
  public void distinctWordsTest(){
    String input1 = "  aa AA aA; -; b B bb BB .--- CCC ccc CcC  --- //";
    String input2 = "a aa Bb Aa AA, A;a";
    String expectedResult1 = "aa b bb ccc";
    String expectedResult2 = "a aa bb";
    String actualResult1 = Main.distinctWords(Main.removeIgnoredChars(input1));
    String actualResult2 = Main.distinctWords(Main.removeIgnoredChars(input2));
    assertEquals(expectedResult1, actualResult1);
    assertEquals(expectedResult2, actualResult2);
  }
}
