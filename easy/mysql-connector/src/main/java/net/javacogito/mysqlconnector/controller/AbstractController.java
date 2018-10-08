package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Basic class for all controllers.
 *
 * @param <E> Entity that is managed by controller (e.g. country, customer etc)
 * @param <K> Type of the primary key
 */

public abstract class AbstractController<E, K> {
  private Connection connection;
  private ConnectionPool connectionPool;

  public AbstractController() {
    connectionPool = ConnectionPool.getConnectionPool();
    connection = connectionPool.getConnection();
  }

  /**
   * Returns all elements as list. Executes SELECT * FROM entity.
   * @return list of all elements
   */
  public abstract List<E> getAll();

  /**
   * Updates an entity.
   * @param entity entity to update.
   * @return
   */
  public abstract void update(E entity);

  /**
   * Returns entity by it id, usually primary key.
   * @param id Id of entity
   * @return result entity.
   */
  public abstract E getEntityById(K id);

  /**
   * Deletes entity by id.
   * @param id Id of entirty to delete
   * @return true of deletion completes successfully.
   */
  public abstract boolean delete(K id);

  /**
   * Creates new entity.
   * @param entity Entity to create.
   * @return true of createn completes successfully.
   */
  public abstract boolean create(E entity);

  /**
   * After connection has been used we return it into pool to be available for other requests.
   */

  public void returnConnectionInPool() {
    connectionPool.returnConnection(connection);
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
      e.printStackTrace();
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
        e.printStackTrace();
      }
    }
  }
}