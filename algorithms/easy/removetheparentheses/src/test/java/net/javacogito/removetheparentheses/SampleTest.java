package net.javacogito.removetheparentheses;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SampleTest {
  @Test
  public void basicTests() {
    for (String[] trial : new String[][]{
      {"example(unwanted thing)example", "exampleexample"},
      {"example(unwanted thing)example", "exampleexample"},
      {"example (unwanted thing) example", "example  example"},
      {"a (bc d)e", "a e"},
      {"a(b(c))", "a"},
      {"hello example (words(more words) here) something", "hello example  something"},
      {"(first group) (second group) (third group)", "  "}
    }
    ) {
      assertEquals(trial[1], Kata.removeParentheses(trial[0]));
    }
  }
}