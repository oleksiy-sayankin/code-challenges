package net.javacogito.jdbcconnector.connection;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class BasicConnectionPoolTest {
  @Test public void createConnectionTest() {
    ConnectionPool cp = BasicConnectionPool.getConnectionPool();
    Connection connection = cp.getConnection();
    Assert.assertNotNull(connection);
    cp.releaseConnection(connection);
  }
}
