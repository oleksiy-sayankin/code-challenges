package net.javacogito.longestpalindrome;

import org.junit.Test;

import static org.junit.Assert.*;

public class SampleTest {
  @Test
  public void basicTests() {
    doTest("a", 1);
    doTest("aa", 2);
    doTest("baa", 2);
    doTest("aab", 2);
    doTest("zyabyz", 1);
    doTest("baabcd", 4);
    doTest("baablkj12345432133d", 9);
  }
  private void doTest(final String s, int expected) {
    assertEquals(expected, Palindromes.longestPalindrome(s));
  }
  @Test
  public void testIsPalindrome(){
    assertTrue(Palindromes.isPalindrome("abba"));
    assertTrue(Palindromes.isPalindrome("123abba321"));
    assertTrue(Palindromes.isPalindrome("a"));
    assertFalse(Palindromes.isPalindrome("jkhkjh786"));
  }
}
