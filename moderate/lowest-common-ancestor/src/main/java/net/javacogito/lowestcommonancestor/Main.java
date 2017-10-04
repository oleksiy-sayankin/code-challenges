package net.javacogito.lowestcommonancestor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println();
      }
    }
  }

  private static class Node{
    private List<Node> children = new ArrayList<>();
    private boolean isRoot = false;

    private boolean isRoot(){
      return isRoot;
    }

    private void setRoot(boolean isRoot){
      this.isRoot = isRoot;
    }


  }
}