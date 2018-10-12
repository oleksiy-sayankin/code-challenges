package net.javacogito.mysqlconnector;

import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple Main.
 */
public class MainTest {
  /**
   * Rigorous Test :-)
   */
  @Test public void shouldAnswerWithTrue() throws IOException {
    Main.initDbAndFillWithData();
  }
}
