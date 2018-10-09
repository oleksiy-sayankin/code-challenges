package net.javacogito.mysqlconnector.entity;

import java.util.Objects;

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

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Country country = (Country) o;
    return id == country.id && Objects.equals(name, country.name);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append("id=");
    sb.append(Integer.toString(id));
    sb.append(", ");
    sb.append("name=");
    sb.append(name);
    sb.append("]");
    return sb.toString();
  }
}
