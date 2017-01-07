package net.javacogito.deepforest;

import net.javacogito.math.Settings;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author oleksiy sayankin
 */
public class SinIntervalTest {

  @Test
  public void containsTrue001Test() {
    Sin startSin = new Sin(0.01231, Quadrant.FIRST);
    Sin endSin = new Sin(0.31231, Quadrant.FIRST);
    Sin sin = new Sin(0.2234234, Quadrant.FIRST);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsTrue002Test() {
    Sin startSin = new Sin(0.01231, Quadrant.FIRST);
    Sin endSin = new Sin(0.31231, Quadrant.SECOND);
    Sin sin = new Sin(0.2234234, Quadrant.FIRST);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsTrue003Test() {
    Sin startSin = new Sin(0.01231, Quadrant.FIRST);
    Sin endSin = new Sin(-0.31231, Quadrant.THIRD);
    Sin sin = new Sin(0.2234234, Quadrant.FIRST);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsTrue004Test() {
    Sin startSin = new Sin(-0.01231, Quadrant.THIRD);
    Sin endSin = new Sin(-0.31231, Quadrant.THIRD);
    Sin sin = new Sin(-0.2234234, Quadrant.THIRD);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }


  @Test
  public void containsTrue005Test() {
    Sin startSin = new Sin(0.01231, Quadrant.FIRST);
    Sin endSin = new Sin(-0.31231, Quadrant.THIRD);
    Sin sin = new Sin(-0.2234234, Quadrant.THIRD);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsTrue006Test() {
    Sin startSin = new Sin(0.01231, Quadrant.FIRST);
    Sin endSin = new Sin(-0.00031231, Quadrant.FORTH);
    Sin sin = new Sin(-0.234234, Quadrant.THIRD);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsTrue007Test() {
    Sin startSin = new Sin(-0.01231, Quadrant.FORTH);
    Sin endSin = new Sin(0.31231, Quadrant.FIRST);
    Sin sin = new Sin(0.234234, Quadrant.FIFTH);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }


  @Test
  public void containsTrue008Test() {
    Sin startSin = new Sin(-0.01231, Quadrant.FORTH);
    Sin endSin = new Sin(0.31231, Quadrant.FIRST);
    Sin sin = new Sin(-0.01231, Quadrant.FORTH);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsTrue009Test() {
    Sin startSin = new Sin(-0.01231, Quadrant.FORTH);
    Sin endSin = new Sin(0.31231, Quadrant.FIRST);
    Sin sin = new Sin(0.31231, Quadrant.FIFTH);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsTrue010Test() {
    Sin startSin = new Sin(0.4, Quadrant.FIRST);
    Sin endSin = new Sin(0.1, Quadrant.FIRST);
    Sin sin = new Sin(0.31231, Quadrant.SECOND);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsTrue011Test() {
    Sin startSin = new Sin(0.4, Quadrant.FIRST);
    Sin endSin = new Sin(0.1, Quadrant.FIRST);
    Sin sin = new Sin(-0.31231, Quadrant.FORTH);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsTrue012Test() {
    Sin startSin = new Sin(0.4, Quadrant.FIRST);
    Sin endSin = new Sin(0.1, Quadrant.FIRST);
    Sin sin = new Sin(0.05, Quadrant.FIFTH);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsTrue013Test() {
    Sin startSin = new Sin(-0.01231, Quadrant.FORTH);
    Sin endSin = new Sin(0.31231, Quadrant.FIRST);
    Sin sin = new Sin(0.31231, Quadrant.FIRST);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertTrue(sinInterval.contains(sin));
  }

  @Test
  public void containsFalse001Test() {
    Sin startSin = new Sin(0.01231, Quadrant.FIRST);
    Sin endSin = new Sin(0.31231, Quadrant.FIRST);
    Sin sin = new Sin(0.5234234, Quadrant.FIRST);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertFalse(sinInterval.contains(sin));
  }

  @Test
  public void containsFalse002Test() {
    Sin startSin = new Sin(-0.01231, Quadrant.THIRD);
    Sin endSin = new Sin(-0.31231, Quadrant.THIRD);
    Sin sin = new Sin(-0.7234234, Quadrant.THIRD);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertFalse(sinInterval.contains(sin));
  }

  @Test
  public void containsFalse003Test() {
    Sin startSin = new Sin(0.01231, Quadrant.FIRST);
    Sin endSin = new Sin(-0.31231, Quadrant.THIRD);
    Sin sin = new Sin(-0.00234234, Quadrant.FORTH);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertFalse(sinInterval.contains(sin));
  }

  @Test
  public void containsFalse004Test() {
    Sin startSin = new Sin(-0.01231, Quadrant.FORTH);
    Sin endSin = new Sin(0.31231, Quadrant.FIRST);
    Sin sin = new Sin(0.434234, Quadrant.FIFTH);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertFalse(sinInterval.contains(sin));
  }

  @Test
  public void containsFalse005Test() {
    Sin startSin = new Sin(-0.01231, Quadrant.FORTH);
    Sin endSin = new Sin(0.31231, Quadrant.FIRST);
    Sin sin = new Sin(-0.6434234, Quadrant.FORTH);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertFalse(sinInterval.contains(sin));
  }


  @Test
  public void containsFalse007Test() {
    Sin startSin = new Sin(0.1, Quadrant.FIRST);
    Sin endSin = new Sin(0.4, Quadrant.FIRST);
    Sin sin = new Sin(0.31231, Quadrant.SECOND);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Assert.assertFalse(sinInterval.contains(sin));
  }

  @Test
  public void innerSin001Test() {
    Sin startSin = new Sin(0.1, Quadrant.FIRST);
    Sin endSin = new Sin(0.4, Quadrant.FIRST);
    Sin innerSinExpected = new Sin(0.25, Quadrant.FIRST);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Sin innerSinActual = sinInterval.innerSin();
    Assert.assertEquals(innerSinExpected, innerSinActual);
    Assert.assertTrue(sinInterval.contains(innerSinActual));
    Assert.assertTrue(sinInterval.contains(innerSinExpected));
  }


  @Test
  public void innerSin002Test() {
    Sin startSin = new Sin(0.1, Quadrant.FIRST);
    Sin endSin = new Sin(-0.4, Quadrant.THIRD);
    Sin innerSinExpected = new Sin(0.5, Quadrant.SECOND);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Sin innerSinActual = sinInterval.innerSin();
    Assert.assertEquals(innerSinExpected, innerSinActual);
    Assert.assertTrue(sinInterval.contains(innerSinActual));
    Assert.assertTrue(sinInterval.contains(innerSinExpected));
  }


  @Test
  public void innerSin003Test() {
    Sin startSin = new Sin(0.1, Quadrant.FIRST);
    Sin endSin = new Sin(0.1, Quadrant.SECOND);
    Sin innerSinExpected = new Sin(0.55, Quadrant.FIRST);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Sin innerSinActual = sinInterval.innerSin();
    Assert.assertEquals(innerSinExpected, innerSinActual);
    Assert.assertTrue(sinInterval.contains(innerSinActual));
    Assert.assertTrue(sinInterval.contains(innerSinExpected));
  }

  @Test
  public void innerSin004Test() {
    Sin startSin = new Sin(0.5, Quadrant.FIRST);
    Sin endSin = new Sin(0.3, Quadrant.FIRST);
    Sin innerSinExpected = new Sin(-0.5, Quadrant.THIRD);
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    Sin innerSinActual = sinInterval.innerSin();
    Assert.assertEquals(innerSinExpected, innerSinActual);
    Assert.assertTrue(sinInterval.contains(innerSinActual));
    Assert.assertTrue(sinInterval.contains(innerSinExpected));
  }

  @Test
  public void length001Test() {
    Sin startSin = new Sin(0.3, Quadrant.FIRST);
    Sin endSin = new Sin(0.5, Quadrant.FIRST);
    double lengthExpected = 0.2;
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    double lengthActual = sinInterval.length();
    Assert.assertEquals(lengthExpected, lengthActual, Settings.error());
  }

  @Test
  public void length002Test() {
    Sin startSin = new Sin(0.5, Quadrant.FIRST);
    Sin endSin = new Sin(0.5, Quadrant.SECOND);
    double lengthExpected = 1;
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    double lengthActual = sinInterval.length();
    Assert.assertEquals(lengthExpected, lengthActual, Settings.error());
  }

  @Test
  public void length003Test() {
    Sin startSin = new Sin(0.1, Quadrant.FIRST);
    Sin endSin = new Sin(0.1, Quadrant.SECOND);
    double lengthExpected = 1.8;
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    double lengthActual = sinInterval.length();
    Assert.assertEquals(lengthExpected, lengthActual, Settings.error());
  }

  @Test
  public void length004Test() {
    Sin startSin = new Sin(0.1, Quadrant.FIRST);
    Sin endSin = new Sin(-0.7, Quadrant.THIRD);
    double lengthExpected = 2.6;
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    double lengthActual = sinInterval.length();
    Assert.assertEquals(lengthExpected, lengthActual, Settings.error());
  }

  @Test
  public void length005Test() {
    Sin startSin = new Sin(0.1, Quadrant.FIRST);
    Sin endSin = new Sin(-0.1, Quadrant.FORTH);
    double lengthExpected = 3.8;
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    double lengthActual = sinInterval.length();
    Assert.assertEquals(lengthExpected, lengthActual, Settings.error());
  }

  @Test
  public void length006Test() {
    Sin startSin = new Sin(0.8, Quadrant.FIRST);
    Sin endSin = new Sin(0.3, Quadrant.FIRST);
    double lengthExpected = 3.5;
    SinInterval sinInterval = new SinInterval(startSin, endSin);
    double lengthActual = sinInterval.length();
    Assert.assertEquals(lengthExpected, lengthActual, Settings.error());
  }

  @Test
  public void compareTo001Test() {
    SinInterval[] expectedArray = new SinInterval[8];
    expectedArray[0] = new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(-0.5, Quadrant.FORTH));
    expectedArray[1] = new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.4, Quadrant.FIRST));
    expectedArray[2] = new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.3, Quadrant.FIRST));
    expectedArray[3] = new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.2, Quadrant.FIRST));
    expectedArray[4] = new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.15, Quadrant.FIRST));
    expectedArray[5] = new SinInterval(new Sin(0.5, Quadrant.FIRST), new Sin(-0.5, Quadrant.THIRD));
    expectedArray[6] = new SinInterval(new Sin(0.5, Quadrant.FIRST), new Sin(0.1, Quadrant.SECOND));
    expectedArray[7] = new SinInterval(new Sin(0.5, Quadrant.FIRST), new Sin(0.5, Quadrant.SECOND));

    SinInterval[] actualArray = new SinInterval[8];

    actualArray[7] = new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(-0.5, Quadrant.FORTH));
    actualArray[0] = new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.15, Quadrant.FIRST));
    actualArray[3] = new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.4, Quadrant.FIRST));
    actualArray[2] = new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.3, Quadrant.FIRST));
    actualArray[6] = new SinInterval(new Sin(0.1, Quadrant.FIRST), new Sin(0.2, Quadrant.FIRST));
    actualArray[5] = new SinInterval(new Sin(0.5, Quadrant.FIRST), new Sin(-0.5, Quadrant.THIRD));
    actualArray[4] = new SinInterval(new Sin(0.5, Quadrant.FIRST), new Sin(0.1, Quadrant.SECOND));
    actualArray[1] = new SinInterval(new Sin(0.5, Quadrant.FIRST), new Sin(0.5, Quadrant.SECOND));

    Arrays.sort(actualArray);

    Assert.assertArrayEquals(expectedArray, actualArray);
  }

}
