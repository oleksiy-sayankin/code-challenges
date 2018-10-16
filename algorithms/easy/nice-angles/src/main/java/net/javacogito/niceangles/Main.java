package net.javacogito.niceangles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";
  private static final int MINUTES_PER_TENTH = 6;
  private static final int SECONDS_IN_MINUTE = 60;
  private static final int SECONDS_PER_HUNDREDS = 36;
  private static final double SECONDS_PER_THOUSANDS = 3.6;
  private static final double SECONDS_PER_TEN_THOUSANDS = 0.36;
  private static final double SECONDS_PER_HUNDRED_THOUSANDS = 0.036;


  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(toMinutesSeconds(inputLine));
      }
    }
  }

  private static String toMinutesSeconds(String angleAsString){
    DecimalFormat df = new DecimalFormat("#.000000");
    DecimalFormat dfOutput = new DecimalFormat("00");
    double angle = Double.parseDouble(angleAsString);
    String formattedAngleAsString = df.format(angle);
    String fractionPart = formattedAngleAsString.substring(formattedAngleAsString.indexOf("."));
    int integer = (int)Math.floor(angle);
    int tenth = Integer.parseInt(Character.toString(fractionPart.charAt(1)));
    int hundredths = Integer.parseInt(Character.toString(fractionPart.charAt(2)));;
    int thousandth = Integer.parseInt(Character.toString(fractionPart.charAt(3)));;
    int tenthThousandth = Integer.parseInt(Character.toString(fractionPart.charAt(4)));;
    int hundredThousandth = Integer.parseInt(Character.toString(fractionPart.charAt(5)));;
    int minutes = tenth * MINUTES_PER_TENTH;
    int seconds = (int) Math.floor(hundredths * SECONDS_PER_HUNDREDS + thousandth * SECONDS_PER_THOUSANDS +
            tenthThousandth * SECONDS_PER_TEN_THOUSANDS + hundredThousandth * SECONDS_PER_HUNDRED_THOUSANDS);
    if (seconds >= SECONDS_IN_MINUTE){
      minutes += seconds / SECONDS_IN_MINUTE;
      seconds = seconds - (seconds / SECONDS_IN_MINUTE) * SECONDS_IN_MINUTE;
    }
    return Integer.toString(integer) + "." + dfOutput.format(minutes) + "'" + dfOutput.format(seconds) + "\"";
  }
}