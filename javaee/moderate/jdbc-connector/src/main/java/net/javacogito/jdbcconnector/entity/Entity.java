package net.javacogito.jdbcconnector.entity;

import net.javacogito.jdbcconnector.controller.Controller;

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
}
