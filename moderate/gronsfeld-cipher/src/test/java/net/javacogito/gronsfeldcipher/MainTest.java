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

  @Test
  public void buildFullKeyTest(){
    int[] expected1 = {3, 1, 4, 1, 5, 3, 1, 4, 1, 5};
    int[] expected2 = {7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8};
    int[] expected3 = {5, 6, 5};
    Assert.assertArrayEquals(expected1, Main.buildFullKey("31415", "EXALTATION".length()));
    Assert.assertArrayEquals(expected2, Main.buildFullKey("78", "AAAABBBBCCCC".length()));
    Assert.assertArrayEquals(expected3, Main.buildFullKey("56", "AAA".length()));
  }


  @Test
  public void encryptTest(){
    Assert.assertEquals("HYEMYDUMPS", Main.encrypt("31415", "EXALTATION"));
    Assert.assertEquals("M%muxi%dncpqftiix\"", Main.encrypt("45162", "I love challenges!"));
    Assert.assertEquals("Uix!&kotvx3", Main.encrypt("14586214", "Test input."));
  }

  @Test
  public void decryptTest(){
    Assert.assertEquals("EXALTATION", Main.decrypt("31415", "HYEMYDUMPS"));
    Assert.assertEquals("I love challenges!", Main.decrypt("45162", "M%muxi%dncpqftiix\""));
    Assert.assertEquals("Test input.", Main.decrypt("14586214", "Uix!&kotvx3"));
  }

}