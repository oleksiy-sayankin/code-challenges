package net.javacogito.binomialexpansion;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ExampleTest {

  @Test
  public void testParseN() {
    assertEquals(203, KataSolution.parseN("(r+0)^203"));
    assertEquals(2, KataSolution.parseN("(-12t+43)^2"));
  }

  @Test
  public void testParseX() {
    assertEquals("x", KataSolution.parseX("(x+1)^2"));
    assertEquals("p", KataSolution.parseX("(p-1)^3"));
    assertEquals("f", KataSolution.parseX("(2f+4)^6"));
    assertEquals("a", KataSolution.parseX("(-2a-4)^0"));
    assertEquals("t", KataSolution.parseX("(-12t+43)^2"));
    assertEquals("r", KataSolution.parseX("(r+0)^203"));
    assertEquals("x", KataSolution.parseX("(-x-1)^2"));
  }

  @Test
  public void testParseA() {
    assertEquals(1, KataSolution.parseA("(x+1)^2"));
    assertEquals(1, KataSolution.parseA("(p-1)^3"));
    assertEquals(2, KataSolution.parseA("(2f+4)^6"));
    assertEquals(-2, KataSolution.parseA("(-2a-4)^0"));
    assertEquals(-12, KataSolution.parseA("(-12t+43)^2"));
    assertEquals(1, KataSolution.parseA("(r+0)^203"));
    assertEquals(-1, KataSolution.parseA("(-x-1)^2"));
  }

  @Test
  public void testParseB() {
    assertEquals(1, KataSolution.parseB("(x+1)^2"));
    assertEquals(-1, KataSolution.parseB("(p-1)^3"));
    assertEquals(4, KataSolution.parseB("(2f+4)^6"));
    assertEquals(-4, KataSolution.parseB("(-2a-4)^0"));
    assertEquals(43, KataSolution.parseB("(-12t+43)^2"));
    assertEquals(0, KataSolution.parseB("(r+0)^203"));
    assertEquals(-1, KataSolution.parseB("(-x-1)^2"));
  }


  @Test
  public void testBuildCoefficients() {
    assertArrayEquals(new BigInteger[]{BigInteger.valueOf(1), BigInteger.valueOf(5), BigInteger.valueOf(10), BigInteger.valueOf(10), BigInteger.valueOf(5), BigInteger.valueOf(1)}, KataSolution.buildCoefficients(5));
  }

  @Test
  public void testBPositive() {
    assertEquals("1", KataSolution.expand("(x+1)^0"));
    assertEquals("x+1", KataSolution.expand("(x+1)^1"));
    assertEquals("x^2+2x+1", KataSolution.expand("(x+1)^2"));
  }

  @Test
  public void testBNegative() {
    assertEquals("1", KataSolution.expand("(x-1)^0"));
    assertEquals("x-1", KataSolution.expand("(x-1)^1"));
    assertEquals("x^2-2x+1", KataSolution.expand("(x-1)^2"));
  }

  @Test
  public void testAPositive() {
    assertEquals("625m^4+1500m^3+1350m^2+540m+81", KataSolution.expand("(5m+3)^4"));
    assertEquals("8x^3-36x^2+54x-27", KataSolution.expand("(2x-3)^3"));
    assertEquals("1", KataSolution.expand("(7x-7)^0"));
    assertEquals("81t^2", KataSolution.expand("(9t+0)^2"));
  }

  @Test
  public void testANegative() {
    assertEquals("625m^4-1500m^3+1350m^2-540m+81", KataSolution.expand("(-5m+3)^4"));
    assertEquals("-8k^3-36k^2-54k-27", KataSolution.expand("(-2k-3)^3"));
    assertEquals("1", KataSolution.expand("(-7x-7)^0"));
    assertEquals("-t^5", KataSolution.expand("(-t+0)^5"));
  }
}
