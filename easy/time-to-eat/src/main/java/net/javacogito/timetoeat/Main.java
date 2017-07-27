package net.javacogito.timetoeat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class Main {

  private static final String EMPTY = "";
  private static final SimpleDateFormat SDF = new SimpleDateFormat("HH:mm:ss");

  public static void main(String[] args) throws IOException, ParseException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(toString(sort(parseInput(inputLine))));
      }
    }
  }


  private static Date[] parseInput(String data) throws ParseException {
    String[] rawData = data.split(" ");
    int length = rawData.length;
    Date[] dates = new Date[length];
    for(int i = 0; i <= length - 1; i++){
      dates[i] = SDF.parse(rawData[i]);
    }
    return dates;
  }

  private static Date[] sort(Date[] dates){
    Arrays.sort(dates, new Comparator<Date>() {
      @Override
      public int compare(Date o1, Date o2) {
        return -o1.compareTo(o2);
      }
    });
    return dates;
  }

  private static String toString(Date[] dates){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(Date date : dates){
      if(first){
        sb.append(SDF.format(date));
        first = false;
        continue;
      }
      sb.append(" ");
      sb.append(SDF.format(date));
    }
    return  sb.toString();
  }
}