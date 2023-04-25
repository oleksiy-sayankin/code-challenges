package net.javacogito.closestpairofpoints;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class KataTest {

  @Test
  public void test01_Example() {

    List<Point> points = Arrays.asList(
      new Point(2, 2), //A
      new Point(2, 8), //B
      new Point(5, 5), //C
      new Point(6, 3), //D
      new Point(6, 7), //E
      new Point(7, 4), //F
      new Point(7, 9)  //G
    );

    List<Point> result = Kata.closestPair(points);
    List<Point> expected = Arrays.asList(new Point(6, 3), new Point(7, 4));
    verify(expected, result);
  }

  @Test
  public void test02_TwoPoints() {

    List<Point> points = Arrays.asList(
      new Point(2, 2),
      new Point(6, 3)
    );

    List<Point> result = Kata.closestPair(points);
    List<Point> expected = Arrays.asList(new Point(6, 3), new Point(2, 2));
    verify(expected, result);
  }

  @Test
  public void test03_DuplicatedPoint() {

    List<Point> points = Arrays.asList(
      new Point(2, 2), //A
      new Point(2, 8), //B
      new Point(5, 5), //C
      new Point(5, 5), //C
      new Point(6, 3), //D
      new Point(6, 7), //E
      new Point(7, 4), //F
      new Point(7, 9)  //G
    );

    List<Point> result = Kata.closestPair(points);
    List<Point> expected = Arrays.asList(new Point(5, 5), new Point(5, 5));
    verify(expected, result);
  }

  private void verify(List<Point> expected, List<Point> actual) {
    Comparator<Point> comparator = Comparator.comparingDouble(p -> p.x);

    Assert.assertNotNull("Returned array cannot be null.", actual);
    Assert.assertEquals("Expected exactly two points.", 2, actual.size());
    Assert.assertFalse("Returned points must not be null.", actual.get(0) == null || actual.get(1) == null);

    expected.sort(comparator);
    actual.sort(comparator);
    boolean eq = expected.get(0).x == actual.get(0).x && expected.get(0).y == actual.get(0).y
      && expected.get(1).x == actual.get(1).x && expected.get(1).y == actual.get(1).y;
    Assert.assertTrue(String.format("Expected: %s, Actual: %s", expected, actual), eq);
  }
}
