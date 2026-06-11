# Lowest Common Ancestor

## Description

This challenge finds the lowest common ancestor of two nodes in a predefined tree.

The tree is fixed and has the following structure:

```text
        30
       /  \
      8    52
     / \
    3   20
       /  \
      10   29
```

For each input line, the program receives two node identifiers and prints their lowest common ancestor.

The lowest common ancestor of two nodes is the deepest node in the tree that has both input nodes as descendants. A node can also be considered a descendant of itself.

## Input

The input file contains several lines.

Each line contains two node identifiers separated by a space.

Example:

```text
8 52
3 29
10 29
```

## Output

For each input line, print the lowest common ancestor of the given two nodes.

Example:

```text
30
8
20
```

## Approach

The solution uses parent references for each node.

For every pair of nodes:

1. Build a path from the first node to the root.
2. Build a path from the second node to the root.
3. Reverse both paths so that they start from the root.
4. Compare both paths node by node.
5. The last equal node before the paths diverge is the lowest common ancestor.

For example, for nodes `10` and `29`:

```text
Path from 10 to root: 30 -> 8 -> 20 -> 10
Path from 29 to root: 30 -> 8 -> 20 -> 29
```

The last common node is `20`, so the answer is:

```text
20
```

## Complexity

Let `h` be the height of the tree.

* Time complexity: `O(h)` for each pair of nodes.
* Space complexity: `O(h)` for storing paths to the root.

In this challenge, the tree is predefined and small, so the algorithm works in constant time for the given input tree.

Example input file:

```text
8 52
3 29
10 29
```

Expected output:

```text
30
8
20
```
