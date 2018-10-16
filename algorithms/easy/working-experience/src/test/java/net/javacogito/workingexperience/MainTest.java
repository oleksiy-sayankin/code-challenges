package net.javacogito.workingexperience;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;


public class MainTest {
  @Test
  public void lengthInMonthsTest() throws ParseException {
    Main.Interval interval1 = new Main.Interval("Jan 2004-Feb 2004");
    int expectedInterval1 = 2;

    Main.Interval interval2 = new Main.Interval("Jan 2004-Jan 2005");
    int expectedInterval2 = 13;

    Main.Interval interval3 = new Main.Interval("Jan 2004-Jan 2006");
    int expectedInterval3 = 25;

    Main.Interval interval4 = new Main.Interval("Jan 2004-Dec 2004");
    int expectedInterval4 = 12;


    Assert.assertEquals(expectedInterval1, interval1.lengthInMonths());
    Assert.assertEquals(expectedInterval2, interval2.lengthInMonths());
    Assert.assertEquals(expectedInterval3, interval3.lengthInMonths());
    Assert.assertEquals(expectedInterval4, interval4.lengthInMonths());
  }

  @Test
  public void mergeTest() throws ParseException {
    Main.Interval interval11 = new Main.Interval("Jan 2004-Mar 2004");
    Main.Interval interval12 = new Main.Interval("Feb 2004-Apr 2004");
    Main.Interval expectedInterval1 = new Main.Interval("Jan 2004-Apr 2004");

    Main.Interval interval21 = new Main.Interval("Jan 2004-Jun 2004");
    Main.Interval interval22 = new Main.Interval("Feb 2004-Apr 2004");
    Main.Interval expectedInterval2 = new Main.Interval("Jan 2004-Jun 2004");

    Main.Interval interval31 = new Main.Interval("Jan 2004-Jun 2004");
    Main.Interval interval32 = new Main.Interval("Dec 2003-Apr 2005");
    Main.Interval expectedInterval3 = new Main.Interval("Dec 2003-Apr 2005");

    Main.Interval interval41 = new Main.Interval("Jan 2004-Jun 2004");
    Main.Interval interval42 = new Main.Interval("Dec 2003-Feb 2004");
    Main.Interval expectedInterval4 = new Main.Interval("Dec 2003-Jun 2004");

    Assert.assertEquals(expectedInterval1, interval11.merge(interval12));
    Assert.assertEquals(expectedInterval2, interval21.merge(interval22));
    Assert.assertEquals(expectedInterval3, interval31.merge(interval32));
    Assert.assertEquals(expectedInterval4, interval41.merge(interval42));

  }

  @Test
  public void hasIntersectionTest() throws ParseException {
    Main.Interval interval1 = new Main.Interval("Jan 2004-Mar 2004");
    Main.Interval interval2 = new Main.Interval("Feb 2004-Apr 2004");

    Main.Interval interval3 = new Main.Interval("Jan 2002-Mar 2003");
    Main.Interval interval4 = new Main.Interval("Feb 2004-Apr 2004");

    Main.Interval interval5 = new Main.Interval("Jan 2002-Mar 2010");
    Main.Interval interval6 = new Main.Interval("Feb 2004-Apr 2004");

    Main.Interval interval7 = new Main.Interval("Jan 2012-Mar 2013");
    Main.Interval interval8 = new Main.Interval("Feb 2004-Apr 2004");


    Main.Interval interval9 = new Main.Interval("Apr 2013-Aug 2013");
    Main.Interval interval10 = new Main.Interval("Aug 2013-Mar 2014");

    Assert.assertTrue(interval1.hasIntersection(interval2));
    Assert.assertTrue(interval2.hasIntersection(interval1));

    Assert.assertFalse(interval3.hasIntersection(interval4));
    Assert.assertFalse(interval4.hasIntersection(interval3));

    Assert.assertTrue(interval5.hasIntersection(interval6));
    Assert.assertTrue(interval6.hasIntersection(interval5));

    Assert.assertFalse(interval7.hasIntersection(interval8));
    Assert.assertFalse(interval8.hasIntersection(interval7));

    Assert.assertTrue(interval9.hasIntersection(interval10));
    Assert.assertTrue(interval10.hasIntersection(interval9));

  }


  @Test
  public void mainTest() throws IOException, ParseException {
    long startTime = System.currentTimeMillis();
    URL url = Thread.currentThread().getContextClassLoader().getResource("data001.in");
    String[] input = {url.getPath()};
    Main.main(input);
    long endTime = System.currentTimeMillis();
    long intervalMilliseconds = endTime - startTime;
    long intervalSeconds = intervalMilliseconds / 1000;
    System.out.println("duration = " + intervalSeconds);
  }
}
