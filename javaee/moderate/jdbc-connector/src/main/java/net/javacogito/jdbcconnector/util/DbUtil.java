package net.javacogito.jdbcconnector.util;

import net.javacogito.jdbcconnector.controller.*;
import net.javacogito.jdbcconnector.entity.Entity;

import java.util.List;

/**
 * Utility class to create / drop all tables in DB.
 */
public final class DbUtil {
  private static final Controller[] CONTROLLERS = {new CountryController(), new CustomerController(),
      new DepartmentController(), new EmployeeController(), new EmployeeEmailController(),
      new EmployeePhoneController(), new OrderController(), new ProductController(), new ProductTypeController() };
  private DbUtil(){}

  /**
   * Creates all tables in DB.
   */
  public static void initDb(){
    for (Controller controller : CONTROLLERS) {
      controller.create();
    }
  }

  /**
   * Drops all tables in Db
   */
  public static void dropDb() {
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
