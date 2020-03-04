package net.javacogito.kaprekarsconstant;


import org.junit.Assert;
import org.junit.Test;


public class ChallengeTest {
  @Test
  public void toArrayDescTest() {
    Assert.assertArrayEquals(new Integer[]{7, 3, 2, 1}, Challenge.toArrayDesc(7132));
    Assert.assertArrayEquals(new Integer[]{9, 8, 2, 0}, Challenge.toArrayDesc(289));
    Assert.assertArrayEquals(new Integer[]{3, 2, 0, 0}, Challenge.toArrayDesc(23));
  }

  @Test
  public void toArrayAscTest() {
    Assert.assertArrayEquals(new Integer[]{1, 2, 3, 7}, Challenge.toArrayAsc(7132));
    Assert.assertArrayEquals(new Integer[]{0, 2, 8, 9}, Challenge.toArrayAsc(289));
    Assert.assertArrayEquals(new Integer[]{0, 0, 2, 3}, Challenge.toArrayAsc(23));
  }

  @Test
  public void toIntTest() {
    Assert.assertEquals(7321, Challenge.toInt(new Integer[]{7, 3, 2, 1}));
    Assert.assertEquals(45, Challenge.toInt(new Integer[]{0, 0, 4, 5}));
  }

  @Test
  public void kaprekarTest() {
    Assert.assertEquals(3, Challenge.kaprekar(3524));
    Assert.assertEquals(4, Challenge.kaprekar(495));
    Assert.assertEquals(5, Challenge.kaprekar(6621));
    Assert.assertEquals(4, Challenge.kaprekar(6554));
    Assert.assertEquals(3, Challenge.kaprekar(1234));
    Assert.assertEquals(5, Challenge.kaprekar(10));
  }

}
