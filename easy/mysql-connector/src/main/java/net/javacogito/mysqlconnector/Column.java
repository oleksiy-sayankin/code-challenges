package net.javacogito.mysqlconnector;

public final class Column {
  public final String name;
  public final Types type;
  public Column(String name, Types type){
    this.name = name;
    this.type = type;
  }
}
