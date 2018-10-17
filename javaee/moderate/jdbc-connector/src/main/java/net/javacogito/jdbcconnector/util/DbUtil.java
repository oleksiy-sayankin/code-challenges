package net.javacogito.jdbcconnector.util;

import net.javacogito.jdbcconnector.connection.BasicConnectionPool;
import net.javacogito.jdbcconnector.connection.ConnectionPool;
import net.javacogito.jdbcconnector.context.Context;
import net.javacogito.jdbcconnector.context.EnvContext;
import net.javacogito.jdbcconnector.controller.*;
import net.javacogito.jdbcconnector.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Utility class to create / drop all tables in DB.
 */
public final class DbUtil {
  private static final Controller[] CONTROLLERS = {CountryController.getCountryController(),
      CustomerController.getCustomerController(),
      DepartmentController.getDepartmentController(), EmployeeController.getEmployeeController(),
      EmployeeEmailController.getEmployeeEmailController(),
      EmployeePhoneController.getEmployeePhoneController(), OrderController.getOrderController(),
      ProductController.getProductController(), ProductTypeController.getProductTypeController() };
  private DbUtil(){}

  /**
   * Drop current Data Base if exists.
   */
  public static void dropDbIfExists() {
    Context context =  new EnvContext();
    ConnectionPool cp = BasicConnectionPool.getConnectionPool(context);
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
  }

  /**
   * Create current Data Base.
   */
  public static void createCurrentDb(){
    Context context =  new EnvContext();
    ConnectionPool cp = BasicConnectionPool.create(context);
    Connection connection=  cp.getConnection();
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
