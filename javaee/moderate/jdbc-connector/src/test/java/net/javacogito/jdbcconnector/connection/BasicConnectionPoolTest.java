package net.javacogito.jdbcconnector.connection;

import net.javacogito.jdbcconnector.context.EnvContext;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class BasicConnectionPoolTest {
  @Test public void createConnectionTest() {
    ConnectionPool cp = BasicConnectionPool.create(new EnvContext());
    Connection connection = cp.getConnection();
    Assert.assertNotNull(connection);
  }
}
