package net.javacogito.removetheletters;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ChallengeTest {

  @Test
  public void shouldAnswerWithTrue() {
    Assert.assertArrayEquals(new String[]{"w"}, Challenge.removeLetters(new String[]{"s", "t", "r", "i", "n", "g", "w"}, "string"));
    Assert.assertArrayEquals(new String[]{"b", "g", "w"}, Challenge.removeLetters(new String[]{"b", "b", "l", "l", "g", "n", "o", "a", "w"}, "balloon"));
    Assert.assertArrayEquals(new String[]{}, Challenge.removeLetters(new String[]{"d", "b", "t", "e", "a", "i"}, "edabit"));
  }
}
