package net.javacogito.hibernate.controller;

import net.javacogito.hibernate.model.Entity;

import java.util.List;

/**
 * Common interface for controllers.
 *
 * @param <E> Type of the entity
 * @param <K> Type of the primary key
 */

public interface Controller<E extends Entity, K> {
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
   */
  boolean update(E entity);

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
  boolean insert(E entity);
}
