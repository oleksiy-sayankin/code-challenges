The Anagrammer
==============

Challenge Description:
----------------------

![Challenge Image](anagrammer.png)

Anagrammer - a special device to receive word of his anagrams (ie words
written by the same letters but in a different order). This device is able to perform two operations:

1. Take the next letter of the original word and put it on the stack.<br>
2. Take the letter from the stack and add it to the end of the output word.<br>

For example, the word TORT can be converted by anagrammer into the word TROT 
in two different sequences of operations: 11112222 or 12112212. Write a program that for two given words computes
 the number of different sequences of anagrammer's operations  that convert the first of this word into the second 
 one and find these sequences.

Input data format

The first line in the file contains the original word and the second - a word that must be obtained.
 Words consist only of uppercase letters and have a length of no more than 50 characters. 
 Both words are of equal length. These lines do not contain any spaces.

Output data format

The first line of output file should contain a number of sequences of anagrammer's operations, with which you can convert 
the first word into the second. If this number does not exceed 1,000, the following lines should contain sequences.
 Each sequence should be displayed on a separate line, and consists of numbers 1 and 2 (indicating the order of operations), 
 printed with no spaces.

Input sample:
-------------

Input file sample 1

    MADAM
    ADAMM

Input file sample 2

    LONG
    GONG

Input file sample 3

    AAAAAAAA
    AAAAAAAA

Output sample:
--------------


Output file sample 1

    4
    1111222122
    1111222212
    1121212122
    1121212212


Output file sample 2

    0
    
Output file sample 3

    1430


Constraints:
------------

* Number of output sequences should not exceed 1,000. If it exceeds then there is no sequence output at all 
* Length if input line does not exceed 50 characters