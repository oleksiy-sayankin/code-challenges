package net.javacogito.mysqlconnector.connection;

import net.javacogito.mysqlconnector.context.EnvContext;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class BasicConnectionPoolTest {
  @Test
  public void createConnectionTest(){
    ConnectionPool cp = BasicConnectionPool.create(new EnvContext());
    Connection connection = cp.getConnection();
    Assert.assertNotNull(connection);
  }
}
