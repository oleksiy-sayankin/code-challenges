English to Pig Latin Translator
===============================

Credit: [link](https://edabit.com/challenge/6dEDvruWbEDqXb7dk).

Pig latin has two very simple rules:

1  If a word starts with a consonant move the first letter(s) of the word, till you reach a vowel, to the end of the word and add "ay" to the end.
* have ➞ avehay
* cram ➞ amcray
* take ➞ aketay
* cat ➞ atcay
* shrimp ➞ impshray
* trebuchet ➞ ebuchettray    

2 If a word starts with a vowel add "yay" to the end of the word.
* ate ➞ ateyay
* apple ➞ appleyay
* oaken ➞ oakenyay
* eagle ➞ eagleyay

Write two functions to make an English to pig latin translator. The first function translateWord(word) takes a single word and returns that word translated into pig latin. The second function translateSentence(sentence) takes an English sentence and returns that sentence translated into pig latin.

**Examples**

    translateWord("flag") ➞ "agflay"

    translateWord("Apple") ➞ "Appleyay"

    translateWord("button") ➞ "uttonbay"

    translateWord("") ➞ ""

    translateSentence("I like to eat honey waffles.") ➞ "Iyay ikelay otay eatyay oneyhay afflesway."

    translateSentence("Do you think it is going to rain today?") ➞ "Oday ouyay inkthay ityay isyay oinggay otay ainray odaytay?"

**Notes**

* Regular expressions will help you not mess up the punctuation in the sentence.
* If the original word or sentence starts with a capital letter, the translation should preserve its case (see examples #2, #5 and #6).