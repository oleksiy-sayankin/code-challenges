package net.javacogito.hibernate.model;

import net.javacogito.hibernate.controller.Controller;

/**
 * Basic interface for entity.
 */
public interface Entity {
  /**
   * Gets controller for entity.
   *
   * @return controller for entity
   */
  Controller getController();

  /**
   * Gets id.
   *
   * @return id
   */
  int getId();

  /**
   * Sets id.
   *
   * @param id primary key
   */
  void setId(int id);
}
