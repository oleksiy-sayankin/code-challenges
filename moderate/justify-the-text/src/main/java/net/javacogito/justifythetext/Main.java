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
    List<String> rawLines = new LinkedList<>();
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        rawLines.add(inputLine);
      }
    }
    System.out.println(toString(justifyLines(splitLines(parseInput(rawLines)))));


  }


  private static List<Line> parseInput(List<String> rawLines){
    List<Line> result = new LinkedList<>();
    for(String rawLine : rawLines){
      result.add(parseLine(rawLine));
    }
    return result;
  }

  private static Line parseLine(String data){
    String[] words = data.split("[ ]+");
    String[] spaces = data.split("[^ ]+");
    int length = words.length;
    Line result = new Line();
    for(int i = 0; i <= length - 1; i++){
      result.addToken(new Word(words[i]));
      boolean isLast = i == length - 1;
      if(!isLast){
        result.addToken(new Space(spaces.length));
      }
    }
    return result;
  }

  private static List<Line> splitLines(List<Line> inputLines){
    List<Line> lines = new LinkedList<>();
    int length = lines.size();
    for(int i = 0; i <= length - 2; i++){
      lines.addAll(inputLines.get(i).split());
    }
    lines.add(inputLines.get(length - 1));
    return lines;
  }

  private static List<Line> justifyLines(List<Line> inputLines){
    List<Line> lines = new LinkedList<>(inputLines);
    int length = lines.size();
    for(int i = 0; i <= length - 2; i++){
      lines.get(i).adjust();
    }
    return lines;
  }

  private static String toString(List<Line> lines){
    StringBuilder sb = new StringBuilder();
    for(Line line : lines){
      sb.append(line);
    }
    return sb.toString();
  }


  private static class Line implements Printable{
    private List<Printable> tokens = new LinkedList<>();

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for(Printable token : tokens){
        sb.append(token);
      }
      return sb.toString();
    }

    @Override
    public boolean isWord() {
      return false;
    }

    private List<Line> split(){
      List<Line> result = new LinkedList<>();
      return result;
    }

    private void adjust(){
      if(numWords() <= 2){
        return;
      }
    }

    private int numWords(){
      int result = 0;
      for(Printable token : tokens){
        if(token.isWord()){
          result++;
        }
      }
      return result;
    }

    private void addToken(Printable token){
      tokens.add(token);
    }
  }

  private static class Word implements Printable{
    private final String value;


    private Word(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }

    @Override
    public boolean isWord() {
      return true;
    }
  }

  private static class Space implements Printable{
    private int num;

    private Space(int num){
      this.num = num;
    }

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

    @Override
    public boolean isWord() {
      return false;
    }
  }

  private  interface Printable{
    String toString();
    boolean isWord();
  }

}