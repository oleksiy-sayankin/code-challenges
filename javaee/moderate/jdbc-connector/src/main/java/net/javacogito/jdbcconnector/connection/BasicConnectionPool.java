package net.javacogito.jdbcconnector.connection;

import net.javacogito.jdbcconnector.context.Context;

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
  private static int INITIAL_POOL_SIZE = 3;
  private static ConnectionPool connectionPool = null;

  private BasicConnectionPool(List<Connection> pool) {
    this.feeConnections = pool;
  }

  /**
   * Gets connection from pool
   *
   * @return JDBC connection object
   */
  @Override public Connection getConnection() {
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
  @Override public boolean releaseConnection(Connection connection) {
    feeConnections.add(connection);
    return usedConnections.remove(connection);
  }

  /**
   * Creates pool of connection with size equal to INITIAL_POOL_SIZE.
   * @param context context with JDBC parameters
   * @return connection pool
   */

  public static ConnectionPool create(Context context) {
    List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
    for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
      pool.add(createConnection(context.getDbUrl(), context.getDbDriver(), context.getDbUser(), context.getDbPassword()));
    }
    return new BasicConnectionPool(pool);
  }

  /**
   * Gets connection pool if it already exists and creates if not.
   * @param context context with JDBC parameters
   * @return existing or created connection pool
   */
  public static ConnectionPool getConnectionPool(Context context){
    if (connectionPool == null) {
      connectionPool = create(context);
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
      // TODO: log this
    }
    return connection;
  }
}