Roman Numerals Helper
=====================
Credit: [link](https://www.codewars.com/kata/51b66044bce5799a7f000003)

Description:
------------

Write two functions that convert a roman numeral to and from an integer value. Multiple roman numeral values will be tested for each function.

Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero. In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.

Input range : 1 <= n < 4000

In this kata 4 should be represented as IV, NOT as IIII (the "watchmaker's four").

Examples
--------
 to roman:

    2000 -> "MM"
    1666 -> "MDCLXVI"
    1000 -> "M"
    400 -> "CD"
    90 -> "XC"
    40 -> "XL"
    1 -> "I"

from roman:

    "MM"      -> 2000
    "MDCLXVI" -> 1666
    "M"       -> 1000
    "CD"      -> 400
    "XC"      -> 90
    "XL"      -> 40
    "I"       -> 1

Help
----
    Symbol 	Value
    I 	1
    IV 	4
    V 	5
    X 	10
    L 	50
    C 	100
    D 	500
    M 	1000
