package net.javacogito.gronsfeldcipher;

import java.io.IOException;
import java.net.URL;


import org.junit.Assert;
import org.junit.Test;

public class MainTest {

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

  @Test
  public void indexOfTest(){
    Assert.assertEquals(0, Main.indexOf(' '));
    Assert.assertEquals(3, Main.indexOf('#'));
  }

  @Test
  public void shiftTest(){
    Assert.assertEquals('H', Main.shift('E', 3));
    Assert.assertEquals('!', Main.shift('z', 2));
  }

}