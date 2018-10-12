package net.javacogito.mysqlconnector.entity;

import net.javacogito.mysqlconnector.controller.Controller;

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
