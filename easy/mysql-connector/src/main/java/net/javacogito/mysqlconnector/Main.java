package net.javacogito.mysqlconnector;

import java.io.IOException;

import static net.javacogito.mysqlconnector.util.DbUtil.initDb;
import static net.javacogito.mysqlconnector.util.DbUtil.storeInDb;
import static net.javacogito.mysqlconnector.util.LoadTableUtil.loadCountries;
import static net.javacogito.mysqlconnector.util.FileUtil.getFromResources;
import static net.javacogito.mysqlconnector.util.LoadTableUtil.loadCustomers;

public class Main {
  public static void main(String[] args) throws IOException {
    initDbAndFillWithData();
  }

  /**
   * This method initializes DB, loads data from CSV file and stores it in DB.
   *
   * @throws IOException when can't read data from file
   */
  private static void initDbAndFillWithData() throws IOException {
    initDb();
    storeInDb(loadCountries(getFromResources("country.csv")));
    storeInDb(loadCustomers(getFromResources("customer.csv")));
  }
}
