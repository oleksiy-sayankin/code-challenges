package net.javacogito.justifythetext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(toString(justifyLines(splitLines(parseInput(inputLine)))));
      }
    }
  }


  private static List<Line> parseInput(String data){
    return new LinkedList<>();
  }


  private static List<Line> splitLines(List<Line> lines){
    return new LinkedList<>();
  }

  private static List<Line> justifyLines(List<Line> lines){
    return new LinkedList<>();
  }

  private static String toString(List<Line> lines){
    StringBuilder sb = new StringBuilder();
    for(Line line : lines){
      sb.append(line);
    }
    return sb.toString();
  }


  private class Line implements Printable{
    private List<Printable> tokens = new LinkedList<>();

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for(Printable token : tokens){
        sb.append(token);
      }
      return sb.toString();
    }

    private List<Line> split(){
      List<Line> result = new LinkedList<>();
      return result;
    }

    private void adjust(){
    }

  }

  private class Word implements Printable{
    private final String value;


    private Word(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  private class Space implements Printable{
    private int num;

    public int getNum() {
      return num;
    }

    public void setNum(int num) {
      this.num = num;
    }

    @Override
    public String toString() {
      StringBuilder sb =  new StringBuilder();
      for(int i = 0; i <= num - 1; i++ ){
        sb.append(" ");
      }
      return sb.toString();
    }
  }

  private  interface Printable{
    String toString();
  }

}