package net.javacogito.knightmoves;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  private static final String EMPTY = "";
  private static final char ERROR = (char) -1;

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(findKnightMoves(inputLine));
      }
    }
  }

  private static String findKnightMoves(String data){
    char col = data.charAt(0);
    char row = data.charAt(1);
    List<String> moves = movesToList(removeIncorrect(findAllKnightMoves(col, row)));
    Collections.sort(moves);
    return toString(moves);
  }


  private static char[][] findAllKnightMoves(char col, char row){
    char[][] allMoves = new char[8][2];
    allMoves[0][0] = (char) (col - 1);
    allMoves[0][1] = (char) (row - 2);

    allMoves[1][0] = (char) (col - 2);
    allMoves[1][1] = (char) (row - 1);

    allMoves[2][0] = (char) (col - 1);
    allMoves[2][1] = (char) (row + 2);

    allMoves[3][0] = (char) (col - 2);
    allMoves[3][1] = (char) (row + 1);

    allMoves[4][0] = (char) (col + 1);
    allMoves[4][1] = (char) (row + 2);

    allMoves[5][0] = (char) (col + 2);
    allMoves[5][1] = (char) (row + 1);

    allMoves[6][0] = (char) (col + 1);
    allMoves[6][1] = (char) (row - 2);

    allMoves[7][0] = (char) (col + 2);
    allMoves[7][1] = (char) (row - 1);

    return allMoves;
  }

  private static char[][] removeIncorrect(char[][] allMoves){
    char[][] correctMoves = new char[8][2];
    int i = 0;
    for(char[] move : allMoves){
      if(move[0] < 'a' || move[0] > 'h' || move[1] < '1' || move[1] > '8'){
        correctMoves[i][0] = ERROR;
        correctMoves[i][1] = ERROR;
      } else {
        correctMoves[i][0] = move[0];
        correctMoves[i][1] = move[1];
      }
      i++;
    }
    return correctMoves;
  }

  private static List<String> movesToList(char[][] correctMoves){
    List<String> list = new ArrayList<>();
    for(char[] move : correctMoves){
      if(move[0] != ERROR){
        list.add(Character.toString(move[0]) + Character.toString(move[1]));
      }
    }
    return list;
  }

  private static String toString(List<String> list){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(String move : list){
      if(first){
        sb.append(move);
        first = false;
        continue;
      }
      sb.append(" ");
      sb.append(move);
    }
    return sb.toString();
  }
}