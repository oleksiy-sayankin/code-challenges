package net.javacogito.deltatime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException, ParseException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(findDelta(inputLine));
      }
    }
  }

  private static String findDelta(String data) throws ParseException {
    String[] rawData = data.split(" ");
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    Date first =  sdf.parse(rawData[0]);
    Date second =  sdf.parse(rawData[1]);
    long interval = Math.abs(second.getTime() - first.getTime());
    return intervalToString(interval);
  }

  private static String intervalToString(long interval){
    final long MILLI_SECONDS_IN_SECOND = 1000;
    final long SECONDS_IN_HOUR = 60 * 60;
    final long SECONDS_IN_MINUTE = 60;
    long hours = interval / MILLI_SECONDS_IN_SECOND / SECONDS_IN_HOUR;
    long minutes = (interval - hours * MILLI_SECONDS_IN_SECOND * SECONDS_IN_HOUR) / MILLI_SECONDS_IN_SECOND / SECONDS_IN_MINUTE;
    long seconds = (interval - hours * MILLI_SECONDS_IN_SECOND * SECONDS_IN_HOUR - minutes * MILLI_SECONDS_IN_SECOND *
      SECONDS_IN_MINUTE) / MILLI_SECONDS_IN_SECOND;
    DecimalFormat df = new DecimalFormat("00");
    return df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
  }
}