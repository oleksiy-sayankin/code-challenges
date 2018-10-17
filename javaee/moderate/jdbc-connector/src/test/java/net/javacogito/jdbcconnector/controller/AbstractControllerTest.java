package net.javacogito.jdbcconnector.controller;

import org.junit.After;
import org.junit.Before;

public abstract class AbstractControllerTest<E, K> {
  protected Controller<E, K> controller;

  @Before public void init() {
    createController();
    dropTable();
    createTable();
    insertData();
  }

  @After public void close() {
    dropTable();
  }

  protected abstract void createController();
  protected abstract void insertData();


  private void dropTable() {
    controller.drop();
  }

  private void createTable() {
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }
}
