package net.javacogito.blackcard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(findWinner(parseNames(inputLine), parseNumber(inputLine)));
      }
    }
  }

  private static List<String> parseNames(String data){
   return new ArrayList<>(Arrays.asList(data.split(" \\| ")[0].split(" ")));
  }

  private static int parseNumber(String data){
    return Integer.parseInt(data.split(" \\| ")[1]);
  }

  private static String findWinner(List<String> names, int number){
    int counter = 1;
    int index = 0;
    while(names.size() > 1){
      if (counter <= names.size()){
        index = counter - 1;
      } else {
        index = counter % names.size() == 0 ? names.size() - 1 : counter % names.size() - 1;
      }
      if(counter == number){
        names.remove(index);
        counter = 1;
        continue;
      }
      counter++;
    }
    return names.get(0);
  }
}