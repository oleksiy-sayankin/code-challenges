package net.javacogito.puzzletiles;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SolutionTest {

  @Test
  public void testSample() {
    ASCIIFun.puzzleTiles(2, 2);
    assertThat(ASCIIFun.puzzleTiles(1, 1), is("   _( )__\n _|     _|\n(_   _ (_\n |__( )_|"));
    // FIXME
//    assertThat(ASCIIFun.puzzleTiles(3, 2),
//      is("   _( )__ _( )__ _( )__\n _|     _|     _|     _|\n(_   _ (_   _ (_   _ (_\n |__( )_|__( )_|__( )_|\n |_     |_     |_     |_\n  _) _   _) _   _) _   _)\n |__( )_|__( )_|__( )_|"));
  }
}
