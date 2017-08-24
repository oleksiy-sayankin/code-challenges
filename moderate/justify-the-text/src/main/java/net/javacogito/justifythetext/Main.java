package net.javacogito.justifythetext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {

  private static final String EMPTY = "";
  private static final String NEW_LINE = "\n";
  private static final int LINE_SIZE = 80;

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


  static List<Line> parseInput(List<String> rawLines){
    List<Line> result = new LinkedList<>();
    for(String rawLine : rawLines){
      result.add(parseLine(rawLine));
    }
    return result;
  }

  static Line parseLine(String data){
    String[] words = removeEmpty(data.split("[ ]+"));
    String[] spaces = removeEmpty(data.split("[^ ]+"));
    int length = words.length;
    Line result = new Line();
    for(int i = 0; i <= length - 1; i++){
      result.addToken(new Word(words[i]));
      boolean isLast = i == length - 1;
      if(!isLast){
        result.addToken(new Space(spaces[i].length()));
      }
    }
    return result;
  }

  private static String[] removeEmpty(String[] strings){
    String[] result = new String[strings.length - numEmpty(strings)];
    int index = 0;
    for(String string : strings){
      if(!string.isEmpty()){
        result[index] = string;
        index++;
      }
    }
    return result;
  }

  private static int numEmpty(String[] strings){
    int result = 0;
    for(String string : strings){
      if (string.isEmpty()){
        result++;
      }
    }
    return result;
  }

  private static List<Line> splitLines(List<Line> lines){
    List<Line> result = new LinkedList<>();
    int length = lines.size();
    for(int i = 0; i <= length - 1; i++){
      result.addAll(markLastLine(lines.get(i).split()));
    }
    return result;
  }

  private static List<Line> markLastLine(List<Line> lines) {
    List<Line> resultLines = new LinkedList<>(lines);
    int index = 0;
    int length = resultLines.size();
    for(Line line : resultLines){
      boolean isLast = index == length - 1;
      if(isLast){
        line.setLast(true);
      } else {
        line.setLast(false);
      }
      index++;
    }
    return resultLines;
  }

  private static List<Line> justifyLines(List<Line> inputLines){
    List<Line> lines = new LinkedList<>(inputLines);
    int length = lines.size();
    for(int i = 0; i <= length - 1; i++){
      Line line = lines.get(i);
      if(!line.isLast()){
        line.adjust();
      }
    }
    return lines;
  }

  private static String toString(List<Line> lines){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(Line line : lines){
      if(first){
        sb.append(line);
        first = false;
        continue;
      }
      sb.append(NEW_LINE);
      sb.append(line);
    }
    return sb.toString();
  }


  static class Line{
    private List<Printable> tokens = new LinkedList<>();
    private boolean isLast;

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for(Printable token : tokens){
        sb.append(token);
      }
      return sb.toString();
    }

    Printable get(int i){
      return tokens.get(i);
    }

    int length(){
      int result = 0;
      for(Printable token : tokens){
        result += token.length();
      }
      return result;
    }

    List<Line> split(){
      List<Line> result = new LinkedList<>();
      Line line = new Line();
      for (Printable token : tokens){
        if (line.length() + token.length() <= LINE_SIZE){
          line.addToken(token);
        } else {
          line.trim();
          result.add(line);
          line = new Line();
          if(token.isWord()) {
            line.addToken(token);
          }
        }
      }
      line.trim();
      result.add(line);
      return result;
    }

    void trim(){
      if(tokens.get(0).isSpace()){
        tokens.remove(0);
      }
      int lastIndex = tokens.size() - 1;
      if(tokens.get(lastIndex).isSpace()){
        tokens.remove(lastIndex);
      }
    }

    void adjust(){
      if(numWords() <= 1){
        return;
      }
      if(numWords() == 2){
        int spaceSize = LINE_SIZE - wordsLength();
        ((Space)tokens.get(1)).setNum(spaceSize + 1);
        return;
      }
      int spaceSize = (LINE_SIZE - wordsLength()) / (numWords() - 1);
      setAllSpacesSize(spaceSize);
      int numSpacesToAdjust = LINE_SIZE - length();
      int numChangedSpaces = 0;
      for(Printable token : tokens){
        if (numChangedSpaces == numSpacesToAdjust){
          break;
        }
        if(token.isSpace()){
          ((Space)token).setNum(spaceSize + 1);
          numChangedSpaces++;
        }
      }
    }

    private void setAllSpacesSize(int size){
      for(Printable token : tokens){
        if(token.isSpace()){
          ((Space)token).setNum(size);
        }
      }
    }

    private int wordsLength(){
      int result = 0;
      for(Printable token : tokens){
        if(token.isWord()){
          result += token.length();
        }
      }
      return result;
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

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Line line = (Line) o;
      if(tokens.size() != line.tokens.size()){
        return false;
      }
      int length = tokens.size();
      for (int i = 0; i <= length - 1; i++){
        if(!tokens.get(i).equals(line.tokens.get(i))){
          return false;
        }
      }
      return true;
    }

    @Override
    public int hashCode() {
      return tokens.hashCode();
    }

    private void addToken(Printable token){
      tokens.add(token);
    }

    boolean isLast() {
      return isLast;
    }

    void setLast(boolean last) {
      isLast = last;
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

    @Override
    public boolean isSpace() {
      return false;
    }

    @Override
    public int length() {
      return value.length();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Word word = (Word) o;
      return value.equals(word.value);
    }

    @Override
    public int hashCode() {
      return value.hashCode();
    }
  }

  private static class Space implements Printable{
    private int num;

    private Space(int num){
      this.num = num;
    }

    private void setNum(int num) {
      this.num = num;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Space space = (Space) o;
      return num == space.num;
    }

    @Override
    public int hashCode() {
      return num;
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

    @Override
    public boolean isSpace() {
      return true;
    }

    @Override
    public int length() {
      return num;
    }
  }

  interface Printable{
    String toString();
    boolean isWord();
    boolean isSpace();
    int length();
  }
}