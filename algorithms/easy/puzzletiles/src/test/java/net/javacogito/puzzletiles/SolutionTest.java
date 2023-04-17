package net.javacogito.puzzletiles;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SolutionTest {

  @Test
  public void testSample() {
    System.out.println(ASCIIFun.puzzleTiles(1, 4)); // FIXME
    assertThat(ASCIIFun.puzzleTiles(1, 1), is("   _( )__\n _|     _|\n(_   _ (_\n |__( )_|"));
    assertThat(ASCIIFun.puzzleTiles(3, 2),
      is("   _( )__ _( )__ _( )__\n _|     _|     _|     _|\n(_   _ (_   _ (_   _ (_\n |__( )_|__( )_|__( )_|\n |_     |_     |_     |_\n  _) _   _) _   _) _   _)\n |__( )_|__( )_|__( )_|"));
    assertThat(ASCIIFun.puzzleTiles(5, 3),is("   _( )__ _( )__ _( )__ _( )__ _( )__\n" +
      " _|     _|     _|     _|     _|     _|\n" +
      "(_   _ (_   _ (_   _ (_   _ (_   _ (_\n" +
      " |__( )_|__( )_|__( )_|__( )_|__( )_|\n" +
      " |_     |_     |_     |_     |_     |_\n" +
      "  _) _   _) _   _) _   _) _   _) _   _)\n" +
      " |__( )_|__( )_|__( )_|__( )_|__( )_|\n" +
      " _|     _|     _|     _|     _|     _|\n" +
      "(_   _ (_   _ (_   _ (_   _ (_   _ (_\n" +
      " |__( )_|__( )_|__( )_|__( )_|__( )_|"));

    assertThat(ASCIIFun.puzzleTiles(1, 2),
      is("   _( )__\n" +
               " _|     _|\n" +
               "(_   _ (_\n" +
               " |__( )_|\n" +
               " |_     |_\n" +
               "  _) _   _)\n" +
               " |__( )_|")
      );
    assertThat(ASCIIFun.puzzleTiles(5, 1),
      is("   _( )__ _( )__ _( )__ _( )__ _( )__\n" +
              " _|     _|     _|     _|     _|     _|\n" +
              "(_   _ (_   _ (_   _ (_   _ (_   _ (_\n" +
              " |__( )_|__( )_|__( )_|__( )_|__( )_|"));

    assertThat(ASCIIFun.puzzleTiles(1, 4),
      is("   _( )__\n" +
        " _|     _|\n" +
        "(_   _ (_\n" +
        " |__( )_|\n" +
        " |_     |_\n" +
        "  _) _   _)\n" +
        " |__( )_|\n" +
        " _|     _|\n" +
        "(_   _ (_\n" +
        " |__( )_|\n" +
        " |_     |_\n" +
        "  _) _   _)\n" +
        " |__( )_|")
    );

  }
}
