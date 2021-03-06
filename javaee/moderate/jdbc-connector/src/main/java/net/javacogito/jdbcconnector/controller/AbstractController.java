package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.connection.BasicConnectionPool;
import net.javacogito.jdbcconnector.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import static net.javacogito.jdbcconnector.util.CommonUtil.isInteger;

/**
 * Basic class for all controllers.
 *
 * @param <E> Entity that is managed by controller (e.g. country, customer etc)
 * @param <K> Type of the primary key
 */

public abstract class AbstractController<E, K> implements Controller<E, K> {
  private Connection connection;
  private ConnectionPool connectionPool;
  private AtomicInteger lastId = null;
  private static final Logger LOG = LogManager.getLogger(CountryController.class);

  public AbstractController() {
    connectionPool = BasicConnectionPool.create();
    connection = connectionPool.getConnection();
  }

  /**
   * After connection has been used we return it into pool to be available for other requests.
   */

  @Override public void returnConnectionInPool() {
    connectionPool.releaseConnection(connection);
  }

  /**
   * Returns prepared statement for further execution.
   *
   * @param sql query to execute
   * @return prepared statement
   */

  public PreparedStatement getPrepareStatement(String sql) {
    PreparedStatement ps = null;
    try {
      ps = connection.prepareStatement(sql);
    } catch (SQLException e) {
      LOG.error(e);
    }
    return ps;
  }

  /**
   * Closes prepared statement after the work is done.
   * @param ps prepared statement to close.
   */

  public void closePrepareStatement(PreparedStatement ps) {
    if (ps != null) {
      try {
        ps.close();
      } catch (SQLException e) {
        LOG.error(e);
      }
    }
  }

  /**
   * Updates entity or inserts it if it does not exist
   *
   * @param entity entity for update or insert
   * @return id of entity
   */
  @Override public K instertOrUpdateIfExists(E entity) {
    if (exists(entity)) {
      return update(entity);
    } else {
      return insert(entity);
    }
  }

  /**
   * Check if entity exists in DB
   * @param entity entity to test
   * @return true if exists
   */
  boolean exists(E entity) {
   return getIdByEntity(entity) != null;
  }
}
