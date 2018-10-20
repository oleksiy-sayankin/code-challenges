package net.javacogito.jdbcconnector.context;

/**
 * Class to return connection parameters from environment variables.
 */

class EnvContext implements Context{
  private final String dbUrl = System.getenv("DB_URL");
  private final String dbUser = System.getenv("DB_USER");
  private final String dbPassword = System.getenv("DB_PASSWORD");
  private final String dbDriver = System.getenv("DB_DRIVER");
  private final String dbName = System.getenv("DB_NAME");
  private final String home = System.getenv("JDBC_CONNECTOR_HOME");
  private final int initialPoolSize = Integer.parseInt(System.getenv("INITIAL_POOL_SIZE"));


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

  /**
   * Gets JDBC DB name
   *
   * @return DB name
   */
  @Override public String getDbName() {
    return dbName;
  }

  /**
   * Gets home folder of the submodule
   * @return home folder of the submodule
   */
  @Override public String getHome() {
    return home;
  }

  /**
   * Get initial connection pool size.
   *
   * @return initial connection pool size
   */
  @Override public int getInitialPoolSize() {
    return initialPoolSize;
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append("dbUrl=");
    sb.append(dbUrl);
    sb.append(", ");
    sb.append("dbUser=");
    sb.append(dbUser);
    sb.append(", ");
    sb.append("dbPassword=");
    sb.append(dbPassword);
    sb.append(", ");
    sb.append("dbName=");
    sb.append(dbName);
    sb.append(", ");
    sb.append("dbDriver=");
    sb.append(dbDriver);
    sb.append(", ");
    sb.append("home=");
    sb.append(home);
    sb.append("]");
    return sb.toString();
  }
}
