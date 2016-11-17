Lost In Translation
===================

Challenge Description:
----------------------

![Challenge Image](lost_in_translation.png)

Credits: This is an adaptation of the original challenge that was created by David Arthur.

We have come up with the best possible language called Codel. To translate text into Codel, we take any message and 
replace each English letter with another English letter. This mapping is one-to-one and onto, which means that the 
same input letter always gets replaced with the same output letter, and different input letters always get replaced 
with different output letters. A letter may be replaced by itself. Spaces are left as-is.
For example (and here is a hint!), our translation algorithm includes the following three mappings: 'b' -> 'n', 
'j' -> 'u', and 'v' -> 'g' is based on the best possible replacement mapping, and we will never change it. It 
will always be the same. In every test case. We will not tell you the rest of our mapping because that would 
make the problem too easy, but there are a few examples below that may help. 

Input sample:
------------

Your program should accept as its first argument a path to a filename. Each line of file consists of a string in 
Codel, made up of one or more words containing the letters 'a' - 'z'. There will be exactly one space (' ') 
character between consecutive words and no spaces at the beginning or at the end of any line. E.g. 

    rbc vjnmkf kd yxyqci na rbc zjkfoscdd ew rbc ujllmcp
    tc rbkso rbyr ejp mysljylc kd kxveddknmc re jsicpdrysi
    de kr kd eoya kw aej icfkici re zjkr

    
Output sample:
------------

For each test case, output one line containing translated string. E.g. 

    the public is amazed by the quickness of the juggler
    we think that our language is impossible to understand
    so it is okay if you decided to quit