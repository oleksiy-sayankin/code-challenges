package net.javacogito.romannumeralshelper;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RomanNumeralsTest {
  @Test
  public void testToRoman() throws Exception {
    assertThat("1 converts to 'I'", RomanNumerals.toRoman(1), is("I"));
    assertThat("2 converts to 'II'", RomanNumerals.toRoman(2), is("II"));
    assertThat("1990 converts to 'MCMXC'", RomanNumerals.toRoman(1990), is("MCMXC"));
    assertThat("2008 converts to 'MMVIII'", RomanNumerals.toRoman(2008), is("MMVIII"));
    assertThat("1666 converts to 'MDCLXVI'", RomanNumerals.toRoman(1666), is("MDCLXVI"));
  }

  @Test
  public void testFromRoman() throws Exception {
    assertThat("'I' converts to 1", RomanNumerals.fromRoman("I"), is(1));
    assertThat("'II' converts to 2", RomanNumerals.fromRoman("II"), is(2));
    assertThat("'MCMXC' converts to 1990", RomanNumerals.fromRoman("MCMXC"), is(1990));
    assertThat("'MMVIII' converts to 2008", RomanNumerals.fromRoman("MMVIII"), is(2008));
    assertThat("'MDCLXVI' converts to 1666", RomanNumerals.fromRoman("MDCLXVI"), is(1666));
  }
}
