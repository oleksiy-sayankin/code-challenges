package net.javacogito.sumofintegers;

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
  public void maxSumTest(){
    int[] numbers1 = {-10, 2, 3, -2, 0, 5, -15};
    int[] numbers2 = {2, 3, -2, -1, 10};
    int[] numbers3 = {-1, -2, 99, 100};
    int[] numbers4 = {101, 99, -1, -2};
    int[] numbers5 = {1, 2, 3, 4};
    int[] numbers6 = {-1, -2, -3, -4};

    Assert.assertEquals(8, Main.maxSum(numbers1));
    Assert.assertEquals(12, Main.maxSum(numbers2));
    Assert.assertEquals(199, Main.maxSum(numbers3));
    Assert.assertEquals(200, Main.maxSum(numbers4));
    Assert.assertEquals(10, Main.maxSum(numbers5));
    Assert.assertEquals(-1, Main.maxSum(numbers6));
  }
}