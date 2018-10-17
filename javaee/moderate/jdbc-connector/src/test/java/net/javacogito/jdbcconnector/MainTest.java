package net.javacogito.jdbcconnector;

import net.javacogito.jdbcconnector.util.DbUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static net.javacogito.jdbcconnector.EnvUtil.injectEnvironmentVariable;

/**
 * Unit test for simple Main.
 */
public class MainTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  /**
   * Rigorous Test :-)
   */
  @Test public void shouldAnswerWithTrue() throws IOException {
    DbUtil.dropDbIfExists();
    Assert.assertTrue(Main.initDbAndFillWithData());
    DbUtil.dropDbIfExists();
  }

  @Test public void validateJdbcParametersNegativeDbUrlTest() throws Exception {
    String dbUrl = System.getenv("DB_URL");
    try {
      injectEnvironmentVariable("DB_URL", "");
      thrown.expect(IllegalArgumentException.class);
      Main.validateJdbcParameters();
    } finally {
      injectEnvironmentVariable("DB_URL", dbUrl);
    }
  }

  @Test public void validateJdbcParametersNegativeDbDriverTest() throws Exception {
    String dbUrl = System.getenv("DB_DRIVER");
    try {
      injectEnvironmentVariable("DB_DRIVER", "");
      thrown.expect(IllegalArgumentException.class);
      Main.validateJdbcParameters();
    } finally {
      injectEnvironmentVariable("DB_DRIVER", dbUrl);
    }
  }
}
