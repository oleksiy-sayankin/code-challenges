package net.javacogito.arrayabsurdity;

import java.io.IOException;
import java.net.URL;

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
  public void findDuplicateTest(){
    int[] numbers01_01 = {1, 1, 3, 4, 5};
    int[] numbers01_02 = {1, 2, 1, 4, 5};
    int[] numbers01_03 = {1, 2, 3, 1, 5};
    int[] numbers01_04 = {1, 2, 3, 4, 1};
    int[] numbers02 = {1, 2, 2, 4, 5};
    int[] numbers03 = {1, 2, 3, 3, 5};
    int[] numbers04 = {1, 2, 3, 4, 4};
    int[] numbers05 = {1, 2, 3, 5, 5};

    Assert.assertEquals(1, Main.findDuplicate(numbers01_01));
    Assert.assertEquals(1, Main.findDuplicate(numbers01_02));
    Assert.assertEquals(1, Main.findDuplicate(numbers01_03));
    Assert.assertEquals(1, Main.findDuplicate(numbers01_04));

    Assert.assertEquals(2, Main.findDuplicate(numbers02));
    Assert.assertEquals(3, Main.findDuplicate(numbers03));
    Assert.assertEquals(4, Main.findDuplicate(numbers04));
    Assert.assertEquals(5, Main.findDuplicate(numbers05));
  }
}