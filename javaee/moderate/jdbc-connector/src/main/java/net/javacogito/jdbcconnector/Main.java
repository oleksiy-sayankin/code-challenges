package net.javacogito.jdbcconnector;

import java.io.IOException;

import static net.javacogito.jdbcconnector.util.DbUtil.*;
import static net.javacogito.jdbcconnector.util.FileUtil.getFromDataDir;
import static net.javacogito.jdbcconnector.util.LoadTableUtil.*;

/**
 * Main demo class.
 */
public class Main {

  /**
   * This is demo method. It checks if minimal JDBC parameters are set, creates DB tables and fills in with data.
   * @param args skipped in current implementation
   * @throws IOException when can't read data from file
   */

  public static void main(String[] args) throws IOException {
    validateJdbcParameters();
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
    dropDbIfExists();
    createCurrentDb();
    createAllTablesInDb();
    storeInDb(loadCountries(getFromDataDir("country.csv")));
    storeInDb(loadDepartments(getFromDataDir("department.csv")));
    storeInDb(loadProductTypes(getFromDataDir("product-type.csv")));
    storeInDb(loadCustomers(getFromDataDir("customer.csv")));
    storeInDb(loadEmployees(getFromDataDir("employee.csv")));
    storeInDb(loadEmployeeEmails(getFromDataDir("employee-email.csv")));
    storeInDb(loadEmployeePhones(getFromDataDir("employee-phone.csv")));
    storeInDb(loadProducts(getFromDataDir("product.csv")));
    storeInDb(loadOrders(getFromDataDir("order.csv")));
    return true;
  }

  /**
   * Checks that all environment variables needed to connects via JDBC, have non empty values.
   *
   */
  public static void validateJdbcParameters(){
    String[] jdbcParameters = {"DB_URL", "DB_DRIVER", "DB_USER", "DB_PASSWORD", "DB_NAME"};
    for (String jdbcParameter : jdbcParameters) {
      String value = System.getenv(jdbcParameter);
      if (value == null || value.isEmpty()) {
        throw new IllegalArgumentException(String.format("Environment variable %s has an empty value. Use EXPORT %s=<value>", jdbcParameter, jdbcParameter));
      }
    }
  }
}
