package net.javacogito.mysqlconnector;

import java.io.*;
import java.net.URL;
import java.sql.*;

final class MySqlUtil {
  private MySqlUtil() {
  }

  static Connection getConnection(String host, String dataBase, String user, String password)
      throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager
        .getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", host, dataBase, user, password));
  }

  static ResultSet execute(Connection connection, String query) throws SQLException {
    Statement statement = connection.createStatement();
    return statement.executeQuery(query);
  }

  static String tableToString(ResultSet rs, Table table) throws SQLException {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    while (rs.next()) {
      if (first) {
        first = false;
        appendRow(sb, rs, table);
      } else {
        sb.append("\n");
        appendRow(sb, rs, table);
      }
    }
    return sb.toString();
  }


  static String readFromResource(String pathToResource) throws IOException {
    URL url = Thread.currentThread().getContextClassLoader().getResource(pathToResource);
    File file = new File(url.getPath());
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    StringBuilder sb = new StringBuilder();
    String inputLine;
    boolean first = true;
    while ((inputLine = buffer.readLine()) != null) {
      if (first) {
        first = false;
        sb.append(inputLine);
      } else {
        sb.append("\n");
        sb.append(inputLine);
      }
    }
    return sb.toString();
  }

  private static void appendRow(StringBuilder sb, ResultSet rs, Table table) throws SQLException {
    boolean first = true;
    for (Column column : table.getColumns()) {
      if (first) {
        first = false;
        appendColumn(sb, rs, column);
      } else {
        sb.append(",");
        appendColumn(sb, rs, column);
      }
    }
  }

  private static void appendColumn(StringBuilder sb, ResultSet rs, Column column) throws SQLException {
    switch (column.type) {
    case INT:
      sb.append(rs.getInt(column.name));
      break;
    case STRING:
      sb.append(rs.getString(column.name));
      break;
    case FLOAT:
      sb.append(rs.getFloat(column.name));
      break;
    }
  }
}
