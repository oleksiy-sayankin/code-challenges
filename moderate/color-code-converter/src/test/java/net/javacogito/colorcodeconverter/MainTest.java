package net.javacogito.colorcodeconverter;

import java.io.IOException;
import java.net.URL;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

  @Test
  public void mainTest() throws IOException {
    long startTime = System.currentTimeMillis();
    URL url = Thread.currentThread().getContextClassLoader().getResource("data001.in");
    String[] input = {url.getPath()};
    Main.main(input);
    long endTime = System.currentTimeMillis();
    long intervalMilliseconds = endTime - startTime;
    long intervalSeconds = intervalMilliseconds / 1000;
    System.out.println("duration = " + intervalSeconds);
  }

  @Test
  public void HslToRgbTest(){
    int hue1 = 359;
    int saturation1 = 0;
    int lightness1 = 0;

    int hue2 = 117;
    int saturation2 = 59;
    int lightness2 = 79;

    int hue3 = 0;
    int saturation3 = 0;
    int lightness3 = 50;


    String RGB1 = "RGB(0,0,0)";
    String RGB2 = "RGB(173,233,170)";
    String RGB3 = "RGB(128,128,128)";

    Assert.assertEquals(RGB1, Main.HslToRgb(hue1, saturation1, lightness1));
    Assert.assertEquals(RGB2, Main.HslToRgb(hue2, saturation2, lightness2));
    Assert.assertEquals(RGB3, Main.HslToRgb(hue3, saturation3, lightness3));
  }
}