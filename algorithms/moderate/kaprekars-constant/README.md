Kaprekar's Constant
===================

Credit: [link](https://edabit.com/challenge/eBkknBKXvMm8bDo8M)

6174 is known as one of Kaprekar's constants, after the Indian mathematician D. R. Kaprekar. Number 6174 is notable for the following rule:

* Take any four-digit number, using at least two different digits (leading zeros are allowed).
* Arrange the digits in descending and then in ascending order to get two four-digit numbers, adding leading zeros if necessary.
* Subtract the smaller number from the bigger number.
* Go back to step 2 and repeat.

The above process, known as Kaprekar's routine, will always reach its fixed point, 6174, in at most 7 iterations. Once 6174 is reached, the process will continue yielding 7641 – 1467 = 6174. For example, choose 3524:

    5432 – 2345 = 3087
    8730 – 0378 = 8352
    8532 – 2358 = 6174
    7641 – 1467 = 6174

Write a recursive function that will return the number of times it will take to get from a number to 6174 (the above example would equal 3).

    (1)5432 – 2345 = 3087,
    (2)8730 – 0378 = 8352,
    (3)8532 – 2358 = 6174

495 would produce the following: 4950 to 9540 - 459, 9081 to 9810 - 189, 9621 to 9621 - 1269, 8352 to 8532 - 2358 answer: 4

For a 2 digit number, the following would be produced (stating with number 10 -> 100):

100 to 100 - 1, 990 to 990 - 99, 8910 to 9810 - 189, 9621 to 9621 - 1269, 8352 to 8532 - 2358 answer: 5

**Examples**

    kaprekar(6621) ➞ 5

    kaprekar(6554) ➞ 4

    kaprekar(1234) ➞ 3

**Notes**

If the subtracted number is less than 1000, add an extra zero to that number. The number 1111 will equal 0000, so this number (1111) is invalid. If you're still unclear, please check the comments section.