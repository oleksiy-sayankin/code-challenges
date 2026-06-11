package net.javacogito.nthreversenumber;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class PalindromeTest {
  @Test
  public void testFixed() {
    assertEquals(new BigInteger("0"), Palindrome.findReverseNumber(1));
    assertEquals(new BigInteger("1"), Palindrome.findReverseNumber(2));
    assertEquals(new BigInteger("9"), Palindrome.findReverseNumber(10));
    assertEquals(new BigInteger("909"), Palindrome.findReverseNumber(100));
  }
}
