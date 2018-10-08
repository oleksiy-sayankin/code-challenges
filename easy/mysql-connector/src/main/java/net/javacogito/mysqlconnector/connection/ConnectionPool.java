package net.javacogito.mysqlconnector.connection;

import java.sql.Connection;

public interface ConnectionPool {
  Connection getConnection();
  boolean releaseConnection(Connection connection);
  String getDbUrl();
  String getDbUser();
  String getDbPassword();
}
