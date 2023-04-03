package net.javacogito.binarysearchtree;

import org.junit.Test;

import static net.javacogito.binarysearchtree.BSTUtil.isBinarySearchTree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BSTUtilTest {
  /**
   *                              10
   *                             /  \
   *                            5    15
   *                          /  \  /  \
   *                         3   7  12  20
   */
  @Test
  public void testTrueIsBinarySearchTree() {
    Node root = new Node(10, new Node(5, new Node(3, null, null), new Node(7, null, null)), new Node(15, new Node(12, null, null), new Node(20, null, null)));
    assertTrue(isBinarySearchTree(root));
  }

  /**
   *                               2
   *                             /  \
   *                            5    4
   *                          /  \  /  \
   *                         3   7  12  20
   */
  @Test
  public void testFalseIsBinarySearchTree() {
    Node root = new Node(2, new Node(5, new Node(3, null, null), new Node(7, null, null)), new Node(4, new Node(12, null, null), new Node(20, null, null)));
    assertFalse(isBinarySearchTree(root));
  }
}
