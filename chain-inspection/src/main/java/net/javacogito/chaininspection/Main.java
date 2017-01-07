package net.javacogito.chaininspection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  private static final String GOOD = "GOOD";
  private static final String BAD = "BAD";
  private static final String BEGIN = "BEGIN";
  private static final String END = "END";
  private static final String EMPTY = "EMPTY";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      Pair[] pairs = parsePairs(inputLine);
      System.out.println(checkChain(pairs));
    }
  }

  private static String checkChain(Pair[] pairs){
    Pair start = findStart(pairs);
    if(start.isEmpty()){
      return BAD;
    }

    Pair end = findEnd(pairs);
    if(end.isEmpty()){
      return BAD;
    }

    if(start.isEnd() && pairs.length == 1){
      return GOOD;
    }

    if(start.isEnd() && pairs.length > 1){
      return BAD;
    }

    start.setInChain(true);
    Pair next = start;
    boolean hasNext = true;
    while (hasNext){
      next = findPair(next.getEnd(), pairs);
      if(next.isEmpty()){
        hasNext = false;
      }
      next.setInChain(true);
    }

    if(allIsInChain(pairs)){
      return GOOD;
    }
    return BAD;
  }

  private static boolean allIsInChain(Pair[] pairs){
    for (Pair pair : pairs){
      if (!pair.isInChain()){
        return false;
      }
    }
    return true;
  }

  private static Pair[] parsePairs(String inputLine){
    String[] rawPairs = inputLine.split(";");
    int numPairs = rawPairs.length;
    Pair[] pairs = new Pair[numPairs];
    int i = 0;
    for(String rawPair : rawPairs){
      String[] elems = rawPair.split("-");
      pairs[i] = new Pair(elems[0], elems[1]);
      i++;
    }
    return pairs;
  }


  public static Pair findPair(String start, Pair[] pairs){
    for(Pair pair : pairs){
      if (pair.startsWith(start) && !pair.isInChain()){
        return pair;
      }
    }
    return new EmptyPair();
  }


  private static Pair findStart(Pair[] pairs){
    return findPair(BEGIN, pairs);
  }


  private static Pair findEnd(Pair[] pairs){
    for(Pair pair : pairs){
      if (pair.endsWith(END) && !pair.isInChain()){
        return pair;
      }
    }
    return new EmptyPair();
  }


  public static class Pair{
    private final String start;
    private final String end;
    private boolean isInChain = false;
    protected boolean isEmpty = false;

    public Pair(String start, String end){
      this.start = start;
      this.end = end;
    }

    public boolean startsWith(String start){
      return this.start.equalsIgnoreCase(start);
    }

    public boolean endsWith(String end){
      return this.end.equalsIgnoreCase(end);
    }

    public String getEnd(){
      return end;
    }

    public void setInChain(boolean isInChain){
      this.isInChain = isInChain;
    }

    public boolean isInChain(){
      return isInChain;
    }

    public boolean isEmpty(){
      return isEmpty;
    }

    public boolean isEnd(){
      return END.equalsIgnoreCase(end);
    }
  }

  public static class EmptyPair extends Pair{
    public EmptyPair(){
      super(EMPTY, EMPTY);
      isEmpty = true;
    }
  }
}