package net.javacogito.romanandarabic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      Aromatic[] aromatics = parseInput(inputLine);
      int length = aromatics.length;
      int value = 0;
      for (int i = 0; i <= length - 2; i++){
        Aromatic current = aromatics[i];
        Aromatic next = aromatics[i + 1];
        value += current.value() * current.sign(next);
      }
      value += aromatics[length - 1].value();
      System.out.println(value);
    }
  }

  private static Aromatic[] parseInput(String inputLine){
    int length = inputLine.length();
    int index = 0;
    Aromatic[] result = new Aromatic[length / 2];

    for(int i = 0; i <= length - 2; i += 2){
      char arabic = inputLine.charAt(i);
      char roman = inputLine.charAt(i + 1);
      result[index] = parseAromatic(arabic, roman);
      index++;
    }
    return result;
  }

  private static Aromatic parseAromatic(char arabic, char roman){
    return new Aromatic(new Arabic(arabic), new Roman(roman));
  }

  private interface Number{
    int value();
  }


  private static class Aromatic implements Number{
    private final int value;
    private final Roman roman;

    public Aromatic(Arabic arabic, Roman roman){
      this.value = roman.value() * arabic.value();
      this.roman = roman;
    }

    @Override
    public int value() {
      return value;
    }

    public int sign(Aromatic other){
      if(other.roman.isGreater(this.roman)){
        return -1;
      }
      return 1;
    }
  }


  private static class Arabic implements Number{
    private final int value;

    public Arabic(char value){
      switch (value){
        case  '0' : this.value = 0; break;
        case  '1' : this.value = 1; break;
        case  '2' : this.value = 2; break;
        case  '3' : this.value = 3; break;
        case  '4' : this.value = 4; break;
        case  '5' : this.value = 5; break;
        case  '6' : this.value = 6; break;
        case  '7' : this.value = 7; break;
        case  '8' : this.value = 8; break;
        case  '9' : this.value = 9; break;
        default: this.value = 0;
      };
    }

    @Override
    public int value() {
      return value;
    }
  }

  private static class Roman implements Number{
    private final int value;

    public Roman(char value){
      switch (value){
        case 'I': this.value = 1; break;
        case 'V': this.value = 5; break;
        case 'X': this.value = 10; break;
        case 'L': this.value = 50; break;
        case 'C': this.value = 100; break;
        case 'D': this.value = 500; break;
        case 'M': this.value = 1000; break;
        default: this.value = 0;
      }
    }

    @Override
    public int value(){
      return value;
    }

    public boolean isGreater(Roman roman){
      return  this.value() > roman.value();
    }
  }
}
