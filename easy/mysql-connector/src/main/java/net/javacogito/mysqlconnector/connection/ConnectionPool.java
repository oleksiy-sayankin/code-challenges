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

  /**
   * Gets JDBC URL
   *
   * @return JDBC URL as string
   */
  String getDbUrl();

  /**
   * Gets JDBC user
   *
   * @return JDBC user as string
   */
  String getDbUser();

  /**
   * Gets JDBC password.
   *
   * @return JDBC password as string
   */
  String getDbPassword();

  /**
   * Gets JDBC driver class name.
   *
   * @return JDBC driver class name as string
   */
  String getDbDriver();
}
