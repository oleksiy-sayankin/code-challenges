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

  @Test
  public void HsvToRgbTest(){
    int hue1 = 276;
    int saturation1 = 33;
    int lightness1 = 7;

    int hue2 = 117;
    int saturation2 = 59;
    int lightness2 = 79;

    int hue3 = 0;
    int saturation3 = 0;
    int lightness3 = 50;


    String RGB1 = "RGB(15,12,18)";
    String RGB2 = "RGB(89,201,83)";
    String RGB3 = "RGB(128,128,128)";

    Assert.assertEquals(RGB1, Main.HsvToRgb(hue1, saturation1, lightness1));
    Assert.assertEquals(RGB2, Main.HsvToRgb(hue2, saturation2, lightness2));
    Assert.assertEquals(RGB3, Main.HsvToRgb(hue3, saturation3, lightness3));
  }


  @Test
  public void CmykToRgbTest(){
    double c1 = 0.56;
    double m1 = 0.94;
    double y1 = 0.21;
    double k1 = 0.02;

    double c2 = 0.31;
    double m2 = 0.4;
    double y2 = 0.7;
    double k2 = 0.07;


    String RGB1 = "RGB(110,15,197)";
    String RGB2 = "RGB(164,142,71)";

    Assert.assertEquals(RGB1, Main.CmykToRgb(c1, m1, y1, k1));
    Assert.assertEquals(RGB2, Main.CmykToRgb(c2, m2, y2, k2));
  }

  @Test
  public void HexToRgbTest(){
    String hexR = "cf";
    String hexG = "a9";
    String hexB = "c4";
    String RGB = "RGB(207,169,196)";
    Assert.assertEquals(RGB, Main.HexToRgb(hexR, hexG, hexB));
  }
}