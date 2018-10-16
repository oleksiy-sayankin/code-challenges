package net.javacogito.agedistribution;

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
        System.out.println(distribute(Integer.parseInt(inputLine)));
      }
    }
  }

  private static String distribute(int age){
    if(age < 0 || age > 100){
      return "This program is for humans";
    }
    if(age <= 2){
      return "Still in Mama's arms";
    }
    if(age <= 4){
      return "Preschool Maniac";
    }
    if(age <= 11){
      return "Elementary school";
    }
    if(age <= 14){
      return "Middle school";
    }
    if(age <= 18){
      return "High school";
    }
    if(age <= 22){
      return "College";
    }
    if (age <= 65){
      return "Working for the man";
    }
    return "The Golden Years";
  }
}