Highest number with two prime factors
=====================================
Credit: [link](https://www.codewars.com/kata/55f347cfb44b879e1e00000d)

Description:
------------

The numbers 6, 12, 18, 24, 36, 48 have a common property. They have the same two prime factors that are 2 and 3.

If we see their prime factorization we will see it more clearly:

    6 = 2 * 3
    12 = 2^2 * 3
    18 = 2 * 3^2
    24 = 2^3 * 3
    36 = 2^2 * 3^2
    48 = 2^4 * 3

48 is the maximum of them bellow the value 50

We may see another cases, for numbers that have another two prime factors like: 5, 11, but bellow (or equal) a maximum value 1000

    55 = 5 * 11
    275 = 5^2 * 11
    605 = 5 * 11^2

In this case 605 is the highest possible number bellow 1000.

Make the function `highest_biPrimefac()`, that receives two primes as arguments and a maximum limit , nMax(found numbers should be less or equal to nMax). Output should be a list or array of highest number found and the corresponding exponents of primes in order given. Representing the features for this function:

    highest_biPrimefac(p1, p2, nMax) -------> [max.number, k1, k2]
    max.number = p1^k1 * p2^k2 <= nMax
    p1 < p2 and k1 >= 1, k2 >= 1

Let's see the cases we have above:

    highest_biPrimefac(2, 3, 50) ------> [48, 4, 1]
    highest_biPrime(5, 11, 1000) ------> [605, 1, 2]

Enjoy it and happy coding!
