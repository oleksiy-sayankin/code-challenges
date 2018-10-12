package net.javacogito.mysqlconnector;

import net.javacogito.mysqlconnector.util.DbUtil;
import org.junit.Assert;
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
    Assert.assertTrue(Main.initDbAndFillWithData());
    DbUtil.dropDb();
  }
}
