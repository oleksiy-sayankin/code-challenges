package net.javacogito.lowestcommonancestor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static final String EMPTY = "";
  private static final Node tree = new Node("30");

  static {
    tree.setRoot(true);
    Node node8 = new Node("8");
    Node node52 = new Node("52");
    tree.addChild(node8, node52);
    Node node3 = new Node("2");
    Node node20 = new Node("30");
    node8.addChild(node3, node20);
    Node node10 = new Node("10");
    Node node29 = new Node("29");
    node20.addChild(node10, node29);

  }

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
    private final String name;

    public Node getParent() {
      return parent;
    }

    public void setParent(Node parent) {
      this.parent = parent;
    }

    private Node parent;

    public Node(String name){
      this.name = name;
    }

    private boolean isRoot(){
      return isRoot;
    }

    private void setRoot(boolean isRoot){
      this.isRoot = isRoot;
    }
    
    private void addChild(Node ...nodes){
      for(Node node : nodes) {
        children.add(node);
        node.setParent(this);
      }
    }

    @Override
    public String toString(){
      return name;
    }

    private List<Node> pathToRoot(){
      List<Node> result = new ArrayList<>();
      Node currentNode = this;
      while (!currentNode.isRoot()){
        result.add(currentNode);
        currentNode = currentNode.getParent();
      }
      Collections.reverse(result);
      return result;
    }
  }
}