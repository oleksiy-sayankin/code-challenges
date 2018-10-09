package net.javacogito.mysqlconnector.context;

/**
 * Class to return connection parameters from environment variables.
 */

public class EnvContext implements Context{
  private final String dbUrl = System.getenv("DB_URL");
  private final String dbUser = System.getenv("DB_USER");
  private final String dbPassword = System.getenv("DB_PASSWORD");
  private final String dbDriver = System.getenv("DB_DRIVER");


  /**
   * Gets JDBC URL
   *
   * @return JDBC URL as string
   */
  public String getDbUrl() {
    return dbUrl;
  }

  /**
   * Gets JDBC user
   *
   * @return JDBC user as string
   */
  public String getDbUser() {
    return dbUser;
  }

  /**
   * Gets JDBC password.
   *
   * @return JDBC password as string
   */
  public String getDbPassword() {
    return dbPassword;
  }

  /**
   * Gets JDBC driver class name.
   *
   * @return JDBC driver class name as string
   */
  public String getDbDriver() {
    return dbDriver;
  }
}
