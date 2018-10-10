package net.javacogito.mysqlconnector.util;

import net.javacogito.mysqlconnector.controller.Controller;
import net.javacogito.mysqlconnector.controller.CountryController;
import net.javacogito.mysqlconnector.controller.CustomerController;
import net.javacogito.mysqlconnector.controller.DepartmentController;

/**
 * Utility class to create / drop all tables in DB.
 */
public final class DbUtil {
  private static final Controller[] CONTROLLERS = {new CountryController(), new CustomerController(), new DepartmentController() };
  private DbUtil(){}

  /**
   * Creates all tables in DB.
   */
  public static void initDb(){
    for (Controller controller : CONTROLLERS) {
      controller.create();
    }
  }
}
