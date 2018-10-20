package net.javacogito.jdbcconnector.context;

/**
 * Interface with JDBC parameters
 */

public interface Context {
  /**
   * Gets JDBC URL
   *
   * @return JDBC URL as string
   */
  String getDbUrl();

  /**
   * Gets JDBC user
   *
   * @return JDBC user as string
   */
  String getDbUser();

  /**
   * Gets JDBC password.
   *
   * @return JDBC password as string
   */
  String getDbPassword();

  /**
   * Gets JDBC driver class name.
   *
   * @return JDBC driver class name as string
   */
  String getDbDriver();

  /**
   * Gets JDBC DB name
   *
   * @return DB name
   */
  String getDbName();

  /**
   * Gets home folder of the submodule
   * @return home folder of the submodule
   */
  String getHome();

  /**
   * Get initial connection pool size.
   *
   * @return initial connection pool size
   */
  int getInitialPoolSize();
}
