package net.javacogito.puzzletiles;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SolutionTest {

  @Test
  public void testSample() {
    ASCIIFun.puzzleTiles(3, 2);
    //assertEquals("   _( )__\n _|     _|\n(_   _ (_\n |__( )_|", ASCIIFun.puzzleTiles(1, 1));
    assertThat(
      "   _( )__ _( )__ _( )__\n _|     _|     _|     _|\n(_   _ (_   _ (_   _ (_\n |__( )_|__( )_|__( )_|\n |_     |_     |_     |_\n  _) _   _) _   _) _   _)\n |__( )_|__( )_|__( )_|",
      is(ASCIIFun.puzzleTiles(3, 2)));
  }
}
