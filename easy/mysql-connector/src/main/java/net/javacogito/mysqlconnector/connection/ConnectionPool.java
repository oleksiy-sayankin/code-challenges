package net.javacogito.mysqlconnector.connection;

import java.sql.Connection;

/**
 * Basic interface for connection pool.
 */
public interface ConnectionPool {

  /**
   * Gets connection from pool
   *
   * @return JDBC connection object
   */
  Connection getConnection();

  /**
   * Releases connection and puts it back to pool.
   *
   * @param connection connection to release
   * @return true if connection successfully released.
   */
  boolean releaseConnection(Connection connection);
}
