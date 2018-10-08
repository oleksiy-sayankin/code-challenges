package net.javacogito.mysqlconnector.connection;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static net.javacogito.mysqlconnector.connection.DbType.H2;

public final class ConnectionPool {
  private static final ConnectionPool CONNECTION_POOL = new ConnectionPool();
  private final String DB_HOST;
  private final String DB_DRIVER;
  private final String DB_NAME;
  private final String DB_USER;
  private final String DB_PASSWORD;
  private static DbType dbType = H2;

  private ConnectionPool(){
    String dbHost = System.getenv("DB_HOST");
    if (dbHost == null || dbHost.isEmpty()){
      dbHost = "localhost";
    }
    String dbDriver = System.getenv("DB_DRIVER");
    if (dbDriver == null || dbDriver.isEmpty()){
      dbDriver = "org.h2.Driver";
    }
    String dbName = System.getenv("DB_NAME");
    if (dbName == null || dbName.isEmpty()){
      dbName = "";
    }
    String dbUser = System.getenv("DB_USER");
    if (dbUser == null || dbUser.isEmpty()){
      dbUser = "";
    }
    String dbPassword = System.getenv("DB_PASSWORD");
    if (dbPassword == null || dbPassword.isEmpty()){
      dbPassword = "";
    }

    DB_HOST = dbHost;
    DB_DRIVER = dbDriver;
    DB_NAME = dbName;
    DB_USER = dbUser;
    DB_PASSWORD =dbPassword;

  }


  public static DbType getDbType() {
    return dbType;
  }

  public static void setDbType(DbType dbType) {
    ConnectionPool.dbType = dbType;
  }

  public static ConnectionPool getConnectionPool(){
    return CONNECTION_POOL;
  }

  public Connection getConnection() {
    Connection conn = null;
    try {

      switch (dbType) {
      case MYSQL:
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(String.format(
            "jdbc:mysql://%s/%s?user=%s&password=%s&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
            DB_HOST, DB_NAME, DB_USER, DB_PASSWORD));
        dataSource.setUser(DB_USER);
        dataSource.setPassword(DB_PASSWORD);

        Class.forName(DB_DRIVER);
        conn = DriverManager.getConnection(dataSource.getUrl(), DB_USER, DB_PASSWORD);
        conn.setAutoCommit(true);
        return conn;

      case H2:
        Class.forName(DB_DRIVER);
        conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", DB_USER, DB_PASSWORD);
        conn.setAutoCommit(true);
        return conn;
      default:
        break;
      }

    } catch (SQLException| ClassNotFoundException e) {
      // TODO: log this
    }
    return conn;
  }

  public void returnConnection(Connection returnConnection){

  }
}
