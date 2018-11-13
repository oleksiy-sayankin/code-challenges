package net.javacogito.jdbcconnector.controller;

import java.util.List;

/**
 * Common interface for controllers.
 *
 * @param <E> Type of the entity
 * @param <K> Type of the primary key
 */

public interface Controller<E, K> {
  /**
   * Returns all elements as list. Executes SELECT * FROM entity.
   *
   * @return list of all elements
   */
  List<E> getAll();

  /**
   * Updates an entity.
   *
   * @param entity entity to update.
   * @return id of entity
   */

  K update(E entity);

  /**
   * Returns entity by it's id, usually primary key.
   *
   * @param id Id of entity
   * @return result entity.
   */
  E getEntityById(K id);

  /**
   * Returns id of the entity by its value
   *
   * @param entity entity with filled in data
   * @return id of the entity by its value
   */
  K getIdByEntity(E entity);

  /**
   * Deletes entity by id.
   *
   * @param id Id of entity to delete
   * @return true of deletion completes successfully.
   */
  boolean delete(K id);

  /**
   * Inserts new entity.
   *
   * @param entity Entity to create.
   * @return true of creation completes successfully.
   */
  K insert(E entity);

  /**
   * Updates entity or inserts it if it does not exist
   *
   * @param entity entity for update or insert
   * @return id of entity
   */
  K instertOrUpdateIfExists(E entity);

  /**
   * Creates table for entity.
   *
   * @return true if creation is successful
   */
  boolean create();

  /**
   * Drops table with entity.
   *
   * @return true if deletion is successful
   */
  boolean drop();

  /**
   * After connection has been used we return it into pool to be available for other requests.
   */
  void returnConnectionInPool();
}
