package net.javacogito.lowestcommonancestor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  private static final String EMPTY = "";
  static final Node ROOT = new Node("30");
  private static final List<Node> ALL_NODES = new ArrayList<>();

  static {
    ROOT.setRoot(true);
    Node node8 = new Node("8");
    Node node52 = new Node("52");
    ROOT.addChild(node8, node52);
    Node node3 = new Node("3");
    Node node20 = new Node("20");
    node8.addChild(node3, node20);
    Node node10 = new Node("10");
    Node node29 = new Node("29");
    node20.addChild(node10, node29);

    ALL_NODES.add(ROOT);
    ALL_NODES.add(node8);
    ALL_NODES.add(node52);
    ALL_NODES.add(node3);
    ALL_NODES.add(node20);
    ALL_NODES.add(node10);
    ALL_NODES.add(node29);
  }

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(lowestCommonAncestor(parseNode1(inputLine), parseNode2(inputLine)));
      }
    }
  }

  private static Node parseNode1(String data){
    return getNodeById(data.split(" ")[0]);
  }

  private static Node parseNode2(String data){
    return getNodeById(data.split(" ")[1]);
  }


  static Node lowestCommonAncestor(Node node1, Node node2) {
    List<Node> path1 = node1.pathToRoot();
    List<Node> path2 = node2.pathToRoot();
    int index = 0;
    int length = Math.min(path1.size(), path2.size());
    while (path1.get(index).equals(path2.get(index))) {
      index++;
      if(index >= length){
        if(length == 1){
          index = length;
        } else {
          index = index - 1;
        }
        break;
      }
    }
    return path1.get(index - 1);
  }


  static Node getNodeById(String id){
    for(Node node : ALL_NODES){
      if(id.equals(node.getId())){
        return node;
      }
    }
    return ROOT; // never happens
  }

  static class Node{
    private List<Node> children = new ArrayList<>();
    private boolean isRoot = false;
    private final String id;

    private Node getParent() {
      return parent;
    }

    private void setParent(Node parent) {
      this.parent = parent;
    }

    private Node parent;

    private Node(String id){
      this.id = id;
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

    private String getId(){
      return id;
    }

    @Override
    public String toString(){
      return id;
    }

    List<Node> pathToRoot(){
      List<Node> result = new ArrayList<>();
      Node currentNode = this;
      while (!currentNode.isRoot()){
        result.add(currentNode);
        currentNode = currentNode.getParent();
      }
      result.add(ROOT);
      Collections.reverse(result);
      return result;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node) o;
      return id.equals(node.id);
    }

    @Override
    public int hashCode() {
      return id.hashCode();
    }
  }
}