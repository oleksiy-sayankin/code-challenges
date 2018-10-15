package net.javacogito.mysqlconnector;

import java.io.IOException;

import static net.javacogito.mysqlconnector.util.DbUtil.initDb;
import static net.javacogito.mysqlconnector.util.DbUtil.storeInDb;
import static net.javacogito.mysqlconnector.util.FileUtil.getFromResources;
import static net.javacogito.mysqlconnector.util.LoadTableUtil.*;

public class Main {
  public static void main(String[] args) throws IOException {
    initDbAndFillWithData();
  }

  /**
   * This method initializes DB, loads data from CSV file and stores it in DB with JDBC connector.
   *
   * @return true if creation completes successfully
   * @throws IOException when can't read data from file
   *
   */
  public static boolean initDbAndFillWithData() throws IOException {
    initDb();
    storeInDb(loadCountries(getFromResources("country.csv")));
    storeInDb(loadCustomers(getFromResources("customer.csv")));
    storeInDb(loadDepartments(getFromResources("department.csv")));
    storeInDb(loadEmployees(getFromResources("employee.csv")));
    storeInDb(loadEmployeeEmails(getFromResources("employee-email.csv")));
    storeInDb(loadEmployeePhones(getFromResources("employee-phone.csv")));
    return true;
  }
}
