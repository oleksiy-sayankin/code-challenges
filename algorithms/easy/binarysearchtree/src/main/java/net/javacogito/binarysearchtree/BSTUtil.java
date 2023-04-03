package net.javacogito.binarysearchtree;

public final class BSTUtil {
  private static boolean isBinarySearchTree;

  public static boolean isBinarySearchTree(Node node) {
    isBinarySearchTree = true;
    checkNode(node);
    return isBinarySearchTree;
  }

  private static void checkNode(Node node) {
    if (!node.isLeaf()) {
      if (node.left().value() > node.right().value()) {
        isBinarySearchTree = false;
        return;
      }
      checkNode(node.left());
      checkNode(node.right());
    }
  }
}