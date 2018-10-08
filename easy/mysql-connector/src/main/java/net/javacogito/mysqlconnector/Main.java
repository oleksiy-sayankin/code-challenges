package net.javacogito.mysqlconnector;

import net.javacogito.mysqlconnector.entity.Employee;

import java.io.IOException;
import java.sql.SQLException;

import static net.javacogito.mysqlconnector.MySqlUtil.tableToString;
import static net.javacogito.mysqlconnector.MySqlUtil.execute;
import static net.javacogito.mysqlconnector.MySqlUtil.readFromResource;
import static net.javacogito.mysqlconnector.MySqlUtil.getConnection;

public class Main {
  public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
    // TODO : reimplement
  }

  private static void printFromTable(String host, String dataBase, String user, String password, Table table)
      throws IOException, SQLException, ClassNotFoundException {
    System.out.println(tableToString(execute(getConnection(host, dataBase, user, password), readFromResource("select-star-query.sql")), table));
  }

}
