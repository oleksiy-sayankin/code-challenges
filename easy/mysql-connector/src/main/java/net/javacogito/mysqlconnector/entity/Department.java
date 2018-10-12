package net.javacogito.mysqlconnector.entity;

import net.javacogito.mysqlconnector.controller.Controller;
import net.javacogito.mysqlconnector.controller.DepartmentController;

import java.io.Serializable;
import java.util.Objects;

/**
 * Basic lass for 'department' table. Represents a single row.
 */

public final class Department implements Serializable, Entity {
  private int id;
  private String name;

  /**
   * Gets id.
   *
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id primary key
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name department name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets department controller.
   *
   * @return department controller.
   */
  @Override public Controller getController() {
    return new DepartmentController();
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Department that = (Department) o;
    return id == that.id && Objects.equals(name, that.name);
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
