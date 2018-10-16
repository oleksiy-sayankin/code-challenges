package net.javacogito.trickortreat;

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
        int numVampires = parseNumVampires(inputLine);
        int numZombies = parseNumZombies(inputLine);
        int numWitches = parseNumWitches(inputLine);
        int numHouses = parseNumHouses(inputLine);
        int totalNumCandies = (3 * numVampires + 4 * numZombies + 5 * numWitches) * numHouses;
        System.out.println(totalNumCandies / (numVampires + numZombies + numWitches));
      }
    }
  }


  private static int parseNumVampires(String data)  {
    String[] rawData = data.split(", ");
    return Integer.parseInt(rawData[0].substring(10));
  }

  private static int parseNumZombies(String data)  {
    String[] rawData = data.split(", ");
    return Integer.parseInt(rawData[1].substring(9));
  }

  private static int parseNumWitches(String data)  {
    String[] rawData = data.split(", ");
    return Integer.parseInt(rawData[2].substring(9));
  }

  private static int parseNumHouses(String data)  {
    String[] rawData = data.split(", ");
    return Integer.parseInt(rawData[3].substring(8));
  }
}