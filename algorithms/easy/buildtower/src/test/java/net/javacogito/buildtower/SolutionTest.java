package net.javacogito.buildtower;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {
  @Test
  public void basicTests() {
    assertEquals(String.join(",", "*"), String.join(",", Kata.towerBuilder(1)));
    assertEquals(String.join(",", " * ", "***"), String.join(",", Kata.towerBuilder(2)));
    assertEquals(String.join(",", "  *  ", " *** ", "*****"), String.join(",", Kata.towerBuilder(3)));
  }
}