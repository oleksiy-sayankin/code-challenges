package net.javacogito.ulamsequence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChallengeTest {

  @Test
  public void basicTest() {
    assertEquals(3, Challenge.ulam(3));
    assertEquals(4, Challenge.ulam(4));
    assertEquals(6, Challenge.ulam(5));
    assertEquals(8, Challenge.ulam(6));
    assertEquals(11, Challenge.ulam(7));
    assertEquals(13, Challenge.ulam(8));
    assertEquals(16, Challenge.ulam(9));
    assertEquals(18, Challenge.ulam(10));
    assertEquals(26, Challenge.ulam(11));
    assertEquals(28, Challenge.ulam(12));
    assertEquals(36, Challenge.ulam(13));
    assertEquals(38, Challenge.ulam(14));
    assertEquals(47, Challenge.ulam(15));
    assertEquals(48, Challenge.ulam(16));
    assertEquals(53, Challenge.ulam(17));
    assertEquals(1856, Challenge.ulam(206));
  }

  @Test
  public void test1() {
    assertEquals(4, Challenge.ulam(4));
  }

  @Test
  public void test2() {
    assertEquals(16, Challenge.ulam(9));
  }

  @Test
  public void test3() {
    assertEquals(180, Challenge.ulam(38));
  }

  @Test
  public void test4() {
    assertEquals(688, Challenge.ulam(99));
  }

  @Test
  public void test5() {
    assertEquals(1856, Challenge.ulam(206));
  }

  @Test
  public void test6() {
    assertEquals(5597, Challenge.ulam(495));
  }

  @Test
  public void test7() {
    assertEquals(6782, Challenge.ulam(577));
  }
}
