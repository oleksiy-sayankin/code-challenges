package net.javacogito.characterwithlongest;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SolutionTest {
  @Test
  public void exampleTests() {
    assertArrayEquals(new Object[]{"a", 4}, Solution.longestRepetition("aaaabb"));
    assertArrayEquals(new Object[]{"a", 4}, Solution.longestRepetition("bbbaaabaaaa"));
    assertArrayEquals(new Object[]{"u", 3}, Solution.longestRepetition("cbdeuuu900"));
    assertArrayEquals(new Object[]{"b", 5}, Solution.longestRepetition("abbbbb"));
    assertArrayEquals(new Object[]{"a", 2}, Solution.longestRepetition("aabb"));
    assertArrayEquals(new Object[]{"", 0}, Solution.longestRepetition(""));
    assertArrayEquals(new Object[]{"a", 3}, Solution.longestRepetition("aaabbbccc"));
    assertArrayEquals(new Object[]{"x", 3}, Solution.longestRepetition("xxxaaabbbccc"));
    assertArrayEquals(new Object[]{"x", 3}, Solution.longestRepetition("iiaaxxxaaabbbccc"));
    assertArrayEquals(new Object[]{"x", 3}, Solution.longestRepetition("iiaa9869898xxxaaa987999bbbccc"));
    assertArrayEquals(new Object[]{"a", 9}, Solution.longestRepetition("aaaaaaaaabbb"));
    assertArrayEquals(new Object[]{"5", 7}, Solution.longestRepetition("335535533222225555555"));
    assertArrayEquals(new Object[]{"a", 3}, Solution.longestRepetition("aa1aaa1aa11aaa"));
    assertArrayEquals(new Object[]{"a", 1}, Solution.longestRepetition("a"));
    assertArrayEquals(new Object[]{"a", 1}, Solution.longestRepetition("a1"));
  }
}