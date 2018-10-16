package net.javacogito.simplesorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Formatter;
import java.util.logging.SimpleFormatter;

public class Main {

  public static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(sort(inputLine));
      }
    }
  }

  private static String sort(String data) {
    String[] rawData = data.split(" ");
    int length = rawData.length;
    double[] values = new double[length];
    for(int i = 0; i <= length - 1; i++){
      values[i] = Double.parseDouble(rawData[i]);
    }
    Arrays.sort(values);
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    DecimalFormat df = new DecimalFormat("0.000");
    for(double value : values){
      if(first){
        first = false;
        sb.append(df.format(value));
      } else {
        sb.append(" ").append(df.format(value));
      }
    }
    return sb.toString();
  }
}