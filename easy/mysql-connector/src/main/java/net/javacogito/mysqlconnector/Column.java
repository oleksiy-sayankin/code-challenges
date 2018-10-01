package net.javacogito.mysqlconnector;

final class Column {
  final String name;
  final Types type;
  Column(String name, Types type){
    this.name = name;
    this.type = type;
  }
}
