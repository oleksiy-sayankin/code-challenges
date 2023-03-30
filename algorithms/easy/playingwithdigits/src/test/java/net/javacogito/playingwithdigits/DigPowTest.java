package net.javacogito.playingwithdigits;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DigPowTest {
  @Test
  public void testPow() {
    assertEquals(1, DigPow.pow(2, 0));
    assertEquals(2, DigPow.pow(2, 1));
    assertEquals(4, DigPow.pow(2, 2));
    assertEquals(8, DigPow.pow(2, 3));
  }

  @Test
  public void testSum() {
    assertEquals(89, DigPow.sum(89, 1));
    assertEquals(1390, DigPow.sum(695, 2));
    assertEquals(2360688, DigPow.sum(46288, 3));
  }

  @Test
  public void Test1() {
    assertEquals(1, DigPow.digPow(89, 1));
  }

  @Test
  public void Test2() {
    assertEquals(-1, DigPow.digPow(92, 1));
  }

  @Test
  public void Test3() {
    assertEquals(51, DigPow.digPow(46288, 3));
  }
}
