package net.javacogito.mysqlconnector.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic implementation of the connection pool.
 */
public final class BasicConnectionPool implements ConnectionPool {
  private List<Connection> connectionPool;
  private List<Connection> usedConnections = new ArrayList<>();
  private String dbUrl;
  private String dbDriver;
  private String dbUser;
  private String dbPassword;
  private static int INITIAL_POOL_SIZE = 3;

  private BasicConnectionPool(String dbUrl, String dbDriver, String dbUser, String dbPassword, List<Connection> pool) {
    this.dbUrl = dbUrl;
    this.dbDriver = dbDriver;
    this.dbUser = dbUser;
    this.dbPassword = dbPassword;
    this.connectionPool = pool;
  }

  @Override public Connection getConnection() {
    Connection connection = connectionPool.remove(connectionPool.size() - 1);
    usedConnections.add(connection);
    return connection;
  }

  @Override public boolean releaseConnection(Connection connection) {
    connectionPool.add(connection);
    return usedConnections.remove(connection);
  }

  @Override public String getDbUrl() {
    return dbUrl;
  }

  @Override public String getDbUser() {
    return dbUser;
  }

  @Override public String getDbPassword() {
    return dbPassword;
  }

  @Override public String getDbDriver() {
    return dbDriver;
  }

  /**
   * Creates pool of connection with size equal to INITIAL_POOL_SIZE.
   * @param dbUrl URL for JDBC connection
   * @param dbDriver JDBC driver
   * @param dbUser JDBC user
   * @param dbPassword JDBC password
   * @return connection pool
   */

  public static ConnectionPool create(String dbUrl, String dbDriver, String dbUser, String dbPassword) {
    List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
    for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
      pool.add(createConnection(dbUrl, dbDriver, dbUser, dbPassword));
    }
    return new BasicConnectionPool(dbUrl, dbDriver, dbUser, dbPassword, pool);
  }

  /**
   * Creates JDBC connection.
   *
   * @param dbUrl URL for JDBC connection
   * @param dbDriver JDBC driver
   * @param dbUser JDBC user
   * @param dbPassword JDBC password
   * @return connection object
   */

  private static Connection createConnection(String dbUrl, String dbDriver, String dbUser, String dbPassword) {
    Connection connection = null;
    try {
      Class.forName(dbDriver);
      connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
      connection.setAutoCommit(true);
    } catch (SQLException | ClassNotFoundException e) {
      // TODO: log this
    }
    return connection;
  }
}
