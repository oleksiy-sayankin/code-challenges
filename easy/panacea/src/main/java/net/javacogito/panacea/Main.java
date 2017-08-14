package net.javacogito.panacea;

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
        final int VIRUS_RADIX = 16;
        final int ANTI_VIRUS_RADIX = 2;
        System.out.println(isPanacea(parseData(parseVirusData(inputLine), VIRUS_RADIX), parseData(parseAntiVirusData(inputLine), ANTI_VIRUS_RADIX)));
      }
    }
  }

  private static String parseVirusData(String data){
    return data.split(" \\| ")[0];
  }

  private static String parseAntiVirusData(String data){
    return data.split(" \\| ")[1];
  }

  private static String isPanacea(int[] virus, int[] antiVirus){
    if(calcSum(virus) <= calcSum(antiVirus)){
      return "True";
    }
    return "False";
  }

  private static int[] parseData(String data, int radix){
    String[] rawData = data.split(" ");
    int length = rawData.length;
    int result[] = new int[length];
    for(int i = 0; i <= length - 1; i++){
      result[i] = Integer.parseInt(rawData[i], radix);
    }
    return result;
  }

  private static int calcSum(int[] numbers){
    int sum = 0;
    for(int number : numbers){
      sum += number;
    }
    return sum;
  }
}