package net.javacogito.binarysearchtree;

public record Node(int value, Node left, Node right) {


  public boolean isLeaf() {
    return left == null && right == null;
  }
}
