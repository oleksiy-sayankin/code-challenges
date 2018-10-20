package net.javacogito.jdbcconnector.connection;

import net.javacogito.jdbcconnector.context.Context;
import net.javacogito.jdbcconnector.context.ContextFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic implementation of the connection pool.
 */
public final class BasicConnectionPool implements ConnectionPool {
  private List<Connection> feeConnections;
  private List<Connection> usedConnections = new ArrayList<>();
  private static ConnectionPool connectionPool = null;

  private BasicConnectionPool(List<Connection> pool) {
    this.feeConnections = pool;
  }

  /**
   * Gets connection from pool
   *
   * @return JDBC connection object
   */
  @Override public synchronized Connection getConnection() {
    Connection connection = feeConnections.remove(feeConnections.size() - 1);
    usedConnections.add(connection);
    return connection;
  }

  /**
   * Releases connection and puts it back to pool.
   *
   * @param connection connection to release
   * @return true if connection successfully released.
   */
  @Override public synchronized boolean releaseConnection(Connection connection) {
    feeConnections.add(connection);
    return usedConnections.remove(connection);
  }

  /**
   * Creates pool of connection with size equal to INITIAL_POOL_SIZE.
   * @return connection pool
   */

  public static ConnectionPool create() {
    Context context = ContextFactory.getDefaultContext();
    int initialPoolSize = context.getInitialPoolSize();
    List<Connection> pool = new ArrayList<>(initialPoolSize);
    for (int i = 0; i < initialPoolSize; i++) {
      pool.add(createConnection(context.getDbUrl(), context.getDbDriver(), context.getDbUser(), context.getDbPassword()));
    }
    return new BasicConnectionPool(pool);
  }

  /**
   * Gets connection pool if it already exists and creates if not.
   * @return existing or created connection pool
   */
  public static ConnectionPool getConnectionPool(){
    if (connectionPool == null) {
      connectionPool = create();
    }
    return connectionPool;
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
      System.out.println(e);
    }
    return connection;
  }
}
