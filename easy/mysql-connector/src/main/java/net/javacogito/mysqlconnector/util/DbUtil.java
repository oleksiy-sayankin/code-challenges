package net.javacogito.mysqlconnector.util;

import net.javacogito.mysqlconnector.controller.Controller;
import net.javacogito.mysqlconnector.controller.CountryController;


public final class DbUtil {
  private DbUtil(){}

  public static void initDb(){
    Controller controller = new CountryController();
    controller.create();
  }
}
