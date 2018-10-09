package net.javacogito.mysqlconnector.util;

import net.javacogito.mysqlconnector.controller.Controller;
import net.javacogito.mysqlconnector.controller.CountryController;

/**
 * Utility class to create / drop all tables in DB.
 */
public final class DbUtil {
  private DbUtil(){}

  /**
   * Creates all tables in DB.
   */
  public static void initDb(){
    Controller[] controllers = {new CountryController()};
    for (Controller controller : controllers) {
      controller.create();
    }
  }
}
