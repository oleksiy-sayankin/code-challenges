package net.javacogito.brokenlcd;

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
      Lcd lcd = parseLsd(inputLine);
      Number number = parseNumber(inputLine);
      if(lcd.canDisplay(number)){
        System.out.println("1");
      } else {
        System.out.println("0");
      }
    }
  }

  private static Lcd parseLsd(String inputLine){
    String[] rawData = inputLine.split(";");
    String[] binaries = rawData[0].split(" ");
    int length = 12;
    byte[] lcd = new byte[length];
    int index = 0;
    for(String binary: binaries){
      lcd[index] = (byte) Integer.parseInt(binary, 2);
      index++;
    }
    return new Lcd(lcd);
  }

  private static Number parseNumber(String inputLine){
    String[] rawData = inputLine.split(";");
    String number = rawData[1];
    return new Number(number.toCharArray());
  }

  private static class Lcd{
    private static final int LCD_LENGTH = 12;
    private final Digit[] digits = new Digit[LCD_LENGTH];

    public Lcd(byte[] bytes){
      int index = 0;
      for(byte symbol : bytes){
        digits[index] = new Digit(symbol);
        index++;
      }
    }

    public boolean canDisplay(Number number){
      int numberLength = number.getLength();
      if (number.getLength() > LCD_LENGTH){
        return false;
      }

      int numFreeDigits = LCD_LENGTH - numberLength;

      for(int i = 0; i <= numFreeDigits; i++){
        if(canDisplayStartingFrom(number, i)){
          return true;
        }
      }
      return false;
    }

    private boolean canDisplayStartingFrom(Number number, int startIndex){
      int digitIndex = startIndex;
      int numberLength = number.getLength();
      for(int i = 0; i <= numberLength  - 1; i++){
        if(!digits[digitIndex].canDisplayHere(number.getDigit(i))){
          return false;
        }
        digitIndex++;
      }
      return true;
    }
  }

  private static class Number{
    private final Digit[] digits;

    public Number(char[] chars){
      int length = chars.length;
      if(hasDecimalMark(chars)){
        length--;
      }
      digits = new Digit[length];
      int index = 0;
      for(char symbol : chars){
        if(isDecimalMark(symbol)){
          digits[index - 1].turnOnDecimalMark();
          continue;
        }
        digits[index] = new Digit(symbol);
        index++;
      }
    }

    public static boolean isDecimalMark(char value){
      return '.'== value;
    }

    public static boolean hasDecimalMark(char[] chars){
      for(char symbol : chars){
        if (isDecimalMark(symbol)){
          return true;
        }
      }
      return false;
    }

    public int getLength(){
      return digits.length;
    }

    public Digit getDigit(int index){
      return digits[index];
    }
  }

  private static class Digit{
    private byte value;

    public Digit(byte value){
      this.value = value;
    }

    public Digit(char value){
      switch (value){
        case '1' : this.value = (byte) 0b01100000; break;
        case '2' : this.value = (byte) 0b11011010; break;
        case '3' : this.value = (byte) 0b11110010; break;
        case '4' : this.value = (byte) 0b01100110; break;
        case '5' : this.value = (byte) 0b10110110; break;
        case '6' : this.value = (byte) 0b10111110; break;
        case '7' : this.value = (byte) 0b11100000; break;
        case '8' : this.value = (byte) 0b11111110; break;
        case '9' : this.value = (byte) 0b11110110; break;
        case '0' : this.value = (byte) 0b11111100; break;
        default: this.value = 0;
      }
    }

    public void turnOnDecimalMark(){
      this.value = (byte) (0b00000001 | value);
    }

    public boolean canDisplayHere(Digit digit){
      return (~value & digit.value) == 0;
    }
  }

}