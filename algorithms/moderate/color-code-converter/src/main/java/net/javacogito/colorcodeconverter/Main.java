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
        System.out.println(convert(inputLine));
      }
    }
  }


  private static String convert(String data){
    if(data.startsWith("(")){
      int startIndex = 1;
      int endIndex = data.indexOf(")");
      String[] rawData = data.substring(startIndex, endIndex).split(",");
      double c = Double.parseDouble(rawData[0]);
      double m = Double.parseDouble(rawData[1]);
      double y = Double.parseDouble(rawData[2]);
      double k = Double.parseDouble(rawData[3]);
      return cmykToRgb(c, m, y, k);
    }

    if(data.startsWith("HSL(")){
      int startIndex = 4;
      int endIndex = data.indexOf(")");
      String[] rawData = data.substring(startIndex, endIndex).split(",");
      int h = Integer.parseInt(rawData[0]);
      int s = Integer.parseInt(rawData[1]);
      int l = Integer.parseInt(rawData[2]);
      return hslToRgb(h, s, l);
    }

    if(data.startsWith("HSV(")){
      int startIndex = 4;
      int endIndex = data.indexOf(")");
      String[] rawData = data.substring(startIndex, endIndex).split(",");
      int h = Integer.parseInt(rawData[0]);
      int s = Integer.parseInt(rawData[1]);
      int v = Integer.parseInt(rawData[2]);
      return hsvToRgb(h, s, v);
    }


    if(data.startsWith("#")){
      String rHex = data.substring(1, 3);
      String gHex = data.substring(3, 5);
      String bHex = data.substring(5, 7);
      return hexToRgb(rHex, gHex, bHex);
    }
    return EMPTY;
  }


  static String hslToRgb(int hue, int saturation, int lightness){
    double h = hue;
    double s = (double) saturation / 100;
    double l = (double) lightness / 100;
    double c = (1 - Math.abs(2 * l - 1)) * s;
    double x = c * (1 - Math.abs((h / 60)  % 2 - 1));
    double m = l - c / 2;
    double r_ = 0 , g_ = 0, b_ = 0;
    double r, g, b;

    if(0 <= h && h < 60){
      r_ = c;
      g_ = x;
      b_ = 0;
    }

    if(60 <= h && h < 120){
      r_ = x;
      g_ = c;
      b_ = 0;
    }

    if(120 <= h && h < 180){
      r_ = 0;
      g_ = c;
      b_ = x;
    }

    if(180 <= h && h < 240){
      r_ = 0;
      g_ = x;
      b_ = c;
    }

    if(240 <= h && h < 300){
      r_ = x;
      g_ = 0;
      b_ = c;
    }

    if(300 <= h && h < 360){
      r_ = c;
      g_ = 0;
      b_ = x;
    }

    r = (r_ + m) * 255;
    g = (g_ + m) * 255;
    b = (b_ + m) * 255;

    int R = (int)Math.round(r);
    int G = (int)Math.round(g);
    int B = (int)Math.round(b);

    return toString(R, G, B);
  }


  static String hsvToRgb(int hue, int saturation, int value){
    double h = hue;
    double s = (double) saturation / 100;
    double v = (double) value / 100;
    double c = v * s;
    double x = c * (1 - Math.abs((h / 60)  % 2 - 1));
    double m = v - c;
    double r_ = 0 , g_ = 0, b_ = 0;
    double r, g, b;

    if(0 <= h && h < 60){
      r_ = c;
      g_ = x;
      b_ = 0;
    }

    if(60 <= h && h < 120){
      r_ = x;
      g_ = c;
      b_ = 0;
    }

    if(120 <= h && h < 180){
      r_ = 0;
      g_ = c;
      b_ = x;
    }

    if(180 <= h && h < 240){
      r_ = 0;
      g_ = x;
      b_ = c;
    }

    if(240 <= h && h < 300){
      r_ = x;
      g_ = 0;
      b_ = c;
    }

    if(300 <= h && h < 360){
      r_ = c;
      g_ = 0;
      b_ = x;
    }

    r = (r_ + m) * 255;
    g = (g_ + m) * 255;
    b = (b_ + m) * 255;

    int R = (int)Math.round(r);
    int G = (int)Math.round(g);
    int B = (int)Math.round(b);

    return toString(R, G, B);
  }


  static String cmykToRgb(double c, double m, double y, double k){
    int R = (int) Math.round(255 * (1- c) * (1 - k));
    int G = (int) Math.round(255 * (1- m) * (1 - k));
    int B = (int) Math.round(255 * (1- y) * (1 - k));
    return toString(R, G, B);
  }

  static String hexToRgb(String hexR, String hexG, String hexB){
    int R = Integer.parseInt(hexR, 16);
    int G = Integer.parseInt(hexG, 16);
    int B = Integer.parseInt(hexB, 16);
    return toString(R, G, B);
  }

  private static String toString(int R, int G, int B){
    StringBuilder sb = new StringBuilder();
    sb.append("RGB(");
    sb.append(R);
    sb.append(",");
    sb.append(G);
    sb.append(",");
    sb.append(B);
    sb.append(")");
    return sb.toString();
  }
}