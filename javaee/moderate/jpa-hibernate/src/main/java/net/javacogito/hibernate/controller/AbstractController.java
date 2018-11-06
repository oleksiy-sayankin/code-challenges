package net.javacogito.hibernate.controller;

import net.javacogito.hibernate.model.Entity;
import net.javacogito.hibernate.util.HibernateUntil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;


/**
 * Basic class for all controllers.
 *
 * @param <E> Entity that is managed by controller (e.g. country, customer etc)
 * @param <K> Type of the primary key
 */

public abstract class AbstractController<E extends Entity, K> implements Controller<E, K> {
  private static final Logger LOG = LogManager.getLogger(CountryController.class);
  Session session = HibernateUntil.getSessionFactory().openSession();

  /**
   * Updates an entity.
   *
   * @param entity entity to update.
   */
  @Override public boolean update(E entity) {
    session.beginTransaction();
    session.update(entity); // no need in this code. commit updates automatically
    session.getTransaction().commit();
    LOG.info(String.format("Entity with id = %d updated to new value %s.", entity.getId(), entity));
    return true;
  }


  /**
   * Inserts new entity.
   *
   * @param entity country to create.
   * @return true of creation completes successfully.
   */
  @Override public boolean insert(E entity) {
    session.beginTransaction();
    try {
      session.save(entity);
      session.getTransaction().commit();
    } catch (Throwable ex) {
      session.getTransaction().rollback();
    }
    LOG.info(String.format("Entity %s inserted", entity));
    return true;
  }

  /**
   * Returns id of the entity by its value
   *
   * @param entity entity with filled in data
   * @return id of the entity by its value
   */
  @SuppressWarnings("unchecked") @Override public K getIdByEntity(E entity) {
    K id = (K) session.getIdentifier(entity);
    LOG.info(String.format("Found id %d for entity %s.",id, entity));
    return id;
  }

  /**
   * Deletes country by id.
   *
   * @param id Id of country to delete
   * @return true of deletion completes successfully.
   */
  @Override public boolean delete(K id) {
    session.beginTransaction();
    E entity = getEntityById(id);
    session.delete(entity);
    session.getTransaction().commit();
    LOG.info(String.format("Entity with id = %d deleted", id));
    return true;
  }
}
