package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.connection.BasicConnectionPool;
import net.javacogito.jdbcconnector.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
   * Gets query for selecting of id if an entity.
   *
   * @return query for selecting of id if an entity
   */
  protected abstract String getMaxIdQuery();

  /**
   * Runs query in DB to get max id of the entity.
   *
   * @return max id of the entity.
   */
  private Integer getMaxIdFromDb(){
    PreparedStatement ps = getPrepareStatement(getMaxIdQuery());
    Integer maxIdFromDb = null;
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        String result = rs.getString(1);
        maxIdFromDb = isInteger(result) ? Integer.parseInt(result): 0;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Found max id = %d in database", maxIdFromDb));
    return maxIdFromDb;
  }


  /**
   * Returns last id of entity in database. Id has type Integer.
   *
   * @return last id of entity in database
   */
  Integer getLastIdAsInteger() {
    if (lastId != null) {
      return lastId.get();
    }
    lastId = new AtomicInteger(getMaxIdFromDb());
    return lastId.get();
  }

  /**
   * Returns next available id of entity in database. Id has type Integer.
   *
   * @return next available id of entity in database
   */
  Integer getNextIdAsInteger() {
    int nextId = getLastIdAsInteger() + 1;
    lastId.set(nextId);
    return nextId;
  }
}
