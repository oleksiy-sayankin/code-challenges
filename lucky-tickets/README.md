Lucky tickets
=============

Challenge Description:
----------------------

We can receive a lucky ticket in a public transport. How to reveal whether the ticket is lucky or not? We call a ticket lucky if the sum of its digits in the first half equals to the sum of digits in the second half. For example, ticket 328940 is a lucky one because 3+2+8=9+4+0.
Write a program that will count the maximum number of lucky tickets depending on the length of the ticket number. In other words, how many lucky combinations can be if a ticket number comprises 4, 6, 8, … digits ?

Input sample:
-------------

The first argument is a path to a file. Each line includes a test case with an even number that indicates the length of the ticket number.

For example:

    4
    6
    8

Output sample:
--------------

Count and print the maximum possible number of lucky tickets depending on the length of the ticket number.

For example:

    670
    55252
    4816030

Constraints:
------------

1. The length of a ticket number can be from 2 to 100 digits.
2. Tickets 000000 and 999999 should be also counted.
3. All the input numbers are even.
4. The number of test cases is 40.
