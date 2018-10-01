package net.javacogito.mysqlconnector;

import static net.javacogito.mysqlconnector.Types.INT;
import static net.javacogito.mysqlconnector.Types.STRING;
import static net.javacogito.mysqlconnector.Types.FLOAT;

public final class Employee implements Table{
  private static final Column[] COLUMNS = {new Column("id", INT), new Column("first_name", STRING)
      , new Column("last_name", STRING), new Column("age", INT), new Column("salary", FLOAT)};

  @Override public Column[] getColumns() {
    return COLUMNS;
  }
}
