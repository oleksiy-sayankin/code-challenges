package net.javacogito.theobservedpin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TestObservedPin {

  public static HashMap<String, String[]> expectations = new HashMap<String, String[]>() {
    {
      put("8", new String[]{"5", "7", "8", "9", "0"});
      put("11", new String[]{"11", "21", "41", "12", "22", "42", "14", "24", "44"});
      put("46", new String[]{"13", "15", "16", "19", "43", "45", "46", "49", "53", "55", "56", "59", "73", "75", "76", "79"});
      put("369", new String[]{"236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699"});
    }
  };
  private final static Comparator<String> comp = String::compareTo;

  @Test
  public void testPins() {
    for (String entered : expectations.keySet()) {
      test(entered, Arrays.asList(expectations.get(entered)), ObservedPin.getPINs(entered));
    }
  } // testPins

  private void test(String entered, List<String> expected, List<String> result) {
    result.sort(comp);
    expected.sort(comp);
    Assert.assertEquals("For observed PIN " + entered, expected, result);
  }

} // Test Class
