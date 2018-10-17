package net.javacogito.jdbcconnector;

import java.io.IOException;

import static net.javacogito.jdbcconnector.util.DbUtil.initDb;
import static net.javacogito.jdbcconnector.util.DbUtil.storeInDb;
import static net.javacogito.jdbcconnector.util.FileUtil.getFromResources;
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
    initDb();
    storeInDb(loadCountries(getFromResources("country.csv")));
    storeInDb(loadCustomers(getFromResources("customer.csv")));
    storeInDb(loadDepartments(getFromResources("department.csv")));
    storeInDb(loadEmployees(getFromResources("employee.csv")));
    storeInDb(loadEmployeeEmails(getFromResources("employee-email.csv")));
    storeInDb(loadEmployeePhones(getFromResources("employee-phone.csv")));
    storeInDb(loadOrders(getFromResources("order.csv")));
    storeInDb(loadProducts(getFromResources("product.csv")));
    storeInDb(loadProductTypes(getFromResources("product-type.csv")));
    return true;
  }

  /**
   * Checks that all environment variables needed to connects via JDBC, have non empty values.
   *
   */
  public static void validateJdbcParameters(){
    String[] jdbcParameters = {"DB_URL", "DB_DRIVER", "DB_USER", "DB_PASSWORD"};
    for (String jdbcParameter : jdbcParameters) {
      String value = System.getenv(jdbcParameter);
      if (value == null || value.isEmpty()) {
        throw new IllegalArgumentException(String.format("Environment variable %s has an empty value. Use EXPORT %s=<value>", jdbcParameter, jdbcParameter));
      }
    }
  }
}
