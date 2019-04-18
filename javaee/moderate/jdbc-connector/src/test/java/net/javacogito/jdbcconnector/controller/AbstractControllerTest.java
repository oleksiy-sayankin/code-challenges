package net.javacogito.jdbcconnector.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static net.javacogito.jdbcconnector.util.DbUtil.createCurrentDb;
import static net.javacogito.jdbcconnector.util.DbUtil.dropDbIfExists;

public abstract class AbstractControllerTest<E, K> {
  protected Controller<E, K> controller;

  @Before public void init() {
    createController();
    createCurrentDb();
    dropTable();
    createTable();
    insertData();
  }

  @After public void close() {
    dropTable();
    dropDbIfExists();
    releaseConnection();
  }

  protected abstract void createController();
  protected abstract void insertData();


  private void dropTable() {
    controller.drop();
  }

  private void releaseConnection() {
    controller.returnConnectionInPool();
  }

  private void createTable() {
    if (!controller.create()) {
      throw new IllegalArgumentException("Can not create table.");
    }
  }
}
