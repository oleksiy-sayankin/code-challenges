package net.javacogito.piglatin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChallengeTest {

  @Test
  public void testTranslateWord() {
    assertEquals("agflay", Challenge.translateWord("flag"));
    assertEquals("Appleyay", Challenge.translateWord("Apple"));
    assertEquals("uttonbay", Challenge.translateWord("button"));
    assertEquals("", Challenge.translateWord(""));
    assertEquals("ouyay", Challenge.translateWord("you"));
  }

  @Test
  public void testTranslateSentence() {
    assertEquals("[[[Amamay", Challenge.translateSentence("[[[Mama"));
    assertEquals("[[[Amamay]]]", Challenge.translateSentence("[[[Mama]]]"));
    assertEquals("[[[Amamay]]][[[Amamay]]][[[Amamay]]]", Challenge.translateSentence("[[[Mama]]][[[Mama]]][[[Mama]]]"));
    assertEquals("[[[Amamay]]][[[Amamay]]][[[Amamay", Challenge.translateSentence("[[[Mama]]][[[Mama]]][[[Mama"));
    assertEquals("Amamay]]][[[Amamay]]][[[Amamay]]]", Challenge.translateSentence("Mama]]][[[Mama]]][[[Mama]]]"));
    assertEquals("Iyay ikelay otay eatyay oneyhay afflesway.", Challenge.translateSentence("I like to eat honey waffles."));
    assertEquals("Oday ouyay inkthay ityay isyay oinggay otay ainray odaytay?", Challenge.translateSentence("Do you think it is going to rain today?"));
  }

  @Test
  public void test1() {
    assertEquals("avehay", Challenge.translateWord("have"));
  }

  @Test
  public void test2() {
    assertEquals("amcray", Challenge.translateWord("cram"));
  }

  @Test
  public void test3() {
    assertEquals("aketay", Challenge.translateWord("take"));
  }

  @Test
  public void test4() {
    assertEquals("Atcay", Challenge.translateWord("Cat"));
  }

  @Test
  public void test5() {
    assertEquals("Impshray", Challenge.translateWord("Shrimp"));
  }

  @Test
  public void test6() {
    assertEquals("ebuchettray", Challenge.translateWord("trebuchet"));
  }

  @Test
  public void test7() {
    assertEquals("ateyay", Challenge.translateWord("ate"));
  }

  @Test
  public void test8() {
    assertEquals("Appleyay", Challenge.translateWord("Apple"));
  }

  @Test
  public void test9() {
    assertEquals("oakenyay", Challenge.translateWord("oaken"));
  }

  @Test
  public void test10() {
    assertEquals("eagleyay", Challenge.translateWord("eagle"));
  }

  @Test
  public void test11() {
    assertEquals("inkyay", Challenge.translateWord("ink"));
  }

  @Test
  public void test12() {
    assertEquals("unicornyay", Challenge.translateWord("unicorn"));
  }

  @Test
  public void test13() {
    assertEquals("", Challenge.translateWord(""));
  }

  @Test
  public void test14() {
    assertEquals("Iyay ikelay otay eatyay oneyhay afflesway", Challenge.translateSentence("I like to eat honey waffles"));
  }

  @Test
  public void test15() {
    assertEquals("Oday ouyay inkthay ityay isyay oinggay otay ainray odaytay?", Challenge.translateSentence("Do you think it is going to rain today?"));
  }

  @Test
  public void test16() {
    assertEquals("Ehay aidsay, \"Enwhay illway isthay allyay endyay?\"", Challenge.translateSentence("He said, \"When will this all end?\""));
  }

  @Test
  public void test17() {
    assertEquals("", Challenge.translateSentence(""));
  }
}
