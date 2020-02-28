package net.javacogito;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChallengeTest {

  @Test
  public void simpleTest() {
   assertEquals("Valid", Challenge.isbn13("9780316066525"));
   assertEquals("Invalid", Challenge.isbn13("0330301824"));
   assertEquals("9780316066525", Challenge.isbn13("0316066524"));
  }
}
