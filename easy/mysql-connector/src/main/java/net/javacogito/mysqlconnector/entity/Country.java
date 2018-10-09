package net.javacogito.mysqlconnector.entity;

/**
 * Basic lass for 'country' table. Represents a single row.
 */
public class Country {
  private int id;
  private String name;

  /**
   * Gets id.
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id.
   * @param id primary key
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets name.
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   * @param name country name.
   */
  public void setName(String name) {
    this.name = name;
  }
}
