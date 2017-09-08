package net.javacogito.colorcodeconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(Integer.parseInt(inputLine));
      }
    }
  }



  static String HslToRgb(int hue, int saturation, int lightness){
    double h = hue;
    double s = saturation / 100;
    double l = lightness / 100;
    double c = (1 - Math.abs(2 * l - 1)) * s;
    double x = c * (1 - Math.abs((h / 60)  % 2 - 1));
    double m = l * c / 2;
    double r_, g_, b_;



    StringBuilder sb = new StringBuilder();


    return sb.toString();
  }
}