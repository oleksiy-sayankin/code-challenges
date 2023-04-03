package net.javacogito.millipedeofwords;

import org.junit.Test;

import static org.junit.Assert.*;

public class MillipedeTest {

  @Test
  public void descriptionTwoWordsTrue() {
    String[] message = new String[]{"excavate", "endure"};
    assertTrue(Millipede.check(message));

    message = new String[]{"aaaccc", "kkkaaa"};
    assertTrue(Millipede.check(message));
  }

  @Test
  public void descriptionThreeWordsTrue() {
    String[] message = new String[]{"a----c", "k----a", "c----e"};
    assertTrue(Millipede.check(message));
  }


  @Test
  public void descriptionTrue() {
    String[] message = new String[]{"excavate", "endure", "desire", "screen", "theater", "excess", "night"};
    assertTrue(Millipede.check(message));
  }

  @Test
  public void descriptionFalse() {
    String[] message = new String[]{"trade", "pole", "view", "grave", "ladder", "mushroom", "president"};
    assertFalse(Millipede.check(message));

    message = new String[]{"traffic", "entertainment", "exotic"};
    assertFalse(Millipede.check(message));

    message = new String[]{"no", "dog", "on", "good"};
    assertFalse(Millipede.check(message));

    message = new String[]{"elephant", "strike", "elephant"};
    assertFalse(Millipede.check(message));

  }
}
