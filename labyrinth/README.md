The Labyrinth1
===========

Challenge Description:
----------------------

![Challenge Image](Labyrinth.jpg)

Labyrinth is given in the field of cell with size M x N, (1 ≤ M ≤ 1000 1 ≤ N ≤ 1000) as follows: 0 (zero) means free space, 1 - is the wall. 
Also, initial position of the player is given the in the form of a pair of integers (y, x) where (1 ≤ y ≤ M, 1 ≤ x ≤ N).
 Find the shortest way out of the labyrinth and save it as a sequence of symbols «U», «D», «L», «R», where

* «U» means a step up by one cell
* «D» means a step down by one cell
* «L» means a step to the left by one cell
* «R» means a step to the right by one cell

Start your output sequence from the initial position of the player. If there is no exit from the labyrinth, output the string "NO EXIT». If there are many shortest paths -  print an arbitrary one.

Input data format:

<table>
  <tr>
    <td>File name:</td>
    <td>data.in</td>
  </tr>
  <tr>
    <td>First line</td>
    <td>M,N - size of labyrinth</td>
  </tr>
  <tr>
    <td>Second line:</td>
    <td>y,x - initial position of the player</td>
  </tr>
  <tr>
    <td>3rd,4th,...3 + M line:</td>
    <td>100111001 (N symbols) labyrinth description</td>
  </tr>
</table>

Output data format:

<table>
  <tr>
    <td>File name:</td>
    <td>result.out</td>
  </tr>
  <tr>
    <td>First line</td>
    <td>DDDDRDLLDU - shortest wayout from the labyrinth</td>
  </tr>

</table>

Input sample:
-------------

Input file sample 1. File 'data.in'

    8,10
    1,7
    1011111111
    1010001011
    1010101011
    1010101011
    1010101011
    1010101011
    1000100011
    1111111111


Input file sample 2. File 'data.in'

    16,20
    1,18
    01111111111111111111
    00010000000000000001
    00010000000000000001
    00010000000000000001
    11110000000000000001
    10000000000000000001
    10000000111111111111
    10000000100000000001
    10000000100000000001
    10000000111111100001
    10000000000000100000
    11110000000000100000
    00010000000000100000
    00100000000000100000
    01000000000000100000
    11111111111111101111


Output sample:
--------------


Output file sample 1. File 'result.out'

    DDDDDLLUUUUULLDDDDDLLUUUUUU

Output file sample 2. File 'result.out'

    NO EXIT


Constraints:
------------

* labyrinth size M x N, (1 ≤ M ≤ 1000 1 ≤ N ≤ 1000) 
* initial position of the player (y, x) where (1 ≤ y ≤ M, 1 ≤ x ≤ N).