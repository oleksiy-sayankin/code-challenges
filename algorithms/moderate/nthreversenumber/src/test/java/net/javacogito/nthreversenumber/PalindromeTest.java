package net.javacogito.nthreversenumber;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class PalindromeTest {
  @Test
  public void testFixed() {
//    for (int n = 1; n <= 100; n++) {
//      System.out.println(n + " --> " + Palindrome.findReverseNumber(n));
//    }
//    assertEquals(new BigInteger("0"), Palindrome.findReverseNumber(1));
//    assertEquals(new BigInteger("1"), Palindrome.findReverseNumber(2));
//    assertEquals(new BigInteger("9"), Palindrome.findReverseNumber(10));
//    assertEquals(new BigInteger("909"), Palindrome.findReverseNumber(100));
    assertEquals(new BigInteger("900000000000000000009"), Palindrome.findReverseNumber(100000000000L));
  }
}
