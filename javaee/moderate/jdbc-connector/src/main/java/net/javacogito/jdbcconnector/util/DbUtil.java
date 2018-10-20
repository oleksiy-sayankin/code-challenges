package net.javacogito.jdbcconnector.util;

import net.javacogito.jdbcconnector.connection.BasicConnectionPool;
import net.javacogito.jdbcconnector.connection.ConnectionPool;
import net.javacogito.jdbcconnector.context.Context;
import net.javacogito.jdbcconnector.context.ContextFactory;
import net.javacogito.jdbcconnector.controller.*;
import net.javacogito.jdbcconnector.entity.Entity;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Utility class to create / drop all tables in DB.
 */
public final class DbUtil {
  private static final Controller[] CONTROLLERS =
      { CountryController.getCountryController(), CustomerController.getCustomerController(), DepartmentController.getDepartmentController(), EmployeeController.getEmployeeController(),
          EmployeeEmailController.getEmployeeEmailController(), EmployeePhoneController.getEmployeePhoneController(),
          OrderController.getOrderController(), ProductController.getProductController(), ProductTypeController.getProductTypeController() };
  private static final Logger LOG = LogManager.getLogger(DbUtil.class);

  private DbUtil(){}

  /**
   * Drop current Data Base if exists.
   */
  public static void dropDbIfExists() {
    Context context = ContextFactory.getDefaultContext();
    ConnectionPool cp = BasicConnectionPool.getConnectionPool();
    Connection connection = cp.getConnection();
    String dropDb = String.format("DROP SCHEMA IF EXISTS %s", context.getDbName());
    PreparedStatement ps = null;
    try {
      ps = connection.prepareStatement(dropDb);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    cp.releaseConnection(connection);
    LOG.info(String.format("Database %s is successfully dropped", context.getDbName()));
  }

  /**
   * Create current Data Base.
   */
  public static void createCurrentDb(){
    Context context = ContextFactory.getDefaultContext();
    ConnectionPool cp = BasicConnectionPool.getConnectionPool();
    Connection connection = cp.getConnection();
    String dropDb = String.format("CREATE SCHEMA IF NOT EXISTS %s", context.getDbName());
    PreparedStatement ps = null;
    try {
      ps = connection.prepareStatement(dropDb);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    cp.releaseConnection(connection);
    LOG.info(String.format("Database %s is successfully created", context.getDbName()));
  }


  /**
   * Creates all tables in DB.
   */
  public static void createAllTablesInDb(){
    for (Controller controller : CONTROLLERS) {
      controller.create();
    }
  }

  /**
   * Drops all tables in Db
   */
  public static void dropAllTablesInDb() {
    for (Controller controller : CONTROLLERS) {
      controller.drop();
    }
  }

  /**
   * Stores entity into DB
   *
   * @param entities list of entities
   */
  @SuppressWarnings("unchecked")
  public static void storeInDb(List<? extends Entity> entities){
    for (Entity entity : entities){
      entity.getController().insert(entity);
    }
  }
}
