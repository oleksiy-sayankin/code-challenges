package net.javacogito.simpleortrump;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  private static final String EMPTY = "";
  private static final int J = 11;
  private static final int Q = 12;
  private static final int K = 13;
  private static final int A = 14;

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(findWinner(parseFirstCard(inputLine), parseSecondCard(inputLine), parseTrump(inputLine)));
      }
    }
  }

  private static String findWinner(Card a, Card b, char trump){
    if(a.compareTo(b, trump) > 0) {
      return a.toString();
    }
    if(a.compareTo(b, trump) < 0) {
      return b.toString();
    }
    return a + " " + b;
  }

  private static char parseTrump(String data){
    return data.split(" \\| ")[1].charAt(0);
  }

  private static Card parseFirstCard(String data){
    return parseCard(data.split(" \\| ")[0].split(" ")[0]);
  }

  private static Card parseSecondCard(String data){
    return parseCard(data.split(" \\| ")[0].split(" ")[1]);
  }


  private static Card parseCard(String data){
    int rank;
    char suit;
    char rawData = data.charAt(0);
    if( rawData == '1'){
      rank = 10;
      suit = data.charAt(2);
      return new Card(suit, rank);
    }
    if(rawData >= '2' && rawData <= '9') {
      rank = data.charAt(0) - '2' + 2;
      suit = data.charAt(1);
      return new Card(suit, rank);
    }
    rank = parseRank(rawData);
    suit = data.charAt(1);
    return new Card(suit, rank);
  }

  private static char parseRank(char rank){
    switch (rank){
      case 'J' : return  J;
      case 'Q' : return  Q;
      case 'K' : return  K;
      case 'A' : return  A;
    }
    return 0;
  }

  private static String decode(int code){
    if(code < J){
      return Integer.toString(code);
    }
    switch (code){
      case J : return "J";
      case Q : return "Q";
      case K : return "K";
      case A : return "A";
    }
    return EMPTY;
  }

  private static class Card{
    private final char suit;
    private final int rank;

    private Card(char suit, int rank){
      this.suit = suit;
      this.rank = rank;
    }

    private int compareTo(Card other, char trump){
      if(this.isTrump(trump) && !other.isTrump(trump)){
        return 1;
      }
      if(!this.isTrump(trump) && other.isTrump(trump)){
        return -1;
      }
      return this.rank - other.rank;
    }

    private boolean isTrump(char trump){
      return suit == trump;
    }

    @Override
    public String toString() {
      return decode(rank) + Character.toString(suit);
    }
  }
}