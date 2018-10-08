package net.javacogito.mysqlconnector.controller;

import java.util.List;

public interface Controller<E, K> {
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
   * Inserts new entity.
   * @param entity Entity to create.
   * @return true of createn completes successfully.
   */
  public abstract boolean insert(E entity);

  /**
   * Creates table for entity.
   */
  public abstract boolean create();

  /**
   * After connection has been used we return it into pool to be available for other requests.
   */

}
