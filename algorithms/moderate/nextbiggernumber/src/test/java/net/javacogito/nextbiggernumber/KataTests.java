package net.javacogito.nextbiggernumber;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KataTests {
  @Test
  public void basicTests() {
    assertEquals(21, Kata.nextBiggerNumber(12));
    assertEquals(321, Kata.nextBiggerNumber(312));
    assertEquals(1111121, Kata.nextBiggerNumber(1111112));
    assertEquals(9999991111121L, Kata.nextBiggerNumber(9999991111112L));
    assertEquals(999999999991111121L, Kata.nextBiggerNumber(999999999991111112L));
    assertEquals(999999999999999921L, Kata.nextBiggerNumber(999999999999999912L));

    assertEquals(414, Kata.nextBiggerNumber(144));
    assertEquals(4144, Kata.nextBiggerNumber(1444));
    assertEquals(41444, Kata.nextBiggerNumber(14444));

    assertEquals(531, Kata.nextBiggerNumber(513));
    assertEquals(2071, Kata.nextBiggerNumber(2017));
    assertEquals(441, Kata.nextBiggerNumber(414));
    assertEquals(414, Kata.nextBiggerNumber(144));
    assertEquals(19009, Kata.nextBiggerNumber(10990));
    assertEquals(-1, Kata.nextBiggerNumber(9));
    assertEquals(-1, Kata.nextBiggerNumber(111));
    assertEquals(-1, Kata.nextBiggerNumber(531));
    assertEquals(987654321, Kata.nextBiggerNumber(987654312));
    assertEquals(9999987654321L, Kata.nextBiggerNumber(9999987654312L));
  }
}